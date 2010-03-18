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
package org.sloth.service.impl;

import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.User;
import org.sloth.persistence.UserDao;
import org.sloth.service.Login;
import org.sloth.service.PasswordService;
import org.sloth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	protected static final Logger logger = LoggerFactory.getLogger(
			UserServiceImpl.class);
	@Autowired
	private PasswordService passwordService;
	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		logger.info("Setting autowired UserDao");
		this.userDao = userDao;
	}

	private PasswordService getPasswordService() {
		return passwordService;
	}

	private UserDao getUserDao() {
		return userDao;
	}

	public void setPasswordService(PasswordService passwordService) {
		this.passwordService = passwordService;
	}

	@Override
	public Collection<User> getUsers() {
		return getUserDao().getAll();
	}

	@Override
	public User get(String mail) {
		return getUserDao().get(mail);
	}

	@Override
	public User get(Long id) {
		return getUserDao().get(id);
	}

	@Override
	public void update(User u) {
		getUserDao().update(u);
	}

	@Override
	public void delete(Long id) {
		getUserDao().delete(getUserDao().get(id));
	}

	@Override
	public void delete(User user) {
		getUserDao().delete(user);
	}

	@Override
	public void registrate(User u) {
		u.setPassword(getPasswordService().hash(u.getPassword()));
		logger.info(
				"Registrating User: ID: {}, Mail: {}, Name: {}, FamilyName: {}, Password: {}, Group: {}", new Object[]{
					u.getId(),
					u.getMail(),
					u.getName(),
					u.getFamilyName(),
					u.getPassword(),
					u.getUserGroup()
				});
		getUserDao().save(u);
	}

	@Override
	public User login(Login login) {
		User u = get(login.getMail());
		if (u != null && getPasswordService().check(u.getPassword(), login.getPassword())) {
				return u;
		} else {
			return null;
		}
	}

}
