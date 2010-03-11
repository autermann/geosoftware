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
import javax.persistence.criteria.CriteriaQuery;
import org.sloth.persistence.ObservationDao;
import org.sloth.model.Observation;

/**
 * @todo
 * @author auti
 */
public class ObservationDaoImpl extends EntityManagerDao implements
		ObservationDao {

	/**
	 * @todo
	 * @return
	 */
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

	/**
	 * @todo
	 * @param id
	 * @return
	 */
	@Override
	public Observation get(long id) {
		logger.info("Searching for Observation with Id: {}", id);
		Observation o = getEntityManager().find(Observation.class, id);
		if (o != null) {
			logger.info(
					"Found Observation with Id {}; Title: {}; Description: {}", new Object[]{o.
						getId(), o.getTitle(), o.getDescription()});
		} else {
			logger.info("Can't find Observation with Id {}", id);
		}
		return o;
	}

	/**
	 * @todo
	 * @param o
	 */
	@Override
	public void save(Observation o) {
		getEntityManager().persist(o);
		logger.info("Persisting Observation; Generated Id is: {}", o.getId());
	}

	/**
	 * @todo
	 * @param o
	 */
	@Override
	public void update(Observation o) {
		logger.info("Updating Observation with Id: {}", o.getId());
		getEntityManager().merge(o);
	}

	/**
	 * @todo
	 * @param id
	 */
	@Override
	public void delete(long id) {
		delete(get(id));
	}

	/**
	 * @todo
	 * @param o
	 */
	@Override
	public void delete(Observation o) {
		logger.info("Deleting Observation with Id: {}", o.getId());
		getEntityManager().remove(o);
	}

	/**
	 * @todo
	 */
	@Override
	public void flush() {
		logger.info("Flushing EntityManager");
		getEntityManager().flush();
	}

}
