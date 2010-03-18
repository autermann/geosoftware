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
import java.util.HashSet;
import java.util.LinkedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	public void setObservationDao(ObservationDao oDao) {
		logger.info("Setting autowired ObservationDao");
		this.observationDao = oDao;
	}

	public void setCategorieDao(CategorieDao ocDao) {
		logger.info("Setting autowired CategorieDao");
		this.categorieDao = ocDao;
	}

	@Override
	public Observation getObservation(Long id) {
		return getObservationDao().get(id);
	}

	@Override
	public Collection<Observation> getObservations() {
		return getObservationDao().getAll();
	}

	@Override
	public Collection<Observation> getObservations(String keyword) {
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
	public void deleteObservation(Observation observation) {
		getObservationDao().delete(observation);
	}

	@Override
	public void updateObservation(Observation observation) {
		getObservationDao().update(observation);
	}

	@Override
	public void registrate(Observation observation) {
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
	public Collection<Observation> getObservations(Categorie oc) {
		Collection<Observation> result = new LinkedList<Observation>();
		for (Observation o : getObservations()) {
			if (o.getCategorie().equals(oc)) {
				result.add(o);
			}
		}
		return result;
	}

	@Override
	public Categorie getCategorie(Long id) {
		return null;
	}

	@Override
	public Collection<Categorie> getCategories() {
		return getCategorieDao().getAll();
	}

	@Override
	public void deleteCategorie(Categorie categorie) {
		getCategorieDao().delete(categorie);
	}

	@Override
	public void deleteCategorie(Long id) {
		getCategorieDao().delete(getCategorie(id));
	}

	@Override
	public void updateCategorie(Categorie categorie) {
		getCategorieDao().update(categorie);
	}

	@Override
	public void registrate(Categorie categorie) {
		getCategorieDao().save(categorie);
	}

	@Override
	public void deleteObservation(Long id) {
		deleteCategorie(getCategorieDao().get(id));
	}



}
