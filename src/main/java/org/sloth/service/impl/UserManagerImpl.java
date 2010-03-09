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
package org.sloth.service.impl;

import java.util.Collection;
import java.util.Date;
import org.sloth.persistence.UserDao;
import org.sloth.model.User;
import org.sloth.service.PasswordManager;
import org.sloth.service.UserManager;

public class UserManagerImpl implements UserManager {

	PasswordManager pm;
	UserDao uDao;

	private UserDao getDAO(){
		return uDao;
	}

	private PasswordManager getPM(){
		return pm;
	}

	@Override
	public void registrateUser(String mail, String name, String familyName,
							   String plainPassword) {
		User u = new User();
		u.setFamilyName(familyName);
		u.seteMail(mail);
		u.setName(name);
		u.setHashedPassword(getPM().hash(plainPassword));
		getDAO().save(u);
	}

	@Override
	public void deleteUser(int id) {
		getDAO().delete(id);
	}

	@Override
	public Collection<User> getUsers() {
		return getDAO().getAll();
	}

	@Override
	public User getUser(String mail) {
		return getDAO().get(mail);
	}

	@Override
	public User getUser(int id) {
		return getDAO().get(id);
	}

	@Override
	public void updateUser(User u) {
		getDAO().update(u);	}

	@Override
	public void deleteUser(User user) {
		getDAO().delete(user);
	}

	@Override
	public void setUserDao(UserDao uDao) {
		this.uDao = uDao;
	}

	@Override
	public UserDao getUserDao() {
		return uDao;
	}
}
