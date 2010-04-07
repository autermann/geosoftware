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

import static org.sloth.util.ControllerUtils.forbiddenView;
import static org.sloth.util.ControllerUtils.getUser;
import static org.sloth.util.ControllerUtils.isAuth;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class SelfRedirectController {

	@RequestMapping("/u/edit")
	public String handleEditRequest(HttpSession s, HttpServletResponse r)
			throws IOException {
		if (isAuth(s)) {
			return "redirect:/u/edit/" + getUser(s).getId();
		} else {
			return forbiddenView(r);
		}
	}

	@RequestMapping("/u/del")
	public String handleDeleteRequest(HttpSession s, HttpServletResponse r)
			throws IOException {
		if (isAuth(s)) {
			return "redirect:/u/del/" + getUser(s).getId();
		} else {
			return forbiddenView(r);
		}
	}
}
