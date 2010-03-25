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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.sloth.model.Categorie;
import org.sloth.model.Observation;
import org.sloth.model.User;
import org.sloth.persistence.ObservationDao;

public class InMemoryObservationDao extends InMemoryDao<Observation> implements
		ObservationDao {

	@Override
	public Collection<Observation> getByCategorie(Categorie c) {
		if (c == null)
			throw new NullPointerException();
		Collection<Observation> result = new ArrayList<Observation>();
		for (Observation o : getAll())
			if (o.getCategorie().equals(c))
				result.add(o);
		return result;
	}

	@Override
	public Collection<Observation> getByUser(User u) throws NullPointerException {
		if (u == null)
			throw new NullPointerException();
		Collection<Observation> result = new ArrayList<Observation>();
		for (Observation o : getAll())
			if (o.getUser().equals(u))
				result.add(o);
		return result;
	}

	@Override
	public List<Observation> getNewestObservations(int count) {
		List<Observation> obs = getAll();
		Collections.sort(obs, new Comparator<Observation>() {

			@Override
			public int compare(Observation o1,
							   Observation o2) {
				return o1.getCreationDate().compareTo(o2.getCreationDate());
			}
		});
		if (!obs.isEmpty())
			return obs.subList(0, ((obs.size() < count) ? obs.size() : count)
								  - 1);
		else
			return obs;
	}
}