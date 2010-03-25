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
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.sloth.exceptions.EntityAlreadyKnownException;
import org.sloth.exceptions.EntityNotKnownException;
import org.sloth.model.Group;
import org.sloth.model.Observation;
import org.sloth.persistence.UserDao;
import org.sloth.model.User;
import org.sloth.model.User_;
import org.sloth.persistence.ObservationDao;
import org.sloth.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @todo
 * @author auti
 */
@Transactional
public class UserDaoImpl extends EntityManagerDao<User> implements UserDao {

	private ObservationDao observationDao;

	/**
	 * @param observationDao the observationDao to set
	 */
	@Autowired
	public void setObservationDao(ObservationDao observationDao) {
		logger.info("getting the observation dao");
		this.observationDao = observationDao;
	}

	@Override
	public Collection<User> getAll() {
		CriteriaQuery<User> cq = getEntityManager().getCriteriaBuilder().
				createQuery(User.class);
		cq.select(cq.from(User.class));
		Collection<User> list =
				getEntityManager().createQuery(cq).getResultList();
		logger.info("Getting all Users; Found: {}", list.size());
		return list;
	}

	@Override
	public User getById(Long id) {
		if (id == null)
			throw new NullPointerException();
		logger.info("Searching for Observation with Id: {}", id);
		User u = getEntityManager().find(User.class, id);
		if (u != null)
			logger.info("Found User with Id {}", u.getId());
		else
			logger.info("Can't find User with Id {}", id);
		return u;
	}

	@Override
	public void save(User u) {
		if (u == null)
			throw new NullPointerException();
		if (isAttached(u))
			throw new EntityAlreadyKnownException();
		logger.info(
				"Registrating User: ID: {}, Mail: {}, Name: {}, FamilyName: {}, Password: {}, Group: {}",
				new Object[]{u.getId(), u.getMail(), u.getName(),
							 u.getFamilyName(), u.getUserGroup()});
		getEntityManager().persist(u);
		getEntityManager().flush();
		logger.info("Persisting User; Generated Id is: {}", u.getId());
	}

	@Override
	public void update(User u) {
		if (!isAttached(u))
			throw new EntityNotKnownException();
		logger.info("Updating {}", u);
		getEntityManager().merge(u);
		getEntityManager().flush();
	}

	@Override
	public void delete(User u) {
		if (!isAttached(u))
			throw new EntityNotKnownException();
		User newUser = getDefaultUser();
		Collection<Observation> obs = observationDao.getByUser(u);
		logger.info("Replacing {} with {} in {} Observations",
				new Object[]{u, newUser, obs.size()});
		for (Observation o : obs) {
			o.setUser(newUser);
			observationDao.update(o);
		}
		logger.info("Deleting User with Id: {}", u.getId());
		getEntityManager().remove(u);
		getEntityManager().flush();
	}

	@Override
	public User getByMail(String mail) {
		if (mail == null)
			throw new NullPointerException();
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> user = cq.from(User.class);
		cq.select(user);
		cq.where(cb.equal(user.get(User_.mail), mail));
		User result = null;
		try {
			result = getEntityManager().createQuery(cq).getSingleResult();
		} catch (NoResultException e) {
			logger.info("User with address {} not found", mail);
		} catch (NonUniqueResultException e) {
			logger.warn("Corrupt Database", e);
		}
		return result;
	}
	private User defaultUser;

	private User getDefaultUser() {
		if (defaultUser == null) {
			String mail = Config.getProperty("default.user.mail");
			String name = Config.getProperty("default.user.name");
			String fami = Config.getProperty("default.user.familyName");
			User tmp = getByMail(mail);
			if (tmp == null) {
				defaultUser = new User((mail == null) ? "UNKNOWN" : mail,
						(name == null) ? "UNKNOWN" : name,
						(fami == null) ? "UNKNOWN" : fami,
						"", Group.USER);
				save(defaultUser);
			} else
				defaultUser = tmp;
		}
		return defaultUser;
	}
}
