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
package org.sloth.persistence.inmemory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;
import org.sloth.model.Categorie;
import org.sloth.model.Observation;
import org.sloth.model.User;
import org.sloth.persistence.ObservationDao;

public class InMemoryObservationDao extends InMemoryDao<Observation> implements
		ObservationDao {

	@Override
	public Collection<Observation> get(Categorie c) {
		if (c == null) {
			throw new NullPointerException();
		}
		Collection<Observation> result = new ArrayList<Observation>();
		for (Entry<Long, Observation> entry : getEntrySet()) {
			if (entry.getValue().getCategorie().equals(c)) {
				result.add(entry.getValue());
			}
		}
		return result;
	}

	@Override
	public Collection<Observation> get(User u) throws NullPointerException {
		if (u == null) {
			throw new NullPointerException();
		}
		Collection<Observation> result = new ArrayList<Observation>();
		for (Entry<Long, Observation> entry : getEntrySet()) {
			if (entry.getValue().getUser().equals(u)) {
				result.add(entry.getValue());
			}
		}
		return result;
	}

}
