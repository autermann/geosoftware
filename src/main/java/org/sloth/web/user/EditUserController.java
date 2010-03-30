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

import org.sloth.web.action.UserEditFormAction;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import static org.sloth.util.ControllerUtils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.validator.UserEditFormValidator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/u/edit/{id}")
@SessionAttributes(types = UserEditFormAction.class)
public class EditUserController {

	private static final String USER_ATTRIBUTE = "userEditAction";
	private static final String VIEW = "users/edit";
	private static final Logger logger = LoggerFactory.getLogger(
			EditUserController.class);
	private UserService us;
	private UserEditFormValidator uefv;

	@Autowired
	public void setUserValidator(UserEditFormValidator validator) {
		this.uefv = validator;
	}

	@Autowired
	public void setUserService(UserService userManager) {
		this.us = userManager;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setAllowedFields("newName", "newFamilyName", "newPassword",
									"newPasswordRepeat", "newMail", "newGroup",
									"actualPassword");
	}

	@RequestMapping(method = GET)
	public ModelAndView setupForm(@PathVariable Long id, HttpSession s,
								  HttpServletResponse r) throws IOException {
		if (isAuth(s)) {
			User u = null;
			if (isSameId(s, id)) {
				u = getUser(s);
			} else if (isAdmin(s)) {
				u = us.get(id);
				if (u == null) {
					return notFoundMAV(r);
				}
			} else {
				return forbiddenMAV(r);
			}
			return new ModelAndView(VIEW, USER_ATTRIBUTE, new UserEditFormAction(
					u, getUser(s)));
		} else {
			return forbiddenMAV(r);
		}
	}

	@RequestMapping(method = POST)
	public String processSubmit(HttpSession s, HttpServletResponse r,
								@ModelAttribute(USER_ATTRIBUTE) UserEditFormAction a,
								BindingResult result, SessionStatus status)
			throws IOException {
		if (isSameId(s, a.getId()) || isAdmin(s)) {
			uefv.validate(a, result);
			if (result.hasErrors()) {
				return VIEW;
			} else {
				try {
					us.update(a.getMergedUser());
				} catch(Exception e) {
					logger.warn("Unexpected Exception", e);
					return internalErrorView(r);
				} finally {
					status.setComplete();
				}
				if (isSameId(s, a.getId())) {
					return "redirect:/acc";
				} else {
					return "redirect:/u";
				}
			}
		}
		return forbiddenView(r);
	}

}
