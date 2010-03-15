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
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import org.sloth.persistence.CategorieDao;
import org.sloth.model.Categorie;

public class CategorieDaoImpl extends EntityManagerDao implements
		CategorieDao {

	@Override
	public Collection<Categorie> getAll() {
		CriteriaQuery<Categorie> cq = getEntityManager().
				getCriteriaBuilder().createQuery(Categorie.class);
		cq.select(cq.from(Categorie.class));
		Collection<Categorie> list = getEntityManager().createQuery(
				cq).getResultList();
		logger.info("Getting all ObservationCategories; Found: {}", list.size());
		return list;
	}

	@Override
	public Categorie get(long id) {
		logger.info("Searching for ObservationCategorie with Id: {}", id);
		Categorie oc = getEntityManager().find(
				Categorie.class, id);
		if (oc != null) {
			logger.info(
					"Found ObservationCategorie with Id {}; Title: {}; Description: {}",
						new Object[]{oc.getId(), oc.getTitle(), oc.
						getDescription()});
		} else {
			logger.info("Can't find ObservationCategorie with Id {}", id);
		}
		return oc;
	}

	@Override
	public void update(Categorie oc) {
		logger.info("Updating ObservationCategorie with Id: {}", oc.getId());
		getEntityManager().merge(oc);
		getEntityManager().flush();

	}

	@Override
	public void delete(Categorie oc) {
		logger.info("Deleting ObservationCategorie with Id: {}", oc.getId());
		getEntityManager().remove(oc);
		getEntityManager().flush();

	}

	@Override
	public void save(Categorie oc) {
		EntityManager em = getEntityManager();
		em.persist(oc);
		logger.info("Persisting ObservationCategorie; Generated Id is: {}", oc.
				getId());
		getEntityManager().flush();

	}
}
