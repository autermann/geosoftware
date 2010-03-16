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
package org.sloth.web;

import org.sloth.model.Group;
import org.sloth.model.User;
import org.sloth.service.UserService;
import org.sloth.service.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * @todo
 * @author auti
 */
@Controller
@RequestMapping("/users/new")
@SessionAttributes(types = User.class)
public class UserAddController {

	@Autowired
	private UserService userManager;
	@Autowired
	private UserValidator userValidator;

	/**
	 * @todo
	 * @param userValidator
	 */
	public void setUserValidator(UserValidator userValidator) {
		this.userValidator = userValidator;
	}

	/**
	 * @todo
	 * @return
	 */
	protected UserValidator getUserValidator() {
		return this.userValidator;
	}

	/**
	 * @todo
	 * @param userManager
	 */
	public void setUserManager(UserService userManager) {
		this.userManager = userManager;
	}

	/**
	 * @todo
	 * @return
	 */
	protected UserService getUserManager() {
		return this.userManager;
	}

	/**
	 * @todo
	 * @param dataBinder
	 */
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id", "creationDate", "userRight");
	}

	/**
	 * @param model
	 * @return
	 * @todo
	 * Beim Aufruf der Seite wird die GET-Methoder aufgerufen...
	 */
	@RequestMapping(method = GET)
	public String setupForm(Model model) {
		User user = new User();
		model.addAttribute(user);
		return "users/form";
	}

	/**
	 * @param user
	 * @param result
	 * @param status
	 * @return
	 * @todo
	 * Beim Absenden des Formulars die POST-Methode...
	 */
	@RequestMapping(method = POST)
	public String processSubmit(@ModelAttribute User user, BindingResult result,
								SessionStatus status) {
		getUserValidator().validate(user, result);
		if (result.hasErrors()) {
			return "users/form";
		} else {
			user.setUserGroup(Group.USER);
			this.userManager.registrate(user);
			status.setComplete();
			return "redirect:/";
		}
	}

}
