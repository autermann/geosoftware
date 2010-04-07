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
	private ObservationService os;
	private UserService us;

	/**
	 * @param os
	 *            the observationService to set
	 */
	@Autowired
	public void setObservationService(ObservationService os) {
		this.os = os;
	}

	/**
	 * @param us
	 *            the userService to set
	 */
	@Autowired
	public void setUserService(UserService us) {
		this.us = us;
	}

	@RequestMapping("/r")
	public ModelAndView handleAllAndOwn(HttpSession s, HttpServletResponse r)
			throws IOException {
		if (isAuth(s)) {
			if (isAdmin(s)) {
				return new ModelAndView(VIEW, REPORTS_ATTRIBUTE, this.os
						.getReports());
			} else {
				return new ModelAndView("redirect:/r/u/" + getUser(s).getId());
			}
		}
		return forbiddenMAV(r);
	}

	@RequestMapping("/r/o/{id}")
	public ModelAndView handleObservationFilter(@PathVariable Long id,
			HttpSession s, HttpServletResponse r) throws IOException {
		if (isAdmin(s)) {
			Observation o = this.os.getObservation(id);
			if (o == null) {
				return notFoundMAV(r);
			}
			return new ModelAndView(VIEW, REPORTS_ATTRIBUTE, this.os
					.getReportsByObservation(o));
		}
		return forbiddenMAV(r);
	}

	@RequestMapping("/r/u/{id}")
	public ModelAndView handleUserFilter(@PathVariable Long id, HttpSession s,
			HttpServletResponse r) throws IOException {
		if (isSameId(s, id)) {
			return new ModelAndView(VIEW, REPORTS_ATTRIBUTE, this.os
					.getObservationsByUser(getUser(s)));
		} else if (isAdmin(s)) {
			User u = us.get(id);
			if (u == null) {
				return notFoundMAV(r);
			} else {
				return new ModelAndView(VIEW, REPORTS_ATTRIBUTE, this.os
						.getReportsByUser(u));
			}
		}
		return forbiddenMAV(r);
	}
}
