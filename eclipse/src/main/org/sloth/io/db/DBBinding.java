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
package org.sloth.io.db;

import org.sloth.data.BoundingBox;
import org.sloth.data.Observation;
import org.sloth.data.ObservationCategorie;
import org.sloth.data.User;
import org.sloth.util.Configuration;
import org.sloth.util.Log;
import java.util.Calendar;
import java.util.Collection;

/**
 *
 * @author Christian Autermann
 */
public abstract class DBBinding {

	private static DBBinding binding = null;

	/**
	 * 
	 * @return
	 */
	public static DBBinding getInstance() {
		if (binding == null){
			try {
				binding = (DBBinding) Class.forName(Configuration.get("DATABASE_INTERFACE")).newInstance();
			} catch (Exception e) {
				Log.throwing(e);
				System.exit(1);
			}
		}
		return binding;
	}

	/**
	 * 
	 * @return
	 */
	public abstract Collection<Observation> getObservations();

	/**
	 *
	 * @param id
	 * @return
	 */
	public abstract Observation getObservationById(long id);

	/**
	 *
	 * @param oc
	 * @return
	 */
	public abstract Collection<Observation> getObservationsByCategorie(ObservationCategorie oc);

	/**
	 *
	 * @param s
	 * @return
	 */
	public abstract Collection<Observation> getObservationsByKeyword(String s);

	/**
	 *
	 * @param u
	 * @return
	 */
	public abstract Collection<Observation> getObservationsByUser(User u);

	/**
	 *
	 * @param after
	 * @param before
	 * @return
	 */
	public abstract Collection<Observation> getObservationsByDate(Calendar after, Calendar before);

	/**
	 *
	 * @param coverage
	 * @return
	 */
	public abstract Collection<Observation> getObservationsByCoverage(BoundingBox coverage);

	/**
	 *
	 *
	 * @param o 
	 */
	public abstract void addObservation(Observation o);

	/**
	 *
	 * @param o
	 * @param reason
	 */
	public abstract void reportObservation(Observation o, String reason);

	/**
	 *
	 * @param o
	 */
	public abstract void deleteObservation(Observation o);

	/**
	 *
	 * @param o
	 */
	public abstract void updateObservation(Observation o);

	/**
	 *
	 * @param u
	 */
	public abstract void deleteUser(User u);

	/**
	 * 
	 * @param u 
	 */
	public abstract void updateUser(User u);

	/**
	 *
	 * @return
	 */
	public abstract Collection<User> getUserList();

	/**
	 *
	 * @param u
	 */
	public abstract void addUser(User u);

	/**
	 *
	 * @param mail
	 * @return
	 */
	public abstract User getUserByEmail(String mail);
}
