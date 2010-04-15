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
package org.sloth.web.ajax;

import static org.sloth.util.ControllerUtils.isAuth;
import static org.sloth.util.ControllerUtils.getUser;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.Categorie;

import org.sloth.model.Coordinate;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * {@code Controller} to create a {@code Observation}.
 */
@Controller
@RequestMapping("/ajax/bubble")
@SessionAttributes({"observation","categories"})
public class ObservationCreationBubbleController {

	private static final String SUCCESS_VIEW = "ajax/success";
	private static final String ERROR_VIEW = "ajax/error";
	private static final String FORM_VIEW = "ajax/form";
	private static final String REJECT_VIEW = "ajax/reject";
	private static final String OBSERVATION_ATTRIBUTE = "observation";
	private static final String CATEGORIES_ATTRIBUTE = "categories";
	private static final String LONGITUDE_PARAM = "lon";
	private static final String LATITUDE_PARAM = "lat";
	private static final Logger logger = LoggerFactory.getLogger(
			ObservationCreationBubbleController.class);
	private ObservationValidator observationValidator;
	private ObservationService observationService;

	/**
	 * @param observationValidator the {@code ObservationValidator} to set
	 */
	@Autowired
	public void setObservationValidator(
			ObservationValidator observationValidator) {
		this.observationValidator = observationValidator;
	}

	/**
	 * @param observationService the {@code ObservationService} to set
	 */
	@Autowired
	public void setObservationService(ObservationService observationService) {
		this.observationService = observationService;
	}

	/**
	 * Configures the Binder
	 * 
	 * @param webDataBinder the {@code WebDataBinder} to configure
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		CategorieEditor categorieEditor = new CategorieEditor();
		categorieEditor.setObservationService(observationService);
		webDataBinder.registerCustomEditor(Categorie.class, categorieEditor);
	}

	/**
	 * Handles the {@code GET}-request and sets up the form.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView get(HttpSession session,
							@RequestParam(LONGITUDE_PARAM) Double longitude,
							@RequestParam(LATITUDE_PARAM) Double latitude) {
		if (isAuth(session)) {
			ModelAndView mav = new ModelAndView(FORM_VIEW);
			Observation o = new Observation();
			o.setCoordinate(new Coordinate(longitude, latitude));
			mav.addObject(OBSERVATION_ATTRIBUTE, o);
			mav.addObject(CATEGORIES_ATTRIBUTE,
						  observationService.getCategories());
			return mav;
		} else {
			return new ModelAndView(REJECT_VIEW);
		}
	}
	
	/**
	 * Handles the {@code POST}-request and registrates the {@code Observation}.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView post(@RequestParam(LONGITUDE_PARAM) Double longitude,
					   @RequestParam(LATITUDE_PARAM) Double latitude,
					   @ModelAttribute(OBSERVATION_ATTRIBUTE) Observation observation,
					   BindingResult result, SessionStatus status,
					   HttpSession session, Model m) {
		if (isAuth(session)) {
			logger.info("Session is authed.");
			logger.info("Building Coordinate from given Lon: {} and Lat: {}.",
						longitude, latitude);
			observation.setCoordinate(new Coordinate(longitude, latitude));
			logger.info("Setting User {}.",getUser(session));
			observation.setUser(getUser(session));
			logger.info("Validating Observation {}", observation);
			observationValidator.validate(observation, result);
			if (result.hasErrors()) {
				logger.info("Found Errors.");
				logger.debug("{}",m.asMap().keySet());
				return new ModelAndView(FORM_VIEW,m.asMap());
			} else {
				try {
					logger.info("Trying to persist Observation {}", observation);
					observationService.registrate(observation);
					logger.info("Succesfully persisted Observation {}", observation);
					ModelAndView mav = new ModelAndView(SUCCESS_VIEW);
					mav.addObject(observation);
					return new ModelAndView(SUCCESS_VIEW, OBSERVATION_ATTRIBUTE, observation);
				} catch(Exception e) {
					logger.warn("Unexpected Exception.", e);
					return new ModelAndView(ERROR_VIEW);
				} finally {
					logger.info("Clearing session attributes");
					//status.setComplete();
				}
			}
		} else {
			logger.info("Session is not authed");
			return new ModelAndView(REJECT_VIEW);
		}
	}

}
