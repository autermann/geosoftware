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

import org.sloth.exception.EntityAlreadyKnownException;
import org.sloth.exception.EntityNotKnownException;
import org.sloth.model.Observation;
import org.sloth.model.Report;
import org.sloth.model.Report_;
import org.sloth.model.User;
import org.sloth.persistence.ReportDao;
import org.springframework.stereotype.Repository;

/**
 * Implementation of a {@link ReportDao}.
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
@Repository
public class ReportDaoImpl extends EntityManagerDao<Report> implements
		ReportDao {

	@Override
	public void delete(Collection<Report> t) throws NullPointerException,
			IllegalArgumentException {
		for (Report r : t) {
			if (!isAttached(r)) {
				throw new EntityNotKnownException();
			}
			logger.info("Deleting Report with Id: {}", r.getId());
			getEntityManager().remove(r);
		}
		getEntityManager().flush();
	}

	@Override
	public void delete(Report t) throws NullPointerException,
			IllegalArgumentException {
		if (!isAttached(t)) {
			throw new EntityNotKnownException();
		}
		logger.info("Deleting Report with Id: {}", t.getId());
		getEntityManager().remove(t);
		getEntityManager().flush();

	}

	@Override
	public Collection<Report> getAll() {
		CriteriaQuery<Report> cq = getEntityManager().getCriteriaBuilder()
				.createQuery(Report.class);
		cq.select(cq.from(Report.class));
		Collection<Report> list = getEntityManager().createQuery(cq)
				.getResultList();
		logger.info("Getting all Reports; Found: {}", list.size());
		return list;
	}

	@Override
	public Report getById(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		logger.info("Searching for Report with Id: {}", id);
		Report r = getEntityManager().find(Report.class, id);
		if (r != null) {
			logger.info("Found Report with Id {}; Author: {}; Observation: {}",
					new Object[] { r.getId(), r.getAuthor().getId(),
							r.getObservation().getId() });
		} else {
			logger.info("Can't find Report with Id {}", id);
		}
		return r;
	}

	@Override
	public Collection<Report> getByObservation(Observation o)
			throws NullPointerException, IllegalArgumentException {
		if (o == null) {
			throw new NullPointerException();
		}
		if (o.isNew()) {
			throw new IllegalArgumentException();
		}
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Report> cq = cb.createQuery(Report.class);
		Root<Report> r = cq.from(Report.class);
		cq.select(r).where(cb.equal(r.get(Report_.observation), o));
		Collection<Report> result = getEntityManager().createQuery(cq)
				.getResultList();
		logger.info("{} Reports for Observation {}.", result.size(), o);
		return result;
	}

	@Override
	public Collection<Report> getByUser(User u) throws NullPointerException,
			IllegalArgumentException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Collection<Report> getProcessed() {
		return get(true);

	}

	@Override
	public Collection<Report> getUnprocessed() {
		return get(false);
	}

	@Override
	public void save(Report t) throws NullPointerException {
		if (isAttached(t)) {
			throw new EntityAlreadyKnownException();
		}
		getEntityManager().persist(t);
		logger.info("Persisting Report; Generated Id is: {}", t.getId());
		getEntityManager().flush();
	}

	@Override
	public void update(Report t) throws NullPointerException,
			IllegalArgumentException {
		if (!isAttached(t)) {
			throw new EntityNotKnownException();
		}
		logger.info("Updating Report with Id: {}", t.getId());
		getEntityManager().merge(t);
		getEntityManager().flush();
	}

	private Collection<Report> get(boolean processed) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Report> cq = cb.createQuery(Report.class);
		Root<Report> r = cq.from(Report.class);
		cq.select(r).where(cb.equal(r.get(Report_.processed), processed));
		Collection<Report> result = getEntityManager().createQuery(cq)
				.getResultList();
		logger.info("{} {} Reports.",
				(processed) ? "processed" : "unprocessed", result.size());
		return result;
	}
}
