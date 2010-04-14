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

import static org.sloth.util.ValidationUtils.isNotEmptyOrWhitespace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.service.ObservationService;
import org.sloth.util.Config;
import org.sloth.validation.ObservationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
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
@SessionAttributes("observations")
public class FrontPageController {

	private static final String VIEW = "index";
	private static final String MAP_CONTENT_ATTRIBUTE = "observations";
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
			logger.warn("Invalid or null value for property 'lastObservationsCount'.", e);
		}
		if (i == null) {
			VISIBLE_OBSERVATIONS = VISIBLE_OBSERVATIONS_DEFAULT;
		} else {
			VISIBLE_OBSERVATIONS = i;
		}
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
	 * Handles the {@code GET} request. It creates the model to fill the Map
	 * with {@code Observation}s. An optional query parameter is also processed.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView fillMap(@RequestParam(value = SEARCH_PARAM, required = false) String query) {
		return new ModelAndView(VIEW, MAP_CONTENT_ATTRIBUTE, isNotEmptyOrWhitespace(query)
				? this.observationService.getObservations(query)
				: this.observationService.getNewestObservations(VISIBLE_OBSERVATIONS));
	}
}
