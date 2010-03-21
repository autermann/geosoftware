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
package org.sloth.web;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.User;
import org.sloth.service.Login;
import org.sloth.service.ObservationService;
import org.sloth.service.UserService;
import org.sloth.service.validator.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

//TODO JavaDoc
@Controller
@SessionAttributes(types = Login.class)
public class LoginFormController {

	private Logger logger = LoggerFactory.getLogger(LoginFormController.class);

	private UserService um;
	private ObservationService os;
	private LoginValidator lv;

	@Autowired
	public void setUserService(UserService um) {
		this.um = um;
	}
	
	@Autowired
	public void setObservationService(ObservationService os) {
		this.os = os;
	}

	@Autowired
	public void setLoginValidator(LoginValidator lv) {
		this.lv = lv;
	}

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String get(Model model) {
		if (model.containsAttribute("login")){
			logger.info("Returning the welcome page; using old login item.");
		} else {
			model.addAttribute("login", new Login());
			logger.info("Returning the welcome page; using new login item.");
		}
		model.addAttribute("map",os.getObservationsByCategories());
		return "welcome";
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setAllowedFields("mail", "password");
	}

	@RequestMapping(value="/", method = RequestMethod.POST)
	public String submit(@ModelAttribute Login login,
						 BindingResult result,
						 SessionStatus status,
						 HttpSession session) {
		lv.validate(login, result);
		if (result.hasErrors()) {
			logger.info("Login item has errors.");
			return "welcome";
		} else {
			logger.info("Login item seem to be ok. Testing the credentials.");
			User dbUser = um.login(login);
			if (dbUser == null) {
				logger.info("no matching user found or password was wrong.");
				result.reject("field.invalidLogin");
			} else {
				logger.info("Got valid user. Setting Session-Attribute.");
				session.setAttribute("loginUser", dbUser);
				status.setComplete();
			}
			return "welcome";
		}
	}


}
