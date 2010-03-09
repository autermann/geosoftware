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
import org.sloth.persistence.ObservationCategorieDao;
import org.sloth.model.ObservationCategorie;

public class ObservationCategorieDaoImpl extends EntityManagerDao implements ObservationCategorieDao {

	@Override
	public Collection<ObservationCategorie> getAll() {
		Collection<ObservationCategorie> col = getEntityManager().createQuery("select o from OBSERVATION_CATEGORIE o").getResultList();
		logger.info("Getting all ObservationCategories; Found: " + col.size());
		return col;
	}

	@Override
	public ObservationCategorie get(long id) {
		logger.info("Searching for ObservationCategorie with Id: " + id);
		ObservationCategorie oc = getEntityManager().find(ObservationCategorie.class, id);
		if (oc != null) {
			logger.info("Found ObservationCategorie with Id " + oc.getId() + "; Title: " + oc.getTitle() + "Description: " + oc.getTitle());
		} else {
			logger.info("Can't find ObservationCategorie with Id " + id);
		}
		return oc;
	}

	@Override
	public void update(ObservationCategorie oc) {
		logger.info("Updating ObservationCategorie with Id: " + oc.getId());
		getEntityManager().merge(oc);
	}

	@Override
	public void delete(ObservationCategorie oc) {
		logger.info("Deleting ObservationCategorie with Id: " + oc.getId());
		getEntityManager().remove(oc);
	}

	@Override
	public void save(ObservationCategorie oc) {
		EntityManager em = getEntityManager();
		em.persist(oc);
		em.flush();
		logger.info("Persisting ObservationCategorie; Generated Id is: " + oc.getId());
	}

	@Override
	public void flush() {
		logger.info("Flushing Entitymanager");
		getEntityManager().flush();
	}
}
