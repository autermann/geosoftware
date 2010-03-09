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
package org.sloth.persistence.impl;

import java.util.Collection;
import org.sloth.persistence.ObservationDao;
import org.sloth.model.Observation;

public class ObservationDaoImpl extends EntityManagerDao implements ObservationDao {

	@Override
	public Collection<Observation> getAll() {
		return getEntityManager().createQuery("SELECT o FROM OBSERVATION o").getResultList();
	}

	@Override
	public Observation get(long id) {
		return getEntityManager().find(Observation.class, id);
	}

	@Override
	public void save(Observation o) {
		getEntityManager().persist(o);
		getEntityManager().flush();
	}

	@Override
	public void update(Observation o) {
		getEntityManager().merge(o);
	}

	@Override
	public void delete(long id) {
		delete(get(id));
	}

	@Override
	public void delete(Observation o) {
		getEntityManager().remove(this);
	}

	@Override
	public void flush() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
