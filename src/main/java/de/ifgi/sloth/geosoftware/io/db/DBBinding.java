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
package de.ifgi.sloth.geosoftware.io.db;

import de.ifgi.sloth.geosoftware.data.BoundingBox;
import de.ifgi.sloth.geosoftware.data.Observation;
import de.ifgi.sloth.geosoftware.data.ObservationCategorie;
import de.ifgi.sloth.geosoftware.data.User;
import java.util.Calendar;
import java.util.Collection;

/**
 *
 * @author Christian Autermann
 */
public interface DBBinding {

	/**
	 * 
	 * @return
	 */
	public DBBinding getInstance();

	/**
	 * 
	 * @return
	 */
	public Collection<Observation> getObservations();

	/**
	 *
	 * @param id
	 * @return
	 */
	public Observation getObservationById(long id);

	/**
	 *
	 * @param oc
	 * @return
	 */
	public Collection<Observation> getObservationsByCategorie(ObservationCategorie oc);

	/**
	 *
	 * @param s
	 * @return
	 */
	public Collection<Observation> getObservationsByKeyword(String s);

	/**
	 *
	 * @param u
	 * @return
	 */
	public Collection<Observation> getObservationsByUser(User u);

	/**
	 *
	 * @param after
	 * @param before
	 * @return
	 */
	public Collection<Observation> getObservationsByDate(Calendar after, Calendar before);

	/**
	 *
	 * @param coverage
	 * @return
	 */
	public Collection<Observation> getObservationsByCoverage(BoundingBox coverage);

	/**
	 *
	 *
	 * @param o 
	 */
	public void addObservation(Observation o);

	/**
	 *
	 * @param o
	 * @param reason
	 */
	public void reportObservation(Observation o, String reason);

	/**
	 *
	 * @param o
	 */
	public void deleteObservation(Observation o);

	/**
	 *
	 * @param o
	 */
	public void updateObservation(Observation o);

	/**
	 *
	 * @param u
	 */
	public void deleteUser(User u);

	/**
	 * 
	 * @param u 
	 */
	public void updateUser(User u);

	/**
	 *
	 * @return
	 */
	public Collection<User> getUserList();

	/**
	 *
	 * @param u
	 */
	public void addUser(User u);

	/**
	 *
	 * @param mail
	 * @return
	 */
	public User getUserByEmail(String mail);
}
