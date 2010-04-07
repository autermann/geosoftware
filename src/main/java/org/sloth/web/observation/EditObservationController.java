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
import static org.sloth.util.ControllerUtils.internalErrorView;
import static org.sloth.util.ControllerUtils.isAdmin;
import static org.sloth.util.ControllerUtils.isAuth;
import static org.sloth.util.ControllerUtils.isOwnObservation;
import static org.sloth.util.ControllerUtils.notFoundMAV;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to edit an {@code Observation}.
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
@Controller
@RequestMapping("/o/edit/{id}")
@SessionAttributes(types = Observation.class)
public class EditObservationController {

	private static final String VIEW = "observations/edit";
	private static final String OBSERVATION_ATTRIBUTE = "observation";
	private static final String CATEGORIE_ATTRIBUTE = "categories";
	private static final Logger logger = LoggerFactory
			.getLogger(EditObservationController.class);
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
	public void setAllowedFields(WebDataBinder webDataBinder) {
		CategorieEditor c = new CategorieEditor();
		c.setObservationService(observationService);
		webDataBinder.registerCustomEditor(Categorie.class, c);
		webDataBinder.setDisallowedFields("id");
	}

	/**
	 * Handles the {@code GET} request and sets up the form.
	 */
	@RequestMapping(method = GET)
	public ModelAndView setupForm(@PathVariable Long id, HttpSession session,
			HttpServletResponse response) throws IOException {
		if (isAuth(session)) {
			Observation o = observationService.getObservation(id);
			if (o == null) {
				return notFoundMAV(response);
			} else if (isAdmin(session) || isOwnObservation(session, o)) {
				ModelAndView mav = new ModelAndView(VIEW);
				mav.addObject(OBSERVATION_ATTRIBUTE, o.clone());
				mav.addObject(CATEGORIE_ATTRIBUTE, this.observationService
						.getCategories());
				return mav;
			}
		}
		return forbiddenMAV(response);
	}

	/**
	 * Handles the {@code POST} request and saves the changes made.
	 */
	@RequestMapping(method = POST)
	public String processSubmit(@ModelAttribute Observation observation,
			BindingResult result, SessionStatus status, HttpSession session,
			HttpServletResponse response) throws IOException {
		if (isAdmin(session) || isOwnObservation(session, observation)) {
			this.observationValidator.validate(observation, result);
			if (result.hasErrors()) {
				return VIEW;
			} else {
				try {
					logger.warn("Wird das hier jemals ausgeführt!?");
					Observation oOrig = this.observationService.getObservation(observation.getId());
					mergeObservation(observation, oOrig);
					this.observationService.updateObservation(oOrig);
				} catch (Exception e) {
					logger.warn("Unexpected Exception", e);
					return internalErrorView(response);
				} finally {
					status.setComplete();
				}
				return "redirect:/o";
			}
		}
		return forbiddenView(response);
	}

	private void mergeObservation(Observation changes, Observation orig){
		orig.setCategorie(changes.getCategorie());
		orig.setCoordinate(changes.getCoordinate());
		orig.setTitle(changes.getTitle());
		orig.setDescription(changes.getDescription());
	}
}
