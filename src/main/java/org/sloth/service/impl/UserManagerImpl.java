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
import org.sloth.model.UserRight;
import org.sloth.persistence.UserDao;
import org.sloth.model.User;
import org.sloth.persistence.UserRightDao;
import org.sloth.service.PasswordManager;
import org.sloth.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @todo
 * @author auti
 */
public class UserManagerImpl implements UserManager {

	/**
	 * @todo
	 */
	protected static final Logger logger = LoggerFactory.getLogger(
			UserManagerImpl.class);
	@Autowired
	private PasswordManager pm;
	@Autowired
	private UserDao uDao;
	@Autowired
	private UserRightDao urDao;

	/**
	 * @todo
	 * @return
	 */
	protected UserDao getUserDao() {
		return uDao;
	}

	/**
	 * @todo
	 * @param uDao
	 */
	@Override
	public void setUserDao(UserDao uDao) {
		logger.info("Setting autowired UserDao");
		this.uDao = uDao;
	}

	/**
	 * @todo
	 * @param urDao
	 */
	@Override
	public void setUserRightDao(UserRightDao urDao) {
		logger.info("Setting autowired UserRightDao");
		this.urDao = urDao;
	}

	/**
	 * @todo
	 * @return
	 */
	protected UserRightDao getUserRightDao() {
		return this.urDao;
	}

	/**
	 * @todo
	 * @return
	 */
	protected PasswordManager getPasswordManager() {
		return pm;
	}

	/**
	 * @todo
	 * @param u
	 */
	@Override
	public void registrateUser(User u) {
		u.setUserRight(urDao.get(UserRight.USER_VALUE));
		getUserDao().save(u);
	}

	/**
	 * @todo
	 * @param id
	 */
	@Override
	public void deleteUser(long id) {
		getUserDao().delete(id);
	}

	/**
	 * @todo
	 * @return
	 */
	@Override
	public Collection<User> getUsers() {
		return getUserDao().getAll();
	}

	/**
	 * @todo
	 * @param mail
	 * @return
	 */
	@Override
	public User getUser(String mail) {
		return getUserDao().get(mail);
	}

	/**
	 * @todo
	 * @param id
	 * @return
	 */
	@Override
	public User getUser(long id) {
		return getUserDao().get(id);
	}

	/**
	 * @todo
	 * @param u
	 */
	@Override
	public void updateUser(User u) {
		getUserDao().update(u);
	}

	/**
	 * @todo
	 * @param user
	 */
	@Override
	public void deleteUser(User user) {
		getUserDao().delete(user);
	}

	/**
	 * @todo
	 * @param value
	 * @return
	 */
	@Override
	public UserRight getUserRight(int value) {
		return urDao.get(value);
	}

	/**
	 * @todo
	 * @param value
	 * @param title
	 * @param description
	 * @return
	 */
	@Override
	public UserRight createUserRight(int value, String title, String description) {
		UserRight ur = new UserRight(title, description, value);
		getUserRightDao().save(ur);
		return ur;
	}

	/**
	 * @todo
	 * @param value
	 */
	@Override
	public void deleteUserRight(int value) {
		getUserRightDao().delete(value);
	}

	/**
	 * @todo
	 * @param ur
	 */
	@Override
	public void deleteUserRight(UserRight ur) {
		getUserRightDao().delete(ur);
	}

	/**
	 * @todo
	 * @param ur
	 */
	@Override
	public void updateUserRight(UserRight ur) {
		getUserRightDao().update(ur);
	}

}
