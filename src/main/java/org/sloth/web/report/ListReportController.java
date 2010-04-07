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
package org.sloth.web.report;

import static org.sloth.util.ControllerUtils.forbiddenMAV;
import static org.sloth.util.ControllerUtils.getUser;
import static org.sloth.util.ControllerUtils.isAdmin;
import static org.sloth.util.ControllerUtils.isAuth;
import static org.sloth.util.ControllerUtils.isSameId;
import static org.sloth.util.ControllerUtils.notFoundMAV;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sloth.model.Observation;
import org.sloth.model.User;
import org.sloth.service.ObservationService;
import org.sloth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to list {@code Report}s. Can be filtered by an {@code User} or an
 * {@code Observation}.
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
public class ListReportController {

	private static final String VIEW = "reports/list";
	private static final String REPORTS_ATTRIBUTE = "reports";
	private ObservationService observationService;
	private UserService userService;

	/**
	 * @param observationService
	 *            the {@code ObservationService} to set
	 */
	@Autowired
	public void setObservationService(ObservationService observationService) {
		this.observationService = observationService;
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Lists <b>all</b> {@code Observation}s.
	 */
	@RequestMapping("/r")
	public ModelAndView handleAllAndOwn(HttpSession session,
			HttpServletResponse response) throws IOException {
		if (isAuth(session)) {
			if (isAdmin(session)) {
				return new ModelAndView(VIEW, REPORTS_ATTRIBUTE,
						this.observationService.getReports());
			} else {
				return new ModelAndView("redirect:/r/u/"
						+ getUser(session).getId());
			}
		}
		return forbiddenMAV(response);
	}

	/**
	 * Lists all {@code Report}s for a {@code Observation}.
	 */
	@RequestMapping("/r/o/{id}")
	public ModelAndView handleObservationFilter(@PathVariable Long id,
			HttpSession session, HttpServletResponse response)
			throws IOException {
		if (isAdmin(session)) {
			Observation o = this.observationService.getObservation(id);
			if (o == null) {
				return notFoundMAV(response);
			}
			return new ModelAndView(VIEW, REPORTS_ATTRIBUTE,
					this.observationService.getReportsByObservation(o));
		}
		return forbiddenMAV(response);
	}

	/**
	 * Lists all {@code Report}s made by a {@code User}.
	 */
	@RequestMapping("/r/u/{id}")
	public ModelAndView handleUserFilter(@PathVariable Long id,
			HttpSession session, HttpServletResponse response)
			throws IOException {
		if (isSameId(session, id)) {
			return new ModelAndView(VIEW, REPORTS_ATTRIBUTE,
					this.observationService
							.getObservationsByUser(getUser(session)));
		} else if (isAdmin(session)) {
			User u = userService.get(id);
			if (u == null) {
				return notFoundMAV(response);
			} else {
				return new ModelAndView(VIEW, REPORTS_ATTRIBUTE,
						this.observationService.getReportsByUser(u));
			}
		}
		return forbiddenMAV(response);
	}
}
