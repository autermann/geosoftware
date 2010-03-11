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
import org.springframework.transaction.annotation.Transactional;

/**
 * @todo
 * @author auti
 */
@Transactional
public abstract class EntityManagerDao {

	/**
	 * @todo
	 */
	protected static final Logger logger = LoggerFactory.getLogger(
			EntityManagerDao.class);
	private EntityManager entityManager;

	/**
	 * @todo
	 * @param em
	 */
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	/**
	 * @todo
	 * @return
	 */
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

}
