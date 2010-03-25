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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.sloth.model.BaseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Abstract class for Data Acccess Objects using an {@code EntityManager}
 * 
 * @param <T> TODO
 * @see EntityManager
 * @author Christian Autermann
 */
@Transactional
@Repository
public abstract class EntityManagerDao<T extends BaseEntity> {

	/**
	 * {@code Logger}-Facade for this class and subclasses.
	 */
	protected static final Logger logger = LoggerFactory.getLogger(
			EntityManagerDao.class);
	private EntityManager entityManager;

	/**
	 * Sets the {@code EntityManager} fpr this class. Will be autowired.
	 *
	 * @param em the {@code EntityManager}
	 */
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	public void setEntityManager(EntityManager em) {
		logger.info("Setting EntityManager");
		this.entityManager = em;
	}

	/**
	 * Returns the {@code EntityManager}.
	 * 
	 * @return the {@code EntityManager}
	 */
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	/**
	 * @todo
	 * @param t
	 * @return 
	 * @throws NullPointerException
	 */
	protected boolean isAttached(T t) throws NullPointerException {
		if (t == null)
			throw new NullPointerException();
		return getEntityManager().contains(t);
	}
}
