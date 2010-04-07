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
package org.sloth.util;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.Group;
import org.sloth.model.Observation;
import org.sloth.model.Report;
import org.sloth.model.User;
import org.springframework.web.servlet.ModelAndView;

/**
 * Utility class for Controller.
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
public class ControllerUtils {

	private static Logger logger = LoggerFactory
			.getLogger(ControllerUtils.class);
	private static final String SESSION_ATTRIBUTE = "LOGIN";
	private static final String UNAUTHORIZED_VIEW = "error/unauthorized";
	private static final String NOT_FOUND_VIEW = "error/notfound";
	private static final String UNEXPECTED_ERROR_VIEW = "error/unexpected";

	/**
	 * Authorizes the {@code HttpSession} with the specified {@code User}.
	 * 
	 * @param s
	 *            the {@code HttpSession}
	 * @param u
	 *            the {@code User}
	 */
	public static void auth(HttpSession s, User u) {
		if (u == null) {
			throw new NullPointerException("User must not be null.");
		}
		s.setAttribute(SESSION_ATTRIBUTE, u);
	}

	/**
	 * De-Authorizes the {@code HttpSession}.
	 * 
	 * @param s
	 *            the {@code HttpSession}
	 */
	public static void deAuth(HttpSession s) {
		User u = getUser(s);
		if (u != null) {
			logger.debug("Unbinding User {} from Session {}", u.getId(), s
					.getId());
		}
		s.invalidate();
	}

	/**
	 * This is a convenient method to send an 403-error to the client.
	 * 
	 * @param r
	 *            the {@code HttpServletResponse}
	 * @return the {@code ModelAndView}
	 * @throws IOException
	 *             If an input or output exception occurs
	 */
	public static ModelAndView forbiddenMAV(HttpServletResponse r)
			throws IOException {
		return new ModelAndView(forbiddenView(r));
	}

	/**
	 * This is a convenient method to send an 403-error to the client.
	 * 
	 * @param r
	 *            the {@code HttpServletResponse}
	 * @return the view
	 * @throws IOException
	 *             If an input or output exception occurs
	 */
	public static String forbiddenView(HttpServletResponse r)
			throws IOException {
		r.sendError(SC_FORBIDDEN);
		return UNAUTHORIZED_VIEW;
	}

	/**
	 * Gets the {@code Group} from the {@code User} in the specified {@code
	 * HttpSession}
	 * 
	 * @param s
	 *            the session
	 * @return the {@code Group} or {@code null} if {@code s} is not authorized
	 */
	public static Group getGroup(HttpSession s) {
		User u = getUser(s);
		if (u != null) {
			return u.getUserGroup();
		} else {
			return null;
		}
	}

	/**
	 * Gets the {@code User} in the specified {@code HttpSession}
	 * 
	 * @param s
	 *            the session
	 * @return the {@code User} or {@code null} if {@code s} is not authorized
	 */
	public static User getUser(HttpSession s) {
		Object o = s.getAttribute(SESSION_ATTRIBUTE);
		if (o != null && o instanceof User) {
			return (User) o;
		} else {
			return null;
		}
	}

	/**
	 * This is a convenient method to send an 500-error to the client.
	 * 
	 * @param r
	 *            the {@code HttpServletResponse}
	 * @return the {@code ModelAndView}
	 * @throws IOException
	 *             If an input or output exception occurs
	 */
	public static ModelAndView internalErrorMAV(HttpServletResponse r)
			throws IOException {
		return new ModelAndView(internalErrorView(r));
	}

	/**
	 * This is a convenient method to send an 500-error to the client.
	 * 
	 * @param r
	 *            the {@code HttpServletResponse}
	 * @return the view
	 * @throws IOException
	 *             If an input or output exception occurs
	 */
	public static String internalErrorView(HttpServletResponse r)
			throws IOException {
		r.sendError(SC_INTERNAL_SERVER_ERROR);
		return UNEXPECTED_ERROR_VIEW;
	}

	/**
	 * Returns whether the {@code HttpSession} is authorized by an {@code User}
	 * which is in the {@code Group} {@code Group.ADMIN}.
	 * 
	 * @param s
	 *            the session
	 * @return {@code true} if it is an admin or {@code false} if {@code s} is
	 *         not authorized or authorized by a normal {@code User}
	 */
	public static boolean isAdmin(HttpSession s) {
		return Group.ADMIN.equals(getGroup(s));
	}

	/**
	 * Returns whether {@code s} is authorized by a {@code User}.
	 * 
	 * @param s
	 *            the {@code HttpSession}
	 * @return {@code true} if {@code s} is authorized and otherwise {@code
	 *         false}
	 */
	public static boolean isAuth(HttpSession s) {
		return getUser(s) != null;
	}

	/**
	 * Returns if the {@code User} who has authorized {@code s} is the creator
	 * of {@code o}.
	 * 
	 * @param s
	 *            the {@code HttpSession}
	 * @param o
	 *            the {@code Observation}
	 * @return {@code true} if it is the creator and {@code false} if it is not
	 *         or if {@code s} is not authorized.
	 */
	public static boolean isOwnObservation(HttpSession s, Observation o) {
		User u = getUser(s);
		if (u != null) {
			return u.getId().equals(o.getUser().getId());
		}
		return false;
	}

	/**
	 * Returns if the {@code User} who has authorized {@code s} is the author of
	 * {@code r}.
	 * 
	 * @param s
	 *            the {@code HttpSession}
	 * @param r
	 *            the {@code Report}
	 * @return {@code true} if it is the author and {@code false} if it is not
	 *         or if {@code s} is not authorized.
	 */
	public static boolean isOwnReport(HttpSession s, Report r) {
		User u = getUser(s);
		if (u != null) {
			return u.getId().equals(r.getAuthor().getId());
		}
		return false;
	}

	/**
	 * Checks whether the {@code User} who has authorized {@code s} has the
	 * specified {@code id}.
	 * 
	 * @param s
	 *            the {@code HttpSession}
	 * @param id
	 *            the {@code id}
	 * @return {@code true} if it is the same {@code id} and otherwise {@code
	 *         false}
	 */
	public static boolean isSameId(HttpSession s, Long id) {
		User u = getUser(s);
		if (u != null) {
			return id.equals(u.getId());
		} else {
			return false;
		}
	}

	/**
	 * This is a convenient method to send an 404-error to the client.
	 * 
	 * @param r
	 *            the {@code HttpServletResponse}
	 * @return the {@code ModelAndView}
	 * @throws IOException
	 *             If an input or output exception occurs
	 */
	public static ModelAndView notFoundMAV(HttpServletResponse r)
			throws IOException {
		return new ModelAndView(notFoundView(r));
	}

	/**
	 * This is a convenient method to send an 404-error to the client.
	 * 
	 * @param r
	 *            the {@code HttpServletResponse}
	 * @return the view
	 * @throws IOException
	 *             If an input or output exception occurs
	 */
	public static String notFoundView(HttpServletResponse r) throws IOException {
		r.sendError(SC_NOT_FOUND);
		return NOT_FOUND_VIEW;
	}

	private ControllerUtils() {
	}
}
