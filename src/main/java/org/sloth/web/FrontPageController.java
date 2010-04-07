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
package org.sloth.web;

import static org.sloth.util.ControllerUtils.forbiddenView;
import static org.sloth.util.ControllerUtils.getUser;
import static org.sloth.util.ControllerUtils.internalErrorView;
import static org.sloth.util.ControllerUtils.isAuth;
import static org.sloth.util.ValidationUtils.isNotEmptyOrWhitespace;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.Categorie;
import org.sloth.model.Observation;
import org.sloth.service.ObservationService;
import org.sloth.util.CategorieEditor;
import org.sloth.util.Config;
import org.sloth.validation.ObservationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to handle the front page. It cares for filling the map, search the
 * database and creating new {@code Observation}s.
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
@RequestMapping("/")
@SessionAttributes( { "observations", "observation", "categories" })
public class FrontPageController {

	private static final String VIEW = "index";
	private static final String MAP_CONTENT_ATTRIBUTE = "observations";
	private static final String NEW_OBSERVATION_ATTRIBUTE = "observation";
	private static final String CATEGORIE_ATTRIBUTE = "categories";
	private static final String SEARCH_PARAM = "q";
	private static final int VISIBLE_OBSERVATIONS_DEFAULT = 10;
	private static final int VISIBLE_OBSERVATIONS;
	private static final Logger logger = LoggerFactory
			.getLogger(FrontPageController.class);
	private ObservationService observationService;
	private ObservationValidator observationValidator;

	static {
		Integer i = null;
		try {
			i = Integer.valueOf(Config.getProperty("lastObservationsCount"));
		} catch (NumberFormatException e) {
			logger
					.warn(
							"Invalid or null value for property 'lastObservationsCount'.",
							e);
		}
		if (i == null) {
			VISIBLE_OBSERVATIONS = VISIBLE_OBSERVATIONS_DEFAULT;
		} else {
			VISIBLE_OBSERVATIONS = i;
		}
	}

	/**
	 * @param ov
	 *            the {@code ObservationValidator} to set
	 */
	@Autowired
	public void setObservationValidator(ObservationValidator ov) {
		this.observationValidator = ov;
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
	public void initBinder(WebDataBinder webDataBinder) {
		CategorieEditor c = new CategorieEditor();
		c.setObservationService(observationService);
		webDataBinder.registerCustomEditor(Categorie.class, c);
	}

	/**
	 * Handles the {@code GET} request. It creates the model to fill the Map
	 * with {@code Observation}s. An optional query parameter is also processed.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView fillMap(HttpSession session,
			@RequestParam(value = SEARCH_PARAM, required = false) String string) {
		ModelAndView mav = new ModelAndView(VIEW);
		if (isAuth(session)) {
			mav.addObject(NEW_OBSERVATION_ATTRIBUTE, new Observation());
			mav.addObject(CATEGORIE_ATTRIBUTE, observationService
					.getCategories());
		}
		return mav.addObject(MAP_CONTENT_ATTRIBUTE,
				isNotEmptyOrWhitespace(string) ? this.observationService
						.getObservations(string) : this.observationService
						.getNewestObservations(VISIBLE_OBSERVATIONS));
	}

	/**
	 * Handles the {@code POST} request. Creates a new {@code Observation}.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String saveObservation(HttpSession session,
			HttpServletResponse request,
			@ModelAttribute(NEW_OBSERVATION_ATTRIBUTE) Observation observation,
			BindingResult result, SessionStatus status) throws IOException {
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
					return internalErrorView(request);
				} finally {
					status.setComplete();
				}
				return "redirect:/";
			}
		} else {
			return forbiddenView(request);
		}
	}
}
