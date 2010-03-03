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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.sloth.persistence.ObservationCategorieDao;
import org.sloth.model.ObservationCategorie;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ObservationCategorieDaoImpl implements
		ObservationCategorieDao {

	EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	private EntityManager getEntityManager() {
		return em;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<ObservationCategorie> getAll() {
		return getEntityManager().createQuery(
				"SELECT oc FROM ObservationCategorie oc").getResultList();
	}

	@Override
	public ObservationCategorie get(long id) {
		return getEntityManager().find(ObservationCategorie.class, id);
	}

	@Override
	public void update(ObservationCategorie oc) {
		getEntityManager().merge(oc);
	}

	@Override
	public void delete(ObservationCategorie oc) {
		getEntityManager().remove(oc);
	}

	@Override
	public void save(ObservationCategorie oc) {
		getEntityManager().persist(oc);
	}

}
