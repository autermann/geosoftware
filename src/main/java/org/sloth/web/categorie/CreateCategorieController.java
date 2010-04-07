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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for creating a new {@code Categorie}.
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 * 
 */
@Controller
@RequestMapping("/c/new")
@SessionAttributes(types = Categorie.class)
public class CreateCategorieController {

	private static final String CATEGORIE_ATTRIBUTE = "categorie";
	private static final String VIEW = "categories/new";
	private static final Logger logger = LoggerFactory
			.getLogger(CreateCategorieController.class);
	private ObservationService observationService;
	private CategorieValidator categorieValidator;

	/**
	 * @param categorieValidator
	 *            the {@link CategorieValidator} to set
	 */
	@Autowired
	public void setCategorieValidator(CategorieValidator categorieValidator) {
		this.categorieValidator = categorieValidator;
	}

	/**
	 * @param observationService
	 *            the {@code ObservationService} to set
	 */
	@Autowired
	public void setObservationService(ObservationService observationService) {
		this.observationService = observationService;
	}

	/**
	 * Sets custom parameters to the {@code WebDataBinder}.
	 * 
	 * @param webDataBinder
	 *            the {@code WebDataBinder} to initialize
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.setAllowedFields("title", "description", "iconFileName");
	}

	/**
	 * Handles the {@code GET} request. Test the rights and sets up the form.
	 */
	@RequestMapping(method = GET)
	public ModelAndView setup(HttpSession session, HttpServletResponse response)
			throws IOException {
		if (isAdmin(session)) {
			return new ModelAndView(VIEW, CATEGORIE_ATTRIBUTE, new Categorie());
		} else {
			return forbiddenMAV(response);
		}
	}

	/**
	 * Handles the {@code POST} request. Validates the input and creates a new
	 * {@code Categorie}.
	 */
	@RequestMapping(method = POST)
	public String submit(
			@ModelAttribute(CATEGORIE_ATTRIBUTE) Categorie categorie,
			BindingResult result, SessionStatus status, HttpSession session,
			HttpServletResponse response) throws IOException {
		if (isAdmin(session)) {
			this.categorieValidator.validate(categorie, result);
			if (result.hasErrors()) {
				return VIEW;
			} else {
				try {
					this.observationService.registrate(categorie);
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
