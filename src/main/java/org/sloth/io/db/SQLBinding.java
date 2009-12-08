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
import java.util.Calendar;
import java.util.Collection;

/**
 *
 * @author Christian Autermann
 */
public class SQLBinding extends DBBinding {

	/**
	 * 
	 */
	public SQLBinding(){
		
	}

	@Override
	public Collection<Observation> getObservations() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Observation getObservationById(long id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Collection<Observation> getObservationsByCategorie(ObservationCategorie oc) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Collection<Observation> getObservationsByKeyword(String s) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Collection<Observation> getObservationsByUser(User u) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Collection<Observation> getObservationsByDate(Calendar after, Calendar before) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Collection<Observation> getObservationsByCoverage(BoundingBox coverage) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void addObservation(Observation o) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void reportObservation(Observation o, String reason) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void deleteObservation(Observation o) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void updateObservation(Observation o) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void deleteUser(User u) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void updateUser(User u) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Collection<User> getUserList() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void addUser(User u) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public User getUserByEmail(String mail) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
