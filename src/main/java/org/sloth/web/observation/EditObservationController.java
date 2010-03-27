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
import javax.servlet.http.HttpSession;
import org.sloth.model.Observation;
import org.sloth.service.ObservationService;
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
import static org.sloth.web.util.ControllerUtils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.Categorie;
import org.sloth.service.validator.ObservationValidator;
import org.sloth.web.util.CategorieEditor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/o/edit/{id}")
@SessionAttributes(types = Observation.class)
public class EditObservationController {

	private static final String view = "observations/edit";
	private static final String observationAttribute = "observation";
	private static final String categorieAttribute = "categories";
	protected static final Logger logger = LoggerFactory
			.getLogger(EditObservationController.class);
	private ObservationService observationService;
	private ObservationValidator observationValidator;

	@Autowired
	public void setObservationValidator(
			ObservationValidator observationValidator) {
		this.observationValidator = observationValidator;
	}

	@Autowired
	public void setObservationManager(ObservationService observationManager) {
		this.observationService = observationManager;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		CategorieEditor c = new CategorieEditor();
		c.setObservationService(observationService);
		dataBinder.registerCustomEditor(Categorie.class, c);
		dataBinder.setDisallowedFields("id", "creationDate", "user");
	}

	@RequestMapping(method = GET)
	public ModelAndView setupForm(@PathVariable Long id, HttpSession s,
			HttpServletResponse r) throws IOException {
		if (isAuth(s)) {
			Observation o = observationService.getObservation(id);
			if (o == null)
				return notFoundMAV(r);
			if (isAdmin(s) || isOwnObservation(s, o)) {
				ModelAndView mav = new ModelAndView(view);
				mav.addObject(observationAttribute, o);
				mav.addObject(categorieAttribute, observationService
						.getCategories());
				return mav;
			}
		}
		return forbiddenMAV(r);
	}

	@RequestMapping(method = POST)
	public String processSubmit(@ModelAttribute Observation o,
			BindingResult result, SessionStatus status, HttpSession s,
			HttpServletResponse r) throws IOException {
		if (isAdmin(s) || isOwnObservation(s, o)) {
			observationValidator.validate(o, result);
			if (result.hasErrors())
				return view;
			else {
				try {
					observationService.updateObservation(o);
				} catch (Exception e) {
					logger.warn("Unexpected Exception", e);
					return internalErrorView(r);
				}
				status.setComplete();
				return "redirect:/o";
			}
		}
		return forbiddenView(r);
	}
}
