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

import static org.sloth.util.ControllerUtils.forbiddenMAV;
import static org.sloth.util.ControllerUtils.forbiddenView;
import static org.sloth.util.ControllerUtils.internalErrorView;
import static org.sloth.util.ControllerUtils.isAdmin;
import static org.sloth.util.ControllerUtils.notFoundMAV;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.Categorie;
import org.sloth.service.ObservationService;
import org.sloth.validation.CategorieValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to edit a {@code Categorie}.
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
@Controller
@RequestMapping("/c/edit/{id}")
@SessionAttributes(types = Categorie.class)
public class EditCategorieController {

	private static final String VIEW = "categories/edit";
	private static final String CATEGORIE_ATTRIBUTE = "categorie";
	private static final Logger logger = LoggerFactory
			.getLogger(EditCategorieController.class);
	private ObservationService observationService;
	private CategorieValidator categorieValidator;

	/**
	 * @param categorieValidateor
	 *            the {@link CategorieValidator} to set
	 */
	@Autowired
	public void setCategorieValidator(CategorieValidator categorieValidateor) {
		this.categorieValidator = categorieValidateor;
	}

	/**
	 * @param os
	 *            the {@code ObservationService} to set
	 */
	@Autowired
	public void setObservationService(ObservationService os) {
		this.observationService = os;
	}

	/**
	 * Sets custom parameters to the {@code WebDataBinder}.
	 * 
	 * @param webDataBinder
	 *            the {@code WebDataBinder} to initialize
	 */
	@InitBinder
	public void setAllowedFields(WebDataBinder webDataBinder) {
		webDataBinder.setAllowedFields("title", "description", "iconFileName");
	}

	/**
	 * Handles the {@code GET} request. Tests the rights and set up the form.
	 */
	@RequestMapping(method = GET)
	public ModelAndView setupForm(@PathVariable Long id, HttpSession session,
			HttpServletResponse response) throws IOException {
		if (isAdmin(session)) {
			Categorie c = observationService.getCategorie(id);
			if (c == null) {
				return notFoundMAV(response);
			} else {
				return new ModelAndView(VIEW, CATEGORIE_ATTRIBUTE, c);
			}
		} else {
			return forbiddenMAV(response);
		}

	}

	/**
	 * Handles the {@code POST} request. Saves the changes made to the {@code
	 * Categorie}.
	 */
	@RequestMapping(method = POST)
	public String processSubmit(
			@ModelAttribute(CATEGORIE_ATTRIBUTE) Categorie categorie,
			BindingResult result, SessionStatus status, HttpSession session,
			HttpServletResponse response) throws IOException {
		if (isAdmin(session)) {
			categorieValidator.validate(categorie, result);
			if (result.hasErrors()) {
				return VIEW;
			} else {
				try {
					observationService.updateCategorie(categorie);
				} catch (Exception e) {
					logger.warn("Unexpected Exception", e);
					return internalErrorView(response);
				} finally {
					status.setComplete();
				}
				return "redirect:/c";
			}
		} else {
			return forbiddenView(response);
		}
	}

}
