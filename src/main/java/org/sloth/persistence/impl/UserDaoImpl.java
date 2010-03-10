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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import org.sloth.persistence.UserDao;
import org.sloth.model.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserDaoImpl extends EntityManagerDao implements UserDao {

	@Override
	public Collection<User> getAll() {
		Collection<User> list = getEntityManager().createQuery("SELECT u FROM USER u").getResultList();
		logger.info("Getting all Users; Found: {}", list.size());
		return list;
	}

	@Override
	public User get(long id) {
		logger.info("Searching for Observation with Id: {}", id);
		User u = getEntityManager().find(User.class, id);
		if (u != null) {
			logger.info("Found User with Id {}", u.getId());
		} else {
			logger.info("Can't find Observation with Id {}", id);
		}
		return u;
	}

	@Override
	public void save(User u) {
		getEntityManager().persist(u);
		logger.info("Persisting User; Generated Id is: {}", u.getId());
	}

	@Override
	public void update(User u) {
		getEntityManager().merge(u);
	}

	@Override
	public void delete(long id) {
		delete(get(id));
	}

	@Override
	public void delete(User u) {
		logger.info("Deleting User with Id: {}", u.getId());
		getEntityManager().remove(u);
	}

	@Override
	public User get(String mail) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		cq.where(cb.equal(cq.from(User.class).get("MAIL_ADDRESS"), mail));
		cq.select(cq.from(User.class));
		return getEntityManager().createQuery(cq).getSingleResult();

	}

	@Override
	public void flush() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
