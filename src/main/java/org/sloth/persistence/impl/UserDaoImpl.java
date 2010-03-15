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
package org.sloth.persistence.impl;

import java.util.Collection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.sloth.persistence.UserDao;
import org.sloth.model.User;
import org.sloth.model.User_;
import org.springframework.transaction.annotation.Transactional;

/**
 * @todo
 * @author auti
 */
@Transactional
public class UserDaoImpl extends EntityManagerDao implements UserDao {

	@Override
	public Collection<User> getAll() {
		CriteriaQuery<User> cq = getEntityManager().getCriteriaBuilder().createQuery(User.class);
		cq.select(cq.from(User.class));
		Collection<User> list = getEntityManager().createQuery(cq).getResultList();
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
		if (u == null)
			throw new NullPointerException();
		logger.info("Registrating User: ID: {}, Mail: {}, Name: {}, FamilyName: {}, Password: {}, Group: {}", new Object[]{
			u.getId(),
			u.getMail(),
			u.getName(),
			u.getFamilyName(),
			u.getPassword(),
			u.getUserGroup()
		});
		getEntityManager().persist(u);
		getEntityManager().flush();
		logger.info("Persisting User; Generated Id is: {}", u.getId());
	}

	@Override
	public void update(User u) {
		if (u == null)
			throw new NullPointerException();
		logger.info("Updating {}", u);
		getEntityManager().merge(u);
		getEntityManager().flush();
	}

	@Override
	public void delete(User u) {
		if (u == null)
			throw new NullPointerException();
		logger.info("Deleting User with Id: {}", u.getId());
		getEntityManager().remove(u);
		getEntityManager().flush();
	}

	@Override
	public User get(String mail) {
		if (mail == null)
			throw new NullPointerException();
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> user = cq.from(User.class);
		cq.select(user);
		cq.where(cb.equal(user.get(User_.mail), mail));
		Collection<User> result = getEntityManager().createQuery(cq).
				getResultList();
		if (result.size() == 1) {
			return result.iterator().next();
		} else if (result.isEmpty()) {
			logger.info("User with eMail {} not found", mail);
			return null;
		} else {
			logger.error("{} results for eMail {}. Corrupt Database?", result.
					size(), mail);
			return null;
		}
	}
}
