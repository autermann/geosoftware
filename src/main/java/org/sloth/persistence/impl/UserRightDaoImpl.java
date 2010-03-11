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
import org.sloth.model.UserRight;
import org.sloth.persistence.UserRightDao;

/**
 * @todo
 * @author auti
 */
public class UserRightDaoImpl extends EntityManagerDao implements UserRightDao {

	/**
	 * @return 
	 * @todo
	 */
	@Override
	public Collection<UserRight> getAll() {
		CriteriaQuery<UserRight> cq = getEntityManager().getCriteriaBuilder().
				createQuery(UserRight.class);
		cq.select(cq.from(UserRight.class));
		Collection<UserRight> list = getEntityManager().createQuery(cq).
				getResultList();

		logger.info("Getting all UserRights; Found: {}", list.size());
		return list;
	}

	/**
	 * @todo
	 * @param Value
	 * @return
	 */
	@Override
	public UserRight get(int Value) {
		logger.info("Searching for UserRight with Value: {}", Value);
		UserRight oc = getEntityManager().find(UserRight.class, Value);
		if (oc != null) {
			logger.info(
					"Found UserRight with Value {}; Name: {}; Description: {}", new Object[]{oc.
						getValue(), oc.getName(), oc.getDescription()});
		} else {
			logger.info("Can't find UserRight with Value {}", Value);
		}
		return oc;
	}

	/**
	 * @todo
	 * @param oc
	 */
	@Override
	public void update(UserRight oc) {
		logger.info("Updating UserRight with Value: {}", oc.getValue());
		getEntityManager().merge(oc);
	}

	/**
	 * @todo
	 * @param oc
	 */
	@Override
	public void delete(UserRight oc) {
		logger.info("Deleting UserRight with Value: {}", oc.getValue());
		getEntityManager().remove(oc);
	}

	/**
	 * @todo
	 * @param oc
	 */
	@Override
	public void save(UserRight oc) {
		getEntityManager().persist(oc);
		logger.info("Persisting UserRight; Generated Value is: {}",
					oc.getValue());
	}

	/**
	 * @todo
	 */
	@Override
	public void flush() {
		logger.info("Flushing EntityManager");
		getEntityManager().flush();
	}

	/**
	 * @todo
	 * @param id
	 */
	@Override
	public void delete(int id) {
		delete(get(id));
	}

}
