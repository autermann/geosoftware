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

import org.sloth.model.User;
import org.sloth.service.PasswordManager;
import org.sloth.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @todo
 * @author auti
 */
@Controller
@RequestMapping("/")
public class LoginFormController {

	@Autowired
	private UserManager userManager;
	@Autowired
	private PasswordManager passwordManager;

	/**
	 * @todo
	 * @param passwordManager
	 */
	public void setPasswordManager(PasswordManager passwordManager) {
		this.passwordManager = passwordManager;
	}

	/**
	 * @todo
	 * @param userManager
	 */
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	/**
	 * @todo
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		model.addAttribute("user", new User());
		return "welcome";
	}

	/**
	 * @todo
	 * @param dataBinder
	 */
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setAllowedFields("eMail", "hashedPassword");
	}

	/**
	 * @todo
	 * @param model
	 * @param user
	 * @param result
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(Model model, @ModelAttribute User user,
								BindingResult result) {
		if (!StringUtils.hasText(user.getHashedPassword())) {
			result.rejectValue("hashedPassword", "field.required");
		}
		if (!StringUtils.hasText(user.geteMail())) {
			result.rejectValue("eMail", "field.required");
		}
		if (result.hasErrors()) {
			return "welcome";
		}
		User dbUser = userManager.getUser(user.geteMail());
		if (dbUser == null) {
			result.rejectValue("eMail", "field.noRegistratedUser");
		} else {
			String hashedPassword = passwordManager.hash(user.getHashedPassword());
			if (!passwordManager.test(hashedPassword, dbUser.getHashedPassword())) {
				result.rejectValue("hashedPassword", "field.wrongPassword");
			}
		}
		if (result.hasErrors()) {
			return "welcome";
		}
		model.addAttribute("loginUser", dbUser);
		return "welcome";
	}

}
