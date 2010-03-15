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

import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.User;
import org.sloth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 *
 * @todo
 * @author auti
 */
@Controller
@RequestMapping("/users")
public class UserListController {

	/**
	 *
	 * @todo
	 */
	protected final static Logger logger = LoggerFactory.getLogger(
			UserListController.class);
	@Autowired
	private UserService userManager;

	/**
	 *
	 * @todo
	 * @param userManager
	 */
	public void setUserManager(UserService userManager) {
		this.userManager = userManager;
	}

	/**
	 *
	 * @todo
	 * @return
	 */
	protected UserService getUserManager() {
		return this.userManager;
	}

	/**
	 *
	 * @todo
	 * @param model
	 * @return
	 */
	@RequestMapping(method = GET)
	public ModelAndView setupList() {
		Collection<User> users = getUserManager().getUsers();
		logger.info("Request for User List; got {} entrys.", users.size());
		return new ModelAndView("users/list", "users", users);
	}

}


