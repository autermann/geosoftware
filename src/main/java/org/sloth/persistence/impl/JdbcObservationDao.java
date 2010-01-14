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
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class JdbcObservationDao extends SimpleJdbcDaoSupport implements
		ObservationDao {

	@Override
	public Collection<Observation> getAll() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Observation get(int id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void save(Observation o) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void update(Observation o) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void delete(int id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void delete(Observation o) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
