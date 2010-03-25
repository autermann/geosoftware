/*
 * Copyright (C) 2009  Stefan Arndt, Christian Autermann, Dustin Demuth,
 * 					 Christoph Fendrich, Christian Paluschek
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

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.User;
import org.sloth.service.Login;
import org.sloth.service.UserService;
import org.sloth.service.validator.LoginValidator;
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
import static org.sloth.web.util.ControllerUtils.*;

@Controller
@RequestMapping("/login")
@SessionAttributes(types = Login.class)
public class LoginController {

	private static final String view = "login";
	private static final String modelAttribute = "login";
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private UserService userService;
	private LoginValidator validator;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setLoginValidator(LoginValidator loginValidator) {
		this.validator = loginValidator;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setAllowedFields("mail", "password");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView prepare(HttpSession s) {
		if (isAuth(s))
			return new ModelAndView("redirect:/");
		return new ModelAndView(view, modelAttribute, new Login());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String login(@ModelAttribute(modelAttribute) Login login,
						BindingResult result,
						SessionStatus status,
						HttpSession s) {
		if (isAuth(s))
			return "redirect:/";
		validator.validate(login, result);
		if (result.hasErrors())
			return view;
		else {
			User u = userService.login(login);
			if (u == null) {
				result.reject("field.invalidLogin");
				return view;
			} else {
				logger.debug("Got valid user. Setting Session-Attribute.");
				auth(s, u);
				return "redirect:/";
			}
		}
	}
}
