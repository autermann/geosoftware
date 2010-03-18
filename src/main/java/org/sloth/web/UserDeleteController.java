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


import org.sloth.model.User;
import org.sloth.service.UserService;
import org.sloth.service.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
@RequestMapping("/users/delete/{id}")
@SessionAttributes(types = User.class)
public class UserDeleteController {

    protected static final Logger logger = LoggerFactory.getLogger(
			UserDeleteController.class);

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


	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setAllowedFields();
	}

        @RequestMapping(method = GET)
	public String setupForm(@PathVariable Long id, Model model) {
                User user = getUserManager().get(id);
                logger.info("Edit of Details for User {}", id);
		model.addAttribute("user", user);
		return "users/delete";
	}
      
        



	@RequestMapping(method = POST)
	public String processSubmit(@ModelAttribute User user, BindingResult result,
								SessionStatus status) {
		getUserValidator().validate(user, result);
		if (user == null) {
			throw new NullPointerException();
		}
		if (result.hasErrors()) {
			return "redirect:/users";
		} else {
			this.userManager.delete(user);
			status.setComplete();
			return "redirect:/users";
		}
	}


}
