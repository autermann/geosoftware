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
import org.sloth.exceptions.ConstraintViolationException;
import org.sloth.model.User;
import org.sloth.persistence.UserDao;
import org.sloth.service.Login;
import org.sloth.service.PasswordService;
import org.sloth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	protected static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);
	@Autowired
	private PasswordService passwordService;
	@Autowired
	private UserDao userDao;

	/**
	 * @todo
	 * @param userDao
	 * @throws NullPointerException
	 */
	public void setUserDao(UserDao userDao) throws NullPointerException {
		logger.info("Setting autowired UserDao");
		if (userDao == null)
			throw new NullPointerException();
		this.userDao = userDao;
	}

	/**
	 * @todo
	 * @param passwordService
	 * @throws NullPointerException
	 */
	public void setPasswordService(PasswordService passwordService)
			throws NullPointerException {
		logger.info("Setting autowired PasswordService");
		if (passwordService == null)
			throw new NullPointerException();
		this.passwordService = passwordService;
	}

	private PasswordService getPasswordService() {
		return passwordService;
	}

	private UserDao getUserDao() {
		return userDao;
	}

	@Override
	public Collection<User> getUsers() {
		return getUserDao().getAll();
	}

	@Override
	public User get(String mail) throws NullPointerException {
		if (mail == null)
			throw new NullPointerException();
		return getUserDao().getByMail(mail);
	}

	@Override
	public User get(Long id) throws NullPointerException {
		return getUserDao().getById(id);
	}

	@Override
	public void update(User u) throws NullPointerException,
			IllegalArgumentException, ConstraintViolationException {
		if (u == null)
			throw new NullPointerException();
		getUserDao().update(u);
	}

	@Override
	public void delete(Long id) throws NullPointerException,
			IllegalArgumentException {
		if (id == null)
			throw new NullPointerException();
		getUserDao().delete(getUserDao().getById(id));
	}

	@Override
	public void delete(User user) throws NullPointerException,
			IllegalArgumentException {
		if (user == null)
			throw new NullPointerException();
		getUserDao().delete(user);
	}

	@Override
	public void registrate(User user) throws NullPointerException,
			ConstraintViolationException {
		if (user == null)
			throw new NullPointerException();
		user.setPassword(getPasswordService().hash(user.getPassword()));
		logger
				.info(
						"Registrating User: ID: {}, Mail: {}, Name: {}, FamilyName: {}, Password: {}, Group: {}",
						new Object[] { user.getId(), user.getMail(),
								user.getName(), user.getFamilyName(),
								user.getPassword(), user.getUserGroup() });
		getUserDao().save(user);
	}

	@Override
	public User login(Login login) throws NullPointerException {
		if (login == null)
			throw new NullPointerException();
		User u = get(login.getMail());
		if (u != null
				&& getPasswordService().check(u.getPassword(),
						login.getPassword()))
			return u;
		else
			return null;
	}
}
