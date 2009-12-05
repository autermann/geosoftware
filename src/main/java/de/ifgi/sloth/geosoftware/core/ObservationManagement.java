/*
 * Copyright (C) 2009  Stefan Arndt, Christian Autermann, Dustin Demuth,
 *                     Christoph Fendrich, Christian Paluschek
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
package de.ifgi.sloth.geosoftware.core;

import de.ifgi.sloth.geosoftware.data.BoundingBox;
import de.ifgi.sloth.geosoftware.data.Observation;
import de.ifgi.sloth.geosoftware.data.ObservationCategorie;
import de.ifgi.sloth.geosoftware.data.User;
import de.ifgi.sloth.geosoftware.exceptions.NotAuthorizedException;
import de.ifgi.sloth.geosoftware.frontend.Session;
import java.util.Calendar;
import java.util.Collection;

/**
 *
 * @author Christian Autermann
 */
public class ObservationManagement {

	/**
	 * 
	 * @param session
	 * @return
	 */
	public static ObservationManagement getInstance(Session session) {
		return null;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Observation getObservationById(long id) {
		return null;
	}

	/**
	 * 
	 * @param oc
	 * @return
	 */
	public Collection<Observation> getObservationsByCategorie(ObservationCategorie oc) {
		return null;
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public Collection<Observation> getObservationsByKeyword(String s) {
		return null;
	}

	/**
	 * 
	 * @param u
	 * @return
	 * @throws NotAuthorizedException
	 */
	public Collection<Observation> getObservationsByUser(User u) throws NotAuthorizedException {
		return null;
	}

	/**
	 * 
	 * @param after
	 * @param before
	 * @return
	 */
	public Collection<Observation> getObservationsByDate(Calendar after, Calendar before) {
		return null;
	}

	/**
	 * 
	 * @param coverage
	 * @return
	 */
	public Collection<Observation> getObservationsByCoverage(BoundingBox coverage) {
		return null;
	}

	/**
	 * 
	 * @param oc
	 * @param title
	 * @param description
	 * @return
	 * @throws NotAuthorizedException
	 */
	public Observation addObservation(ObservationCategorie oc, String title, String description) throws NotAuthorizedException {
		return null;
	}

	/**
	 * 
	 * @param o
	 * @param reason
	 * @throws NotAuthorizedException
	 */
	public void reportObservation(Observation o, String reason) throws NotAuthorizedException {
	}

	/**
	 * 
	 * @param o
	 * @throws NotAuthorizedException
	 */
	public void deleteObservation(Observation o) throws NotAuthorizedException {
	}

	/**
	 * 
	 * @param o
	 * @throws NotAuthorizedException
	 */
	public void updateObservation(Observation o) throws NotAuthorizedException {
	}
}
