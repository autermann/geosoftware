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
import org.sloth.model.Observation;
import org.sloth.model.Categorie;

//TODO javadoc
public interface ObservationService {

	public Observation getObservation(int id);

	public Categorie getCategorie(int id);

	public Collection<Observation> getObservations();

	public Collection<Observation> getObservations(Categorie oc);

	public Collection<Observation> getObservations(String keyword);

	public Collection<Categorie> getCategories();

	public void deleteObservation(int id);

	public void deleteObservation(Observation observation);

	public void deleteCategorie(Categorie categorie);

	public void deleteCategorie(int id);

	public void updateCategorie(Categorie categorie);

	public void updateObservation(Observation observation);

	public void registrate(Categorie categorie);

	public void registrate(Observation observation);

}
