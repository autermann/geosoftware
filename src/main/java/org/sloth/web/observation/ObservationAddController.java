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

import static org.sloth.util.ControllerUtils.forbiddenMAV;
import static org.sloth.util.ControllerUtils.forbiddenView;
import static org.sloth.util.ControllerUtils.getUser;
import static org.sloth.util.ControllerUtils.internalErrorView;
import static org.sloth.util.ControllerUtils.isAuth;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.Categorie;
import org.sloth.model.Observation;
import org.sloth.service.ObservationService;
import org.sloth.util.CategorieEditor;
import org.sloth.validation.ObservationValidator;
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
 * Controller to create a new {@code Observation}.
 * @deprecated Functionality replaced by {@code FrontPageController}
 * @see org.sloth.web.FrontPageController
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
@RequestMapping("/o/new")
@SessionAttributes(types = Observation.class)
@Deprecated
public class ObservationAddController {

	private static final String VIEW = "observations/form";
	private static final String OBSERVATION_ATTRIBUTE = "observation";
	private static final String CATEGORIES_ATTRIBUTE = "categories";
	private Logger logger = LoggerFactory
			.getLogger(ObservationAddController.class);
	private ObservationService observationService;
	private ObservationValidator observationValidator;

	/**
	 * 
	 * @param observationValidator
	 *            the {@code ObservationValidator} to set
	 */
	@Autowired
	public void setObservationValidator(
			ObservationValidator observationValidator) {
		this.observationValidator = observationValidator;
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
		webDataBinder.setDisallowedFields("id", "user", "creationDate");
		CategorieEditor c = new CategorieEditor();
		c.setObservationService(this.observationService);
		webDataBinder.registerCustomEditor(Categorie.class, c);
	}

	/**
	 * Handles the {@code GET} request and sets up the form.
	 */
	@RequestMapping(method = GET)
	public ModelAndView setupForm(HttpSession session,
			HttpServletResponse response) throws IOException {
		if (isAuth(session)) {
			ModelAndView mav = new ModelAndView(VIEW);
			mav.addObject(OBSERVATION_ATTRIBUTE, new Observation());
			mav.addObject(CATEGORIES_ATTRIBUTE, observationService
					.getCategories());
			return mav;
		} else {
			return forbiddenMAV(response);
		}
	}

	/**
	 * Handles the {@code POST} request, validates the form and registers the
	 * new {@code Observation}.
	 */
	@RequestMapping(method = POST)
	public String processSubmit(
			@ModelAttribute(OBSERVATION_ATTRIBUTE) Observation observation,
			BindingResult result, SessionStatus status, HttpSession session,
			HttpServletResponse response) throws IOException {
		if (isAuth(session)) {
			observation.setUser(getUser(session));
			this.observationValidator.validate(observation, result);
			if (result.hasErrors()) {
				return VIEW;
			} else {
				try {
					this.observationService.registrate(observation);
				} catch (Exception e) {
					logger.warn("Unexpected Exception", e);
					return internalErrorView(response);
				} finally {
					status.setComplete();
				}
				return "redirect:/admin/observations";
			}
		} else {
			return forbiddenView(response);
		}
	}

}
