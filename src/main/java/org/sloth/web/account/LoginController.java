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
package org.sloth.web.account;

import static org.sloth.util.ControllerUtils.auth;
import static org.sloth.util.ControllerUtils.isAuth;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.User;
import org.sloth.service.UserService;
import org.sloth.validation.LoginValidator;
import org.sloth.web.action.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to let {@code User}s login.
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
@RequestMapping("/login")
@SessionAttributes(types = Login.class)
public class LoginController {

	private static final String VIEW = "login";
	private static final String LOGIN_ATTRIBUTE = "login";
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	private UserService userService;
	private LoginValidator loginValidator;

	/**
	 * @param userService
	 *            the {@code UserService} to set
	 */
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 
	 * @param loginValidator
	 *            the {@code LoginValidator} to set
	 */
	@Autowired
	public void setLoginValidator(LoginValidator loginValidator) {
		this.loginValidator = loginValidator;
	}

	/**
	 * Sets custom parameters to the {@code WebDataBinder}.
	 * 
	 * @param webDataBinder
	 *            the {@code WebDataBinder} to initialize
	 */
	@InitBinder
	public void setAllowedFields(WebDataBinder webDataBinder) {
		webDataBinder.setAllowedFields("mail", "password");
	}

	/**
	 * Handles the {@code GET} request and sets up the login form.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView prepare(HttpSession session) {
		if (isAuth(session)) {
			return new ModelAndView("redirect:/");
		} else {
			return new ModelAndView(VIEW, LOGIN_ATTRIBUTE, new Login());
		}
	}

	/**
	 * Handles the {@code POST} request. Validates the login form and authorizes
	 * the {@code HttpSession}.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String login(@ModelAttribute(LOGIN_ATTRIBUTE) Login login,
			BindingResult result, SessionStatus status, HttpSession session) {
		if (isAuth(session)) {
			return "redirect:/";
		} else {
			this.loginValidator.validate(login, result);
			if (result.hasErrors()) {
				return VIEW;
			} else {
				User u = this.userService.login(login.getMail(), login
						.getPassword());
				if (u == null) {
					result.reject("field.login.invalid");
					return VIEW;
				} else {
					logger.debug("Got valid user. Setting Session-Attribute.");
					auth(session, u);
					status.setComplete();
					return "redirect:/";
				}
			}
		}
	}

}
