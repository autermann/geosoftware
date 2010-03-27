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

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.Group;
import org.sloth.model.User;
import org.sloth.service.UserService;
import org.sloth.service.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import static org.sloth.web.util.ControllerUtils.*;

@Controller
@RequestMapping("/signup")
@SessionAttributes(types = RegistrationFormAction.class)
public class RegistrationController {

	private static final String view = "users/registration";
	private static final String modelAttribute = "user";
	private Logger logger = LoggerFactory
			.getLogger(RegistrationController.class);
	private UserService userManager;
	private UserValidator userValidator;

	@Autowired
	public void setUserValidator(UserValidator userValidator) {
		this.userValidator = userValidator;
	}

	@Autowired
	public void setUserManager(UserService userManager) {
		this.userManager = userManager;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id", "creationDate", "userGroup");
	}

	@RequestMapping(method = GET)
	public ModelAndView prepare() {
		return new ModelAndView(view, modelAttribute,
				new RegistrationFormAction());
	}

	@RequestMapping(method = POST)
	public String submit(
			@ModelAttribute(modelAttribute) RegistrationFormAction action,
			BindingResult result, SessionStatus status, HttpSession s,
			HttpServletResponse r) throws IOException {
		User u = userValidator.validate(action, result);
		if (result.hasErrors())
			return view;
		else {
			try {

				userManager.registrate(u);
				auth(s, u);
				status.setComplete();
				return "redirect:/";
			} catch (Exception e) {
				logger.warn("Unexpected Exception", e);
				return internalErrorView(r);
			}
		}
	}
}
