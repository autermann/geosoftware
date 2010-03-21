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
package org.sloth.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.exceptions.ConstraintViolationException;
import org.sloth.persistence.ObservationDao;
import org.sloth.model.Observation;
import org.sloth.model.Categorie;
import org.sloth.persistence.CategorieDao;
import org.sloth.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

@Service
public class ObservationServiceImpl implements ObservationService {

	protected static final Logger logger = LoggerFactory.getLogger(
			ObservationServiceImpl.class);
	@Autowired
	private ObservationDao observationDao;
	@Autowired
	private CategorieDao categorieDao;

	private ObservationDao getObservationDao() {
		return observationDao;
	}

	private CategorieDao getCategorieDao() {
		return categorieDao;
	}

	/**
	 * @todo
	 * @param oDao
	 * @throws NullPointerException
	 */
	public void setObservationDao(ObservationDao oDao) throws
			NullPointerException {
		if (oDao == null) {
			throw new NullPointerException();
		}
		logger.info("Setting autowired ObservationDao");
		this.observationDao = oDao;
	}

	/**
	 * @todo
	 * @param ocDao
	 * @throws NullPointerException 
	 */
	public void setCategorieDao(CategorieDao ocDao) throws NullPointerException {
		if (ocDao == null) {
			throw new NullPointerException();
		}
		logger.info("Setting autowired CategorieDao");
		this.categorieDao = ocDao;
	}

	@Override
	public Observation getObservation(Long id) throws NullPointerException {
		if (id == null) {
			throw new NullPointerException();
		}
		return getObservationDao().getById(id);
	}

	@Override
	public Collection<Observation> getObservations() {
		return getObservationDao().getAll();
	}

	@Override
	public Collection<Observation> getObservations(String keyword) throws
			NullPointerException {
		if (keyword == null) {
			throw new NullPointerException();
		}
		String regex = buildRegex(keyword);
		HashSet<Observation> result = new HashSet<Observation>();
		for (Observation o : getObservations()) {
			Categorie oc = o.getCategorie();
			if (o.getTitle().matches(regex)
					|| o.getDescription().matches(regex)
					|| oc.getTitle().matches(regex)
					|| oc.getDescription().matches(regex)) {
				result.add(o);
			}
		}
		return result;
	}

	@Override
	public void deleteObservation(Observation observation) throws
			NullPointerException, IllegalArgumentException {
		if (observation == null) {
			throw new NullPointerException();
		}
		getObservationDao().delete(observation);
	}

	@Override
	public void updateObservation(Observation observation) throws
			NullPointerException, ConstraintViolationException,
			IllegalArgumentException {
		if (observation == null) {
			throw new NullPointerException();
		}
		getObservationDao().update(observation);
	}

	@Override
	public void registrate(Observation observation) throws NullPointerException,
			ConstraintViolationException,
			IllegalArgumentException {
		if (observation == null) {
			throw new NullPointerException();
		}
		getObservationDao().save(observation);
	}

	private String buildRegex(String keyword) {
		//TODO geht besser
		StringBuffer buffer = new StringBuffer(".*");
		for (char c : keyword.toCharArray()) {
			buffer.append("[");
			buffer.append(toLowerCase(c));
			buffer.append("|");
			buffer.append(toUpperCase(c));
			buffer.append("]");
		}
		buffer.append(".*");
		return buffer.toString();
	}

	@Override
	public Collection<Observation> getObservations(Categorie oc) throws
			NullPointerException, IllegalArgumentException {
		if (oc == null) {
			throw new NullPointerException();
		}
		Collection<Observation> result = new LinkedList<Observation>();
		for (Observation o : getObservations()) {
			if (o.getCategorie().equals(oc)) {
				result.add(o);
			}
		}
		return result;
	}

	@Override
	public Categorie getCategorie(Long id) throws NullPointerException {
		if (id == null) {
			throw new NullPointerException();
		}
		return getCategorieDao().getById(id);
	}

	@Override
	public Collection<Categorie> getCategories() {
		return getCategorieDao().getAll();
	}

	@Override
	public void deleteCategorie(Categorie categorie) throws NullPointerException,
			IllegalArgumentException {
		if (categorie == null) {
			throw new NullPointerException();
		}
		getCategorieDao().delete(categorie);
	}

	@Override
	public void deleteCategorie(Long id) throws NullPointerException,
			IllegalArgumentException {
		if (id == null) {
			throw new NullPointerException();
		}
		getCategorieDao().delete(getCategorie(id));
	}

	@Override
	public void updateCategorie(Categorie categorie) throws NullPointerException,
			ConstraintViolationException,
			IllegalArgumentException {
		if (categorie == null) {
			throw new NullPointerException();
		}
		getCategorieDao().update(categorie);
	}

	@Override
	public void registrate(Categorie categorie) throws
			ConstraintViolationException, NullPointerException {
		if (categorie == null) {
			throw new NullPointerException();
		}
		getCategorieDao().save(categorie);
	}

	@Override
	public void deleteObservation(Long id) throws NullPointerException,
			IllegalArgumentException {
		if (id == null) {
			throw new NullPointerException();
		}
		deleteCategorie(getCategorieDao().getById(id));
	}

	@Override
	public Map<Categorie, Collection<Observation>> getObservationsByCategories() {
		HashMap<Categorie, Collection<Observation>> map
				= new HashMap<Categorie, Collection<Observation>>();
		for (Categorie c : categorieDao.getAll()) {
			map.put(c, observationDao.getByCategorie(c));
		}
		return map;
	}

	@Override
	public Categorie getCategorieByTitle(String title) {
		if (title == null)
			throw new NullPointerException();
		return categorieDao.getByTitle(title);
	}
}
