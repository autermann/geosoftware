/*
 * Copyright (C) 2009-2010  Stefan Arndt, Christian Autermann, Dustin Demuth,
 *                  Christoph Fendrich, Simon Ottenhues, Christian Paluschek
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sloth.web.observation;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.sloth.web.util.CategorieEditor;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.Categorie;
import org.sloth.model.Group;
import org.sloth.model.Observation;
import org.sloth.service.ObservationService;
import org.sloth.service.validator.ObservationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import static org.sloth.web.util.ControllerUtils.*;

@Controller
@RequestMapping("/o/new")
@SessionAttributes(types = Observation.class)
@Deprecated
public class ObservationAddController {

	private static final String view = "observations/form";
	private static final String observationAttribute = "observation";
	private static final String categorieCollectionAttribute = "categories";
	private Logger logger = LoggerFactory
			.getLogger(ObservationAddController.class);
	private ObservationService observationManager;
	private ObservationValidator validator;

	@Autowired
	public void setObservationValidator(ObservationValidator validator) {
		this.validator = validator;
	}

	@Autowired
	public void setObservationManager(ObservationService observationManager) {
		this.observationManager = observationManager;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id", "user", "creationDate");
		CategorieEditor c = new CategorieEditor();
		c.setObservationService(observationManager);
		dataBinder.registerCustomEditor(Categorie.class, c);
	}

	@RequestMapping(method = GET)
	public ModelAndView setupForm(HttpSession s, HttpServletResponse r)
			throws IOException {
		if (isAuth(s)) {
			ModelAndView mav = new ModelAndView(view);
			mav.addObject(observationAttribute, new Observation());
			mav.addObject(categorieCollectionAttribute, observationManager
					.getCategories());
			return mav;
		} else
			return forbiddenMAV(r);
	}

	@RequestMapping(method = POST)
	public String processSubmit(
			@ModelAttribute(observationAttribute) Observation observation,
			BindingResult result, SessionStatus status, HttpSession session,
			HttpServletResponse r) throws IOException {
		if (isAuth(session)) {
			observation.setUser(getUser(session));
			validator.validate(observation, result);
			if (result.hasErrors())
				return view;
			else {
				try {
					this.observationManager.registrate(observation);
				} catch (Exception e) {
					logger.warn("Unexpected Exception", e);
					return internalErrorView(r);
				}
				status.setComplete();
				return "redirect:/admin/observations";
			}
		} else
			return forbiddenView(r);
	}
}
