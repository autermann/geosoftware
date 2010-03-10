/*
 * Copyright (C) 2009  Stefan Arndt, Christian Autermann, Dustin Demuth,
 * 					 Christoph Fendrich, Christian Paluschek
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

public interface UserManager {

	public void setUserDao(UserDao uDao);
	public void setUserRightDao(UserRightDao uDao);
	public Collection<User> getUsers();
	public User getUser(String mail);
	public User getUser(long id);
	public void updateUser(User u);
	public void deleteUser(long id);
	public void deleteUser(User user);
	public void registrateUser(String mail, String name, String familyName,
			String plainPassword);
	public UserRight getUserRight(int value);
	public UserRight createUserRight(int value, String title, String description);
	public void deleteUserRight(int value);
	public void deleteUserRight(UserRight ur);
	public void updateUserRight(UserRight ur);
}
