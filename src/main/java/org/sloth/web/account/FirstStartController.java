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

import static org.sloth.util.ControllerUtils.auth;
import static org.sloth.util.ControllerUtils.forbiddenMAV;
import static org.sloth.util.ControllerUtils.forbiddenView;
import static org.sloth.util.ControllerUtils.internalErrorView;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.Group;
import org.sloth.model.User;
import org.sloth.service.UserService;
import org.sloth.validation.RegistrationFormValidator;
import org.sloth.web.action.RegistrationFormAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
/**
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 *
 */
@Controller
@RequestMapping("/firstStart")
@SessionAttributes(types = RegistrationFormAction.class)
public class FirstStartController {

	private static final String VIEW = "users/registration";
	private static final String USER_ATTRIBUTE = "user";
	private static final Logger logger = LoggerFactory
			.getLogger(RegistrationController.class);
	private UserService userService;
	private RegistrationFormValidator rfv;

	@Autowired
	public void setUserValidator(RegistrationFormValidator rfv) {
		this.rfv = rfv;
	}

	@Autowired
	public void setUserService(UserService us) {
		this.userService = us;
	}

	@InitBinder
	public void initBinder(WebDataBinder wdb) {
		wdb.setDisallowedFields("id", "creationDate", "userGroup");
	}

	@RequestMapping(method = GET)
	public ModelAndView prepare(HttpServletResponse r) throws IOException {
		if (this.isFirstStart()) {
			return new ModelAndView(VIEW, USER_ATTRIBUTE,
					new RegistrationFormAction());
		} else {
			return forbiddenMAV(r);
		}
	}

	@RequestMapping(method = POST)
	public String submit(
			@ModelAttribute(USER_ATTRIBUTE) RegistrationFormAction a,
			BindingResult result, SessionStatus status, HttpSession s,
			HttpServletResponse r) throws IOException {
		if (isFirstStart()) {
			this.rfv.validate(a, result);
			if (result.hasErrors()) {
				return VIEW;
			} else {
				try {
					User u = a.createUser();
					u.setUserGroup(Group.ADMIN);
					this.userService.registrate(u);
					auth(s, u);
				} catch (Exception e) {
					logger.warn("Unexpected Exception", e);
					return internalErrorView(r);
				} finally {
					status.setComplete();
				}
				return "redirect:/";
			}
		} else {
			return forbiddenView(r);
		}

	}

	private boolean isFirstStart() {
		return userService.getUsers().size() == 0;
	}
}
