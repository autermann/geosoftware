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
package org.sloth.web.categorie;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.Categorie;
import org.sloth.service.ObservationService;
import org.sloth.service.validator.CategorieValidator;
import static org.sloth.web.util.ControllerUtils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/c/new")
@SessionAttributes(types = Categorie.class)
public class CreateCategorieController {

	private static final String modelAttributeName = "categorie";
	private static final String view = "categories/new";
	private static final Logger logger = LoggerFactory
			.getLogger(CreateCategorieController.class);
	private ObservationService observationService;
	private CategorieValidator categorieValidator;

	@Autowired
	public void setCategorieValidator(CategorieValidator categorieValidator) {
		this.categorieValidator = categorieValidator;
	}

	@Autowired
	public void setObservationService(ObservationService observationService) {
		this.observationService = observationService;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.setAllowedFields("title", "description", "iconFileName");
	}

	@RequestMapping(method = GET)
	public ModelAndView setup(HttpSession session, HttpServletResponse response)
			throws IOException {
		if (isAdmin(session))
			return new ModelAndView(view, modelAttributeName, new Categorie());
		else
			return forbiddenMAV(response);
	}

	@RequestMapping(method = POST)
	public String submit(
			@ModelAttribute(modelAttributeName) Categorie categorie,
			BindingResult result, SessionStatus status, HttpSession s,
			HttpServletResponse r) throws IOException {
		if (isAdmin(s)) {
			categorieValidator.validate(categorie, result);
			if (result.hasErrors())
				return view;
			else {
				try {
					observationService.registrate(categorie);
				} catch (Exception e) {
					// should not happen; the validator shound found all cases.
					logger.warn("Unexpected Exception", e);
					return internalErrorView(r);
				}
				status.setComplete();
				return "redirect:/c";
			}
		} else
			return forbiddenView(r);
	}
}
