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
package org.sloth.web.util;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.Group;
import org.sloth.model.Observation;
import org.sloth.model.User;
import org.springframework.web.servlet.ModelAndView;

public class ControllerUtils {

	protected static Logger logger = LoggerFactory.getLogger(ControllerUtils.class);
	private static final String sessionAttribute = "loginUser";
	private static final String UNAUTHORIZED_VIEW = "error/unauthorized";
	private static final String NOT_FOUND_VIEW = "error/notfound";
	private static final String UNEXPECTED_ERROR_VIEW = "error/unexpected";

	public static ModelAndView unexpectedErrorView(Throwable t) {
		return new ModelAndView(UNEXPECTED_ERROR_VIEW, "throwable", t);
	}

	public static ModelAndView unexpectedErrorView() {
		return new ModelAndView(UNEXPECTED_ERROR_VIEW);
	}

	private ControllerUtils() {
	}

	public static Group getGroup(HttpSession s) {
		User u = getUser(s);
		if (u != null)
			return u.getUserGroup();
		else
			return null;
	}

	public static User getUser(HttpSession s) {
		Object o = s.getAttribute(sessionAttribute);
		if (o != null && o instanceof User)
			return ((User) o);
		else
			return null;
	}

	public static void auth(HttpSession s,
							User u) {
		if (u == null)
			throw new NullPointerException("User must not be null.");
		s.setAttribute(sessionAttribute, u);
	}

	public static void deAuth(HttpSession s) {
		User u = getUser(s);
		if (u != null)
			logger.debug("Unbinding User {} from Session {}", u.getId(), s.getId());
		s.invalidate();
	}

	public static boolean isAuth(HttpSession s) {
		return getUser(s) != null;
	}

	public static boolean isAdmin(HttpSession s) {
		return Group.ADMIN.equals(getGroup(s));
	}

	public static boolean isOwnObservation(HttpSession s,
										   Observation o) {
		return o.getUser().equals(getUser(s));
	}

	public static ModelAndView forbiddenMAV(HttpServletResponse r) throws
			IOException {
		return new ModelAndView(forbiddenView(r));
	}

	public static String forbiddenView(HttpServletResponse r) throws
			IOException {
		r.sendError(HttpServletResponse.SC_FORBIDDEN);
		return UNAUTHORIZED_VIEW;
	}

	public static String notFoundView(HttpServletResponse r) throws
			IOException {
		r.sendError(HttpServletResponse.SC_NOT_FOUND);
		return NOT_FOUND_VIEW;
	}

	public static ModelAndView notFountMAV(HttpServletResponse r) throws
			IOException {
		return new ModelAndView(notFoundView(r));
	}

	public static ModelAndView internalErrorMAV(HttpServletResponse r) throws
			IOException {
		return new ModelAndView(internalErrorView(r));
	}

	public static String internalErrorView(HttpServletResponse r) throws
			IOException {
		r.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return UNEXPECTED_ERROR_VIEW;
	}
}
