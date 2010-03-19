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
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.sloth.persistence.CategorieDao;
import org.sloth.model.Categorie;
import org.sloth.model.Categorie_;
import org.sloth.model.Observation;
import org.sloth.persistence.ObservationDao;
import org.sloth.util.Config;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @todo
 * @author auti
 */
public class CategorieDaoImpl extends EntityManagerDao<Categorie> implements
		CategorieDao {

	
	private ObservationDao observationDao;
	private Categorie defaultCategorie;

	/**
	 * @param observationDao the observationDao to set
	 */
	@Autowired
	public void setObservationDao(ObservationDao observationDao) {
		this.observationDao = observationDao;
	}

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
	public Categorie get(String title) {
		if (title == null) {
			throw new NullPointerException();
		}
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Categorie> cq = cb.createQuery(Categorie.class);
		Root<Categorie> categorie = cq.from(Categorie.class);
		cq.select(categorie);
		cq.where(cb.equal(categorie.get(Categorie_.title), title));
		Categorie result = null;
		try{
			result = getEntityManager().createQuery(cq).getSingleResult();
		} catch (NoResultException e) {
			logger.info("Categorie with Title {} not found", title);
		} catch (NonUniqueResultException e) {
			logger.warn("Corrupt Database", e);
		}
		return result;
	}

	@Override
	public Categorie get(Long id) {
		logger.info("Searching for ObservationCategorie with Id: {}", id);
		Categorie oc = getEntityManager().find(Categorie.class, id);
		if (oc != null) {
			logger.info("Found ObservationCategorie with Id {}; Title: {}; Description: {}",
					new Object[]{oc.getId(), oc.getTitle(), oc.getDescription()});
		} else {
			logger.info("Can't find ObservationCategorie with Id {}", id);
		}
		return oc;
	}

	@Override
	public void update(Categorie oc) {
		isAttached(oc);
		logger.info("Updating ObservationCategorie with Id: {}", oc.getId());
		getEntityManager().merge(oc);
		getEntityManager().flush();

	}

	@Override
	public void delete(Categorie oc) {
		isAttached(oc);
		Categorie newCategorie = getDefaultCategorie();
		logger.info("Replacing Categorie {} with default one.", oc);
		for (Observation o : observationDao.get(oc)) {
			o.setCategorie(newCategorie);
			observationDao.update(o);
		}
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

	private Categorie getDefaultCategorie() {
		if (defaultCategorie == null) {
			String title = Config.getProperty("default.categorie.title");
			String descr = Config.getProperty("default.categorie.description");
			Categorie tmp = get(title);
			if (tmp == null) {
				defaultCategorie = new Categorie((title == null) ? "UNKNOWN" : title,
												 (descr == null) ? "UNKNOWN" : descr);
				save(defaultCategorie);
			} else {
				defaultCategorie = tmp;
			}
		}
		return defaultCategorie;
	}

}
