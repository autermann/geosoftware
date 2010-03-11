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
package org.sloth.service;

import java.util.Collection;
import org.sloth.model.Coordinate;
import org.sloth.model.Observation;
import org.sloth.model.ObservationCategorie;
import org.sloth.model.User;
import org.sloth.persistence.ObservationCategorieDao;
import org.sloth.persistence.ObservationDao;

/**
 * @todo
 * @author auti
 */
public interface ObservationManager {

	/**
	 * @todo
	 * @param oDao
	 */
	public void setObservationDao(ObservationDao oDao);

	/**
	 * @todo
	 * @param ocDao
	 */
	public void setObservationCategorieDao(ObservationCategorieDao ocDao);

	/**
	 * @todo
	 * @param id
	 * @return
	 */
	public Observation getObservation(int id);

	/**
	 * @todo
	 * @return
	 */
	public Collection<Observation> getObservations();

	/**
	 * @todo
	 * @param oc
	 * @return
	 */
	public Collection<Observation> getObservations(ObservationCategorie oc);

	/**
	 * 
	 * @param keyword
	 * @return
	 */
	public Collection<Observation> getObservations(String keyword);

	/**
	 * @todo
	 * @param id
	 */
	public void deleteObservation(int id);

	/**
	 * @todo
	 * @param observation
	 */
	public void deleteObservation(Observation observation);

	/**
	 * @todo
	 * @param observation
	 */
	public void updateObservation(Observation observation);

	/**
	 * @todo
	 * @param title
	 * @param description
	 * @param user
	 * @param coordinate
	 */
	public void registrateObservation(String title, String description,
									  User user, Coordinate coordinate);

}
