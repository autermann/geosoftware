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

import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.Observation;
import org.sloth.model.ObservationCategorie;
import org.sloth.service.ObservationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 *
 * @todo
 * @author arnie
 */
@Controller
@RequestMapping("/observations")
public class ObservationListController {

	/**
	 *
	 * @todo
	 */
	protected final static Logger logger = LoggerFactory.getLogger(
			ObservationListController.class);
	@Autowired
	private ObservationManager observationManager;

	/**
	 *
	 * @todo
	 * @param observationManager
	 */
	public void setobservationManager(ObservationManager observationManager) {
		this.observationManager = observationManager;
	}

	/**
	 *
	 * @todo
	 * @return
	 */
	protected ObservationManager getObservationManager() {
		return this.observationManager;
	}

	/**
	 *
	 * @todo
	 * @param model
	 * @return
	 */
	@RequestMapping(method = GET)
	public String setupList(Model model) {
		Collection<Observation> observations = getObservationManager().getObservations();
		logger.info("Request for Observation List; got {} entrys.", observations.size());
		model.addAttribute("observations", observations);
		return "observations/list";
	}

}
