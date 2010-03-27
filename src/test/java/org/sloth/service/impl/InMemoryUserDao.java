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
import org.sloth.model.User;
import org.sloth.persistence.UserDao;

/**
 * 
 * @author auti
 */
public class InMemoryUserDao extends InMemoryDao<User> implements UserDao {

	@Override
	public User getByMail(String mail) {
		for (User u : getAll())
			if (u.getMail().equalsIgnoreCase(mail))
				return u;
		return null;
	}

	@Override
	public void delete(Collection<User> t) throws NullPointerException,
			IllegalArgumentException {
		this.deleteAll(t);
	}
}
