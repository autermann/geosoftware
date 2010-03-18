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

import org.sloth.model.Observation;
import org.sloth.service.ObservationService;
import org.sloth.service.validator.ObservationValidator;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
@RequestMapping("/observations/edit/{id}")
@SessionAttributes(types = Observation.class)
public class ObservationEditController {

    protected static final Logger logger = LoggerFactory.getLogger(
			ObservationEditController.class);

	@Autowired
	private ObservationService observationManager;
	@Autowired
	private ObservationValidator observationValidator;

	/**
	 * @todo
	 * @param userValidator
	 */
	public void setObservationValidator(ObservationValidator observationValidator) {
		this.observationValidator = observationValidator;
	}

	/**
	 * @todo
	 * @return
	 */
	protected ObservationValidator getObservationValidator() {
		return this.observationValidator;
	}

	/**
	 * @todo
	 * @param userManager
	 */
	public void setObservationManager(ObservationService observationManager) {
		this.observationManager = observationManager;
	}

	/**
	 * @todo
	 * @return
	 */
	protected ObservationService getObservationManager() {
		return this.observationManager;
	}

	/**
	 * @todo
	 * @param dataBinder
	 */
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id", "creationDate", "user");
	}

	/**
	 * @param model
	 * @return
	 * @todo
	 * Beim Aufruf der Seite wird die GET-Methoder aufgerufen...
	 */
	@RequestMapping(method = GET)
	public String setupForm(@PathVariable Long id, Model model) {
                Observation observation = getObservationManager().getObservation(id);
                logger.info("Edit of Details for Observation {}", id);
		model.addAttribute("observation", observation);
		return "observations/edit";
	}



	/**
	 * @param user
	 * @param result
	 * @param status
	 * @return
	 * @todo
	 * Beim Absenden des Formulars die POST-Methode...
	 */
	@RequestMapping(method = POST)
	public String processSubmit(@ModelAttribute Observation observation, BindingResult result,
								SessionStatus status) {
		getObservationValidator().validate(observation, result);
		if (result.hasErrors()) {
			return "observations/";
		} else {
			this.observationManager.updateObservation(observation);
			status.setComplete();
			return "observations";
		}
	}

}
