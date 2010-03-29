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
package org.sloth.web.user;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.sloth.model.User;
import org.sloth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import static org.sloth.util.ControllerUtils.*;

@Controller
@RequestMapping("/u/del/{id}")
public class DeleteUserController {

	private static final String VIEW = "users/delete";
	private static final String USER_ATTRIBUTE = "user";
	private static final Logger logger = LoggerFactory.getLogger(
			DeleteUserController.class);
	private UserService userManager;

	@Autowired
	public void setUserManager(UserService userManager) {
		this.userManager = userManager;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setAllowedFields();
	}

	@RequestMapping(method = GET)
	public ModelAndView setupForm(@PathVariable Long id, HttpSession s,
								  HttpServletResponse r) throws IOException {
		if (isAuth(s)) {
			if (getUser(s).getId().equals(id)) {
				return new ModelAndView(VIEW, USER_ATTRIBUTE, getUser(s));
			} else if (isAdmin(s)) {
				User u = userManager.get(id);
				if (u == null) {
					return notFoundMAV(r);
				} else {
					return new ModelAndView(VIEW, USER_ATTRIBUTE, u);
				}
			}
		}
		return forbiddenMAV(r);

	}

	@RequestMapping(method = POST)
	public String processSubmit(@PathVariable Long id, HttpSession s,
								HttpServletResponse r) throws IOException {
		if (isAuth(s)) {
			boolean self = getUser(s).getId().equals(id);
			if (self || isAdmin(s)) {
				try {
					userManager.delete(id);
					if (self) {
						deAuth(s);
						return "redirect:/";
					} else {
						userManager.delete(id);
						return "redirect:/u";
					}

				} catch(Exception e) {
					logger.warn("Unexpected Exception", e);
					return internalErrorView(r);
				}
			}
		}
		return forbiddenView(r);
	}

}
