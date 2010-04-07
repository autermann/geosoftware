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
import static org.sloth.util.ControllerUtils.isAdmin;
import static org.sloth.util.ControllerUtils.isAuth;
import static org.sloth.util.ControllerUtils.isOwnObservation;
import static org.sloth.util.ControllerUtils.notFoundMAV;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sloth.model.Observation;
import org.sloth.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to view the details of an {@code Observation}.
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
@Controller
@RequestMapping("/o/{id}")
@SessionAttributes(types = Observation.class)
public class ViewObservationController {

	private static final String VIEW = "observations/details";
	private static final String OBSERVATIONS_ATTRIBUTE = "observation";
	private ObservationService observationService;

	/**
	 * @param observationService
	 *            the {@code ObservationService} to set
	 */
	@Autowired
	public void setObservationService(ObservationService observationService) {
		this.observationService = observationService;
	}

	/**
	 * Handles all request and sets up the view.
	 */
	@RequestMapping
	public ModelAndView handle(@PathVariable Long id, HttpSession session,
			HttpServletResponse response) throws IOException {
		if (isAuth(session)) {
			Observation o = this.observationService.getObservation(id);
			if (o == null) {
				return notFoundMAV(response);
			} else if (isAdmin(session) || isOwnObservation(session, o)) {
				ModelAndView mav = new ModelAndView(VIEW);
				mav.addObject(OBSERVATIONS_ATTRIBUTE, o);
				return mav;
			}
		}
		return forbiddenMAV(response);
	}
}
