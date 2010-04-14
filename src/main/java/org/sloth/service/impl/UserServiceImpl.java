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
import org.sloth.exception.ConstraintViolationException;
import org.sloth.model.User;
import org.sloth.persistence.UserDao;
import org.sloth.service.PasswordService;
import org.sloth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implemtation of {@link UserService}.
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);
	@Autowired
	private PasswordService passwordService;
	@Autowired
	private UserDao userDao;

	@Override
	public void delete(Long id) throws NullPointerException,
			IllegalArgumentException {
		if (id == null) {
			throw new NullPointerException();
		} else {
			logger.info("Deleting User with Id {}", id);
			this.delete(this.get(id));
		}
	}

	@Override
	public void delete(User user) throws NullPointerException,
			IllegalArgumentException {
		if (user == null) {
			throw new NullPointerException();
		} else {
			logger.info("Deleting User {}", user);
			this.userDao.delete(user);
		}
	}

	@Override
	public User get(Long id) throws NullPointerException {
		if (id == null) {
			throw new NullPointerException();
		} else {
			logger.info("Getting User with Id {}", id);
			return this.userDao.getById(id);
		}
	}

	@Override
	public User get(String mail) throws NullPointerException {
		if (mail == null) {
			throw new NullPointerException();
		} else {
			logger.info("Getting User with mail address '{}'", mail);
			return this.userDao.getByMail(mail);
		}
	}

	@Override
	public Collection<User> getUsers() {
		logger.info("Getting all Users");
		return this.userDao.getAll();
	}

	@Override
	public boolean isMailAddressAvailable(String mail)
			throws NullPointerException {
		logger.info("Testing whether mail address '{}' is available.", mail);
		return this.get(mail) == null;
	}

	@Override
	public User login(String mail, String plainPassword)
			throws NullPointerException {
		if (mail == null || plainPassword == null) {
			throw new NullPointerException();
		} else {
			logger.info("Logging in User with mail address {}", mail);
			User u = this.get(mail);
			if (u == null) {
				logger.info("Unknown login");
				return null;
			} else {
				logger.info("User found: {}", u);
				return this.passwordService.check(u.getPassword(),
						plainPassword) ? u : null;
			}
		}
	}

	@Override
	public void registrate(User user) throws NullPointerException,
			ConstraintViolationException {
		if (user == null) {
			throw new NullPointerException();
		} else {
			user.setPassword(this.passwordService.hash(user.getPassword()));
			logger.info(
				"Registrating User: ID: {}, Mail: {}, Name: {}, FamilyName: {}, Password: {}, Group: {}",
				new Object[] { user.getId(), user.getMail(),user.getName(), user.getFamilyName(),
									user.getPassword(), user.getUserGroup() });
			this.userDao.save(user);
		}
	}

	/**
	 * @param passwordService
	 *            the passwordService to set
	 */
	public void setPasswordService(PasswordService passwordService) {
		logger.info("Setting autowired PasswordService");
		if (passwordService == null) {
			throw new NullPointerException();
		} else {
			this.passwordService = passwordService;
		}
	}

	/**
	 * @param userDao
	 *            the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		logger.info("Setting autowired UserDao");
		if (userDao == null) {
			throw new NullPointerException();
		} else {
			this.userDao = userDao;
		}
	}

	@Override
	public void update(User u) throws NullPointerException,
			IllegalArgumentException, ConstraintViolationException {
		if (u == null) {
			throw new NullPointerException();
		} else {
			logger.info("Updating User {}", u);
			this.userDao.update(u);
		}
	}
}
