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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.User;
import org.sloth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @todo
 * @author auti
 */
@Controller
@RequestMapping("/users/{id}")
public class UserDetailController {

	/**
	 * @todo
	 */
	protected static final Logger logger = LoggerFactory.getLogger(
			UserDetailController.class);
	@Autowired
	private UserService userManager;

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
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String setupForm(@PathVariable Long id, Model model) {
		User u = getUserManager().get(id);
		logger.info("Request of Details for User {}", id);
		model.addAttribute("user", u);
		return "users/details";
	}

}
