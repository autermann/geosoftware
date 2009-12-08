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

import org.sloth.data.Rights;
import org.sloth.data.User;
import org.sloth.exceptions.NotAuthorizedException;
import org.sloth.frontend.Session;
import java.util.Collection;

/**
 *
 * @author Christian Autermann
 */
public class UserManagement {

	/**
	 * 
	 * @param session
	 * @return
	 */
	public static UserManagement getInstance(Session session) {
		return null;
	}

	/**
	 * 
	 * @param u
	 * @throws NotAuthorizedException
	 */
	public void deleteUser(User u) throws NotAuthorizedException {
	}

	/**
	 *
	 */
	public void updateUser() {
	}

	/**
	 * 
	 * @return
	 * @throws NotAuthorizedException
	 */
	public Collection<User> getUserList() throws NotAuthorizedException {
		return null;
	}

	/**
	 *
	 * @param u
	 * @param rights
	 * @throws NotAuthorizedException
	 */
	public void changeRights(User u, Rights rights) throws NotAuthorizedException {
	}

	/**
	 *
	 * @param email
	 * @param passwd
	 * @return
	 */
	public boolean logIn(String email, String passwd) {
		return false;
	}

	/**
	 *
	 */
	public void logOut() {
	}

	/**
	 *
	 * @param email
	 * @param name
	 * @param familyName
	 * @param passwd
	 */
	public void registrateUser(String email, String name, String familyName, String passwd) {
	}
}
