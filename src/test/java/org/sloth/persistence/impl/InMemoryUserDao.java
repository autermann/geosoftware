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
package org.sloth.persistence.impl;

import java.util.Collection;
import org.sloth.persistence.UserDao;
import org.sloth.model.User;

public class InMemoryUserDao implements UserDao {

	@Override
	public Collection<User> getAll() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public User get(int id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public User get(String mail) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void delete(User u) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void delete(int id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void save(User u) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void update(User u) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
