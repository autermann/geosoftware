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
package org.sloth.service;

import java.util.Collection;
import org.sloth.model.Coordinate;
import org.sloth.model.Observation;
import org.sloth.model.ObservationCategorie;
import org.sloth.model.User;
import org.sloth.persistence.ObservationCategorieDao;
import org.sloth.persistence.ObservationDao;

public interface ObservationManager {

	public void setObservationDao(ObservationDao oDao);

	public void setObservationCategorieDao(ObservationCategorieDao ocDao);

	public ObservationDao getObservationDao();

	public ObservationCategorieDao getObservationCategorieDao();

	public Observation getObservation(int id);

	public Collection<Observation> getObservations();

	public Collection<Observation> getObservations(ObservationCategorie oc);

	public Collection<Observation> getObservations(String keyword);

	public void deleteObservation(int id);

	public void deleteObservation(Observation observation);

	public void updateObservation(Observation observation);

	public void registrateObservation(String title, String description,
									  User user, Coordinate coordinate);

}
