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
import org.sloth.web.action.Login;
import org.sloth.service.UserService;
import org.sloth.validation.LoginValidator;
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
import static org.sloth.util.ControllerUtils.*;

@Controller
@RequestMapping("/login")
@SessionAttributes(types = Login.class)
public class LoginController {

	private static final String VIEW = "login";
	private static final String LOGIN_ATTRIBUTE = "login";
	private static final Logger logger = LoggerFactory.getLogger(
			LoginController.class);
	private UserService us;
	private LoginValidator lv;

	@Autowired
	public void setUserService(UserService us) {
		this.us = us;
	}

	@Autowired
	public void setLoginValidator(LoginValidator lv) {
		this.lv = lv;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder wdb) {
		wdb.setAllowedFields("mail", "password");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView prepare(HttpSession s) {
		if (isAuth(s)) {
			return new ModelAndView("redirect:/");
		} else {
			return new ModelAndView(VIEW, LOGIN_ATTRIBUTE, new Login());
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public String login(@ModelAttribute(LOGIN_ATTRIBUTE) Login l,
						BindingResult result, SessionStatus status,
						HttpSession s) {
		if (isAuth(s)) {
			return "redirect:/";
		} else {
			this.lv.validate(l, result);
			if (result.hasErrors()) {
				return VIEW;
			} else {
				User u = this.us.login(l.getMail(), l.getPassword());
				if (u == null) {
					result.reject("field.login.invalid");
					return VIEW;
				} else {
					logger.debug("Got valid user. Setting Session-Attribute.");
					auth(s, u);
					status.setComplete();
					return "redirect:/";
				}
			}
		}
	}

}
