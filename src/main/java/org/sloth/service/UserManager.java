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
package org.sloth.service;

import java.util.Collection;
import org.sloth.model.User;
import org.sloth.model.UserRight;
import org.sloth.persistence.UserDao;
import org.sloth.persistence.UserRightDao;

/**
 * @todo
 * @author auti
 */
public interface UserManager {

	/**
	 * @todo
	 * @param uDao
	 */
	public void setUserDao(UserDao uDao);

	/**
	 * @todo
	 * @param uDao
	 */
	public void setUserRightDao(UserRightDao uDao);

	/**
	 * @todo
	 * @return
	 */
	public Collection<User> getUsers();

	/**
	 * @todo
	 * @param mail
	 * @return
	 */
	public User getUser(String mail);

	/**
	 * @todo
	 * @param id
	 * @return
	 */
	public User getUser(long id);

	/**
	 * @todo
	 * @param u
	 */
	public void updateUser(User u);

	/**
	 * @todo
	 * @param id
	 */
	public void deleteUser(long id);

	/**
	 * @todo
	 * @param user
	 */
	public void deleteUser(User user);

	/**
	 * @todo
	 * @param u
	 */
	public void registrateUser(User u);

	/**
	 * @todo
	 * @param value
	 * @return
	 */
	public UserRight getUserRight(int value);

	/**
	 * @todo
	 * @param value
	 * @param title
	 * @param description
	 * @return
	 */
	public UserRight createUserRight(int value, String title, String description);

	/**
	 * @todo
	 * @param value
	 */
	public void deleteUserRight(int value);

	/**
	 * @todo
	 * @param ur
	 */
	public void deleteUserRight(UserRight ur);

	/**
	 * @todo
	 * @param ur
	 */
	public void updateUserRight(UserRight ur);

}
