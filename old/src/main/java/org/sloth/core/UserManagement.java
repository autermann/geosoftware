/*
 * Copyright (C) 2009  Stefan Arndt, Christian Autermann, Dustin Demuth,
 *                     Christoph Fendrich, Christian Paluschek
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
package org.sloth.core;

import static org.sloth.data.User.*;
import org.sloth.data.User;
import org.sloth.exceptions.NotAuthorizedException;
import java.util.Collection;
import javax.servlet.http.HttpSession;
import org.sloth.io.db.DBBinding;

/**
 *
 * @author Christian Autermann
 */
public class UserManagement {

	private HttpSession session;

	/**
	 * 
	 * @param session
	 * @return 
	 */
	public static UserManagement getInstance(HttpSession session) {
		if (session == null) {
			throw new NullPointerException();
		}
		if (session.getAttribute("um") == null) {
			UserManagement um = new UserManagement();
			session.setAttribute("user", GUEST);
			session.setAttribute("um", um);
			um.setSession(session);
		}
		return (UserManagement) session.getAttribute("um");
	}

	private UserManagement() {
	}

	private void setSession(HttpSession session) {
		this.session = session;
	}

	private HttpSession getSession() {
		return session;
	}

	private User getUser(){
		return (User) getSession().getAttribute("user");
	}

	private void setUser(User u){
		getSession().setAttribute("user", u);
	}


	/**
	 * 
	 * @param u
	 * @throws NotAuthorizedException
	 */
	public void deleteUser(User u) throws NotAuthorizedException {
		boolean me = u.equals(getUser());
		if (me) {
			if (getUser().getRights() < STANDARD_RIGHTS) {
				throw new NotAuthorizedException();
			}
		} else if (getUser().getRights() < ADMIN_RIGHTS) {
			throw new NotAuthorizedException();
		}
		DBBinding.getInstance().deleteUser(u);
		if (me) {
			setUser(User.GUEST);
		}
	}

	/**
	 *
	 *
	 * @param u
	 * @throws NotAuthorizedException
	 */
	public void updateUser(User u) throws NotAuthorizedException {
		boolean me = u.equals(getUser());
		if (me) {
			if (getUser().getRights() < STANDARD_RIGHTS) {
				throw new NotAuthorizedException();
			}
		} else if (getUser().getRights() < ADMIN_RIGHTS) {
			throw new NotAuthorizedException();
		}
		DBBinding.getInstance().updateUser(u);
	}

	/**
	 * 
	 * @return
	 * @throws NotAuthorizedException
	 */
	public Collection<User> getUserList() throws NotAuthorizedException {
		if (getUser().getRights() < ADMIN_RIGHTS) {
			throw new NotAuthorizedException();
		}
		return DBBinding.getInstance().getUserList();
	}

	/**
	 *
	 * @param u
	 * @param rights
	 * @throws NotAuthorizedException
	 */
	public void changeRights(User u, int rights) throws
			NotAuthorizedException {
		if (getUser().getRights() < ADMIN_RIGHTS) {
			throw new NotAuthorizedException();
		}
		getUser().setRights(rights);
		updateUser(u);
	}

	/**
	 *
	 * @param email
	 * @param passwd
	 * @throws NotAuthorizedException 
	 */
	public void logIn(String email, String passwd) throws
			NotAuthorizedException {
		User u = DBBinding.getInstance().getUserByEmail(email);
		if (u == null) {
			throw new NotAuthorizedException("wrong email");
		}
		if (!u.testPassword(passwd)) {
			throw new NotAuthorizedException("wrong password");
		}
		setUser(u);
	}

	/**
	 *
	 */
	public void logOut() {
		setUser(User.GUEST);
	}

	/**
	 *
	 * @param email
	 * @param name
	 * @param familyName
	 * @param passwd
	 * @throws Exception 
	 */
	public void registrateUser(String email, String name, String familyName, String passwd) throws Exception {
		if (DBBinding.getInstance().getUserByEmail(email) == null) {
			User u = new User();
			u.setFamilyName(familyName);
			u.setName(name);
			u.seteMail(email);
			u.setPasswort(passwd);
			DBBinding.getInstance().addUser(u);
			setUser(u);
		} else {
			throw new Exception("email not available");
		}
	}
}
