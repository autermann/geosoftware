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

import static org.sloth.util.ControllerUtils.forbiddenView;
import static org.sloth.util.ControllerUtils.isAdmin;
import static org.sloth.util.ControllerUtils.isAuth;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to test whether a {@code User} is logged in deliver the
 * appropriate. view.
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
public class AccountController {

	private final static String USER_VIEW = "account/user";
	private final static String ADMIN_VIEW = "account/admin";

	/**
	 * Handles any Request on "/acc" and returns the appropriate view.
	 */
	@RequestMapping("/acc")
	public String handle(HttpSession session, HttpServletResponse response)
			throws IOException {
		if (!isAuth(session)) {
			return forbiddenView(response);
		} else if (isAdmin(session)) {
			return ADMIN_VIEW;
		} else {
			return USER_VIEW;
		}
	}
}
