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

import org.sloth.web.action.RegistrationFormAction;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.User;
import org.sloth.service.UserService;
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
import org.sloth.model.Group;
import org.sloth.validation.RegistrationFormValidator;
import static org.sloth.util.ControllerUtils.*;

@Controller
@RequestMapping("/signup")
@SessionAttributes(types = RegistrationFormAction.class)
public class RegistrationController {

	private static final String VIEW = "users/registration";
	private static final String USER_ATTRIBUTE = "user";
	private static final Logger logger = LoggerFactory.getLogger(
			RegistrationController.class);
	private UserService us;
	private RegistrationFormValidator rfv;

	@Autowired
	public void setUserValidator(
			RegistrationFormValidator rfv) {
		this.rfv = rfv;
	}

	@Autowired
	public void setUserService(UserService us) {
		this.us = us;
	}

	@InitBinder
	public void initBinder(WebDataBinder wdb) {
		wdb.setDisallowedFields("id", "creationDate", "userGroup");
	}

	@RequestMapping(method = GET)
	public ModelAndView prepare() {
		return new ModelAndView(VIEW, USER_ATTRIBUTE,
				new RegistrationFormAction());
	}

	@RequestMapping(method = POST)
	public String submit(HttpSession s, HttpServletResponse r,
			@ModelAttribute(USER_ATTRIBUTE) RegistrationFormAction a,
			BindingResult result, SessionStatus status) throws
			IOException {
		this.rfv.validate(a, result);
		if (result.hasErrors()) {
			return VIEW;
		} else {
			try {
				User u = a.createUser();
				u.setUserGroup(Group.USER);
				this.us.registrate(u);
				auth(s, u);
			} catch (Exception e) {
				logger.warn("Unexpected Exception", e);
				return internalErrorView(r);
			} finally {
				status.setComplete();
			}
			return "redirect:/";
		}
	}
}
