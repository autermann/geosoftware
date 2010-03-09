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
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserDaoImpl extends EntityManagerDao implements UserDao {

	@Override
	@SuppressWarnings("unchecked")
	public Collection<User> getAll() {
		return getEntityManager().createQuery(
				"SELECT u FROM User u").getResultList();
	}

	@Override
	public User get(long id) {
		return getEntityManager().find(User.class, id);
	}

	@Override
	public void save(User u) {
		getEntityManager().persist(u);
	}

	@Override
	public void update(User u) {
		getEntityManager().merge(u);
	}

	@Override
	public void delete(User u) {
		getEntityManager().remove(u);
	}

	@Override
	public void delete(long id) {
		delete(get(id));
	}

	@Override
	public User get(String mail) {
		return (User) getEntityManager().createQuery("SELECT u FROM User u WHERE eMail == "+mail).getSingleResult();
	}

	@Override
	public void flush() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
