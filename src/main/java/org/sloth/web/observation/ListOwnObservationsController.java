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
import static org.sloth.util.ControllerUtils.getUser;
import static org.sloth.util.ControllerUtils.isAuth;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sloth.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
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
@RequestMapping("/o/own")
public class ListOwnObservationsController {

	private final static String VIEW = "observations/list";
	private final static String OBSERVATIONS_ATTRIBUTE = "observations";
	private ObservationService os;

	@Autowired
	public void setobservationService(ObservationService os) {
		this.os = os;
	}

	@RequestMapping
	public ModelAndView setupList(HttpSession s, HttpServletResponse r)
			throws IOException {
		if (isAuth(s)) {
			return new ModelAndView(VIEW, OBSERVATIONS_ATTRIBUTE, this.os
					.getObservationsByUser(getUser(s)));
		} else {
			return forbiddenMAV(r);
		}
	}
}
