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

import static org.sloth.util.ControllerUtils.deAuth;
import static org.sloth.util.ControllerUtils.forbiddenMAV;
import static org.sloth.util.ControllerUtils.forbiddenView;
import static org.sloth.util.ControllerUtils.getUser;
import static org.sloth.util.ControllerUtils.internalErrorView;
import static org.sloth.util.ControllerUtils.isAdmin;
import static org.sloth.util.ControllerUtils.isAuth;
import static org.sloth.util.ControllerUtils.notFoundMAV;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.User;
import org.sloth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to delete a {@code User}.
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
@Controller
@RequestMapping("/u/del/{id}")
public class DeleteUserController {

	private static final String VIEW = "users/delete";
	private static final String USER_ATTRIBUTE = "user";
	private static final Logger logger = LoggerFactory.getLogger(DeleteUserController.class);
	private UserService userService;

	/**
	 * @param userService
	 *            the {@code UserService} to set
	 */
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Sets custom parameters to the {@code WebDataBinder}.
	 * 
	 * @param webDataBinder
	 *            the {@code WebDataBinder} to initialize
	 */
	@InitBinder
	public void setAllowedFields(WebDataBinder webDataBinder) {
		webDataBinder.setAllowedFields();
	}

	/**
	 * Handles all {@code GET} requests. Sets up the form.
	 */
	@RequestMapping(method = GET)
	public ModelAndView setupForm(@PathVariable Long id, HttpSession session,
			HttpServletResponse response) throws IOException {
		if (isAuth(session)) {
			if (getUser(session).getId().equals(id)) {
				return new ModelAndView(VIEW, USER_ATTRIBUTE, getUser(session));
			} else if (isAdmin(session)) {
				User u = this.userService.get(id);
				if (u == null) {
					return notFoundMAV(response);
				} else {
					return new ModelAndView(VIEW, USER_ATTRIBUTE, u);
				}
			}
		}
		return forbiddenMAV(response);

	}

	/**
	 * Handles all {@code POST} requests and deletes a {@code User}.
	 */
	@RequestMapping(method = POST)
	public String processSubmit(@PathVariable Long id, HttpSession session,
			HttpServletResponse response, SessionStatus status)
			throws IOException {
		if (isAuth(session)) {
			boolean self = getUser(session).getId().equals(id);
			if (self || isAdmin(session)) {
				try {
					if (self) {
						deAuth(session);
						this.userService.delete(id);
						return "redirect:/";
					} else {
						this.userService.delete(id);
						return "redirect:/u";
					}
				} catch (Exception e) {
					logger.warn("Unexpected Exception", e);
					return internalErrorView(response);
				} finally {
					status.setComplete();
				}
			}
		}
		return forbiddenView(response);
	}
}
