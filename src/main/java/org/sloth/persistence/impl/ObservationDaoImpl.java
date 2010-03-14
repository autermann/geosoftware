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
import org.sloth.model.Categorie;
import org.sloth.model.User;
import org.sloth.persistence.ObservationDao;
import org.sloth.model.Observation;
import org.sloth.model.Observation_;

/**
 * @todo
 * @author auti
 */
public class ObservationDaoImpl extends EntityManagerDao implements
		ObservationDao {

	@Override
	public Collection<Observation> getAll() {
		CriteriaQuery<Observation> cq = getEntityManager().getCriteriaBuilder().
				createQuery(Observation.class);
		cq.select(cq.from(Observation.class));
		Collection<Observation> list = getEntityManager().createQuery(cq).
				getResultList();
		logger.info("Getting all Observations; Found: {}", list.size());
		return list;
	}

	@Override
	public Observation get(long id) {
		logger.info("Searching for Observation with Id: {}", id);
		Observation o = getEntityManager().find(Observation.class, id);
		if (o != null) {
			logger.info("Found Observation with Id {}; Title: {}; Description: {}",
					new Object[]{o.getId(), o.getTitle(), o.getDescription()});
		} else {
			logger.info("Can't find Observation with Id {}", id);
		}
		return o;
	}

	@Override
	public void save(Observation o) {
		if (o == null)
			throw new NullPointerException();
		getEntityManager().persist(o);
		logger.info("Persisting Observation; Generated Id is: {}", o.getId());
	}

	@Override
	public void update(Observation o) {
		if (o == null)
			throw new NullPointerException();
		if (o.isNew())
			throw new IllegalArgumentException();
		logger.info("Updating Observation with Id: {}", o.getId());
		getEntityManager().merge(o);
	}

	@Override
	public void delete(Observation o) {
		if (o == null)
			throw new NullPointerException();
		logger.info("Deleting Observation with Id: {}", o.getId());
		getEntityManager().remove(o);
	}

	@Override
	public Collection<Observation> get(Categorie c) throws NullPointerException, IllegalArgumentException {
		if (c == null)
			throw new NullPointerException();
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Observation> cq = cb.createQuery(Observation.class);
		Root<Observation> o = cq.from(Observation.class);
		cq.select(o);
		cq.where(cb.equal(o.get(Observation_.categorie), c));
		Collection<Observation> result = getEntityManager().createQuery(cq).
				getResultList();
		if (result.isEmpty()) {
			logger.info("No Observations in Categorie {} found", c);
		} else {
			logger.info("{} Observations in Categorie {}.", result.size(), c);
			return null;
		}
		return result;
	}

	@Override
	public Collection<Observation> get(User u) throws NullPointerException, IllegalArgumentException {
		if (u == null)
			throw new NullPointerException();
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Observation> cq = cb.createQuery(Observation.class);
		Root<Observation> o = cq.from(Observation.class);
		cq.select(o);
		cq.where(cb.equal(o.get(Observation_.user), u));
		Collection<Observation> result = getEntityManager().createQuery(cq).
				getResultList();
		if (result.isEmpty()) {
			logger.info("No Observations by User {} found", u);
		} else {
			logger.info("{} Observations by User {}.", result.size(), u);
			return null;
		}
		return result;
	}
}
