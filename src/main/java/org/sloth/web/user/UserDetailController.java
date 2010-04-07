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
package org.sloth.web.user;

import static org.sloth.util.ControllerUtils.forbiddenMAV;
import static org.sloth.util.ControllerUtils.isAdmin;
import static org.sloth.util.ControllerUtils.notFoundMAV;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sloth.model.User;
import org.sloth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to display the datails of a {@code User}.
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
@RequestMapping("/u/{id}")
public class UserDetailController {

	private static final String VIEW = "users/details";
	private static final String USERS_ATTRIBUTE = "user";
	private UserService us;

	/**
	 * @param us
	 *            the {@code UserService} to set
	 */
	@Autowired
	public void setUserService(UserService us) {
		this.us = us;
	}

	/**
	 * Handles all requests and sets up the view.
	 */
	@RequestMapping
	public ModelAndView handleRequest(@PathVariable Long id, HttpSession s,
			HttpServletResponse r) throws IOException {
		if (isAdmin(s)) {
			User u = this.us.get(id);
			if (u == null) {
				return notFoundMAV(r);
			} else {
				return new ModelAndView(VIEW, USERS_ATTRIBUTE, u);
			}
		} else {
			return forbiddenMAV(r);
		}
	}
}
