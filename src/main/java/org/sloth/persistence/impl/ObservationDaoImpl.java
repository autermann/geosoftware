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
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import org.sloth.exceptions.EntityAlreadyKnownException;
import org.sloth.exceptions.EntityNotKnownException;
import org.sloth.model.Categorie;
import org.sloth.model.Categorie_;
import org.sloth.model.User;
import org.sloth.persistence.ObservationDao;
import org.sloth.model.Observation;
import org.sloth.model.Observation_;
import org.sloth.persistence.ReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ObservationDaoImpl extends EntityManagerDao<Observation> implements
		ObservationDao {

	private ReportDao reportDao;

	@Autowired
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

	@Override
	public Collection<Observation> getAll() {
		CriteriaQuery<Observation> cq = getEntityManager().getCriteriaBuilder().createQuery(Observation.class);
		cq.select(cq.from(Observation.class));
		Collection<Observation> list = getEntityManager().createQuery(cq).getResultList();
		logger.info("Getting all Observations; Found: {}", list.size());
		return list;
	}

	@Override
	public Observation getById(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		logger.info("Searching for Observation with Id: {}", id);
		Observation o = getEntityManager().find(Observation.class, id);
		if (o != null) {
			logger.info(
					"Found Observation with Id {}; Title: {}; Description: {}",
					new Object[]{o.getId(), o.getTitle(),
								 o.getDescription()});
		} else {
			logger.info("Can't find Observation with Id {}", id);
		}
		return o;
	}

	@Override
	public void save(Observation o) {
		if (isAttached(o)) {
			throw new EntityAlreadyKnownException();
		}
		getEntityManager().persist(o);
		logger.info("Persisting Observation; Generated Id is: {}", o.getId());
		getEntityManager().flush();
	}

	@Override
	public void update(Observation o) {
		if (!isAttached(o)) {
			throw new EntityNotKnownException();
		}
		logger.info("Updating Observation with Id: {}", o.getId());
		getEntityManager().merge(o);
		getEntityManager().flush();
	}

	@Override
	public void delete(Observation o) {
		if (!isAttached(o)) {
			throw new EntityNotKnownException();
		}
		this.reportDao.delete(this.reportDao.getByObservation(o));
		logger.info("Deleting Observation with Id: {}", o.getId());
		getEntityManager().remove(o);
		getEntityManager().flush();
	}

	@Override
	public Collection<Observation> getByCategorie(Categorie c)
			throws NullPointerException, IllegalArgumentException {
		if (c == null) {
			throw new NullPointerException();
		}
		if (c.isNew()) {
			throw new IllegalArgumentException();
		}
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Observation> cq = cb.createQuery(Observation.class);
		Root<Observation> o = cq.from(Observation.class);
		cq.select(o).where(cb.equal(o.get(Observation_.categorie), c));
		Collection<Observation> result = getEntityManager().createQuery(cq).getResultList();
		logger.info("{} Observations in Categorie {}.", result.size(), c);
		return result;
	}

	@Override
	public Collection<Observation> getByUser(User u)
			throws NullPointerException, IllegalArgumentException {
		if (u == null) {
			throw new NullPointerException();
		}
		if (u.isNew()) {
			throw new EntityNotKnownException();
		}
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Observation> cq = cb.createQuery(Observation.class);
		Root<Observation> o = cq.from(Observation.class);
		cq.select(o).where(cb.equal(o.get(Observation_.user), u));
		Collection<Observation> result = getEntityManager().createQuery(cq).getResultList();
		logger.info("{} Observations by User {}.", result.size(), u);
		return result;
	}

	@Override
	public List<Observation> getNewestObservations(int count) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Observation> cq = cb.createQuery(Observation.class);

		Root<Observation> o = cq.from(Observation.class);
		cq.select(o).orderBy(cb.desc(o.get(Observation_.creationDate)));
		List<Observation> obs = getEntityManager().createQuery(cq).getResultList();
		if (!obs.isEmpty()) {
			return obs.subList(0, ((obs.size() < count) ? obs.size() : count) - 1);
		} else {
			return obs;
		}
	}

	@Override
	public Collection<Observation> getByKeyWord(String key)
			throws NullPointerException {
		if (key == null) {
			throw new NullPointerException();
		}
		key = "%" + key.trim().replace('*','%').toUpperCase() + "%";
		CriteriaBuilder b = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Observation> q = b.createQuery(Observation.class);
		Root<Observation> o = q.from(Observation.class);
		Join<Observation, Categorie> j = o.join(Observation_.categorie);
		Collection<Observation> result = getEntityManager().createQuery(
		q.select(o).distinct(true).where(b.or(
				b.like(b.upper(j.get(Categorie_.description)), key),
				b.like(b.upper(j.get(Categorie_.description)), key),
				b.like(b.upper(o.get(Observation_.title)), key),
				b.like(b.upper(o.get(Observation_.description)), key)))).getResultList();
		logger.info("Searched for {}. Got {} Results.", key, result.size());
		return result;
	}

	@Override
	public void delete(Collection<Observation> t) throws NullPointerException,
			IllegalArgumentException {
		for (Observation o : t) {
			if (!isAttached(o)) {
				throw new EntityNotKnownException();
			}
			reportDao.delete(reportDao.getByObservation(o));
			logger.info("Deleting Observation with Id: {}", o.getId());
			getEntityManager().remove(o);
		}
		getEntityManager().flush();
	}
}
