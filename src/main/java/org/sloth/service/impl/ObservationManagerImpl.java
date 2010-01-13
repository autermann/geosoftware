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
package org.sloth.service.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import org.sloth.persistence.ObservationDao;
import org.sloth.pojo.Coordinate;
import org.sloth.pojo.Observation;
import org.sloth.pojo.ObservationCategorie;
import org.sloth.pojo.User;
import org.sloth.service.ObservationManager;
import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

public class ObservationManagerImpl implements ObservationManager {

	private ObservationDao oDao;

	private ObservationDao getDAO() {
		return oDao;
	}

	public ObservationManagerImpl(ObservationDao oDao) {
		this.oDao = oDao;
	}

	@Override
	public Observation getObservation(int id) {
		return getDAO().get(id);
	}

	@Override
	public Collection<Observation> getObservations() {
		return getDAO().getAll();
	}

	@Override
	public Collection<Observation> getObservations(String keyword) {
		String regex = buildRegex(keyword);
		HashSet<Observation> result = new HashSet<Observation>();
		for (Observation o : getObservations()) {
			ObservationCategorie oc = o.getObservationCategorie();
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
	public void deleteObservation(int id) {
		getDAO().delete(id);
	}

	@Override
	public void deleteObservation(Observation observation) {
		getDAO().delete(observation);
	}

	@Override
	public void updateObservation(Observation observation) {
		getDAO().update(observation);
	}

	@Override
	public void registrateObservation(String title, String description,
									  User user, Coordinate coordinate) {
		Observation o = new Observation();
		o.setCoordinate(coordinate);
		o.setDescription(description);
		o.setTitle(title);
		o.setUser(user);
		o.setTimestamp(Calendar.getInstance());
		getDAO().save(o);
	}

	private String buildRegex(String keyword) {
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
	public Collection<Observation> getObservations(ObservationCategorie oc) {
		Collection<Observation> result = new LinkedList<Observation>();
		for (Observation o : getObservations()) {
			if (o.getObservationCategorie().equals(oc)) {
				result.add(o);
			}
		}
		return result;
	}

}
