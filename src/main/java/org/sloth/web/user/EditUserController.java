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
import static org.sloth.util.ControllerUtils.forbiddenView;
import static org.sloth.util.ControllerUtils.getUser;
import static org.sloth.util.ControllerUtils.internalErrorView;
import static org.sloth.util.ControllerUtils.isAdmin;
import static org.sloth.util.ControllerUtils.isAuth;
import static org.sloth.util.ControllerUtils.isSameId;
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
import org.sloth.validation.UserEditFormValidator;
import org.sloth.web.action.UserEditFormAction;
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
 * Controller to edit a {@code User}.
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
@RequestMapping("/u/edit/{id}")
@SessionAttributes(types = UserEditFormAction.class)
public class EditUserController {

	private static final String USER_ATTRIBUTE = "userEditAction";
	private static final String VIEW = "users/edit";
	private static final Logger logger = LoggerFactory
			.getLogger(EditUserController.class);
	private UserService userService;
	private UserEditFormValidator userEditFormValidator;

	/**
	 * @param userEditFormValidator
	 *            the {@code UserEditFormValidator} to set
	 */
	@Autowired
	public void setUserEditFormValidator(
			UserEditFormValidator userEditFormValidator) {
		this.userEditFormValidator = userEditFormValidator;
	}

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
		webDataBinder.setAllowedFields("newName", "newFamilyName",
				"newPassword", "newPasswordRepeat", "newMail", "newGroup",
				"actualPassword");
	}

	/**
	 * Handles all {@code GET} requests and sets up the form.
	 */
	@RequestMapping(method = GET)
	public ModelAndView setupForm(@PathVariable Long id, HttpSession session,
			HttpServletResponse r) throws IOException {
		if (isAuth(session)) {
			User u = null;
			if (isSameId(session, id)) {
				u = getUser(session);
			} else if (isAdmin(session)) {
				u = this.userService.get(id);
				if (u == null) {
					return notFoundMAV(r);
				}
			} else {
				return forbiddenMAV(r);
			}
			return new ModelAndView(VIEW, USER_ATTRIBUTE,
					new UserEditFormAction(u, getUser(session)));
		} else {
			return forbiddenMAV(r);
		}
	}

	/**
	 * Handles all {@code POST} requests and saves the changes made to the
	 * {@code User}.
	 */
	@RequestMapping(method = POST)
	public String processSubmit(HttpSession session,
			HttpServletResponse response,
			@ModelAttribute(USER_ATTRIBUTE) UserEditFormAction action,
			BindingResult result, SessionStatus status) throws IOException {
		if (isSameId(session, action.getId()) || isAdmin(session)) {
			this.userEditFormValidator.validate(action, result);
			if (result.hasErrors()) {
				return VIEW;
			} else {
				try {
					this.userService.update(action.getMergedUser());
				} catch (Exception e) {
					logger.warn("Unexpected Exception", e);
					return internalErrorView(response);
				} finally {
					status.setComplete();
				}
				if (isSameId(session, action.getId())) {
					return "redirect:/acc";
				} else {
					return "redirect:/u";
				}
			}
		}
		return forbiddenView(response);
	}
}
