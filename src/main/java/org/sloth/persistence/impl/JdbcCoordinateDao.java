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

import org.sloth.persistence.CoordinateDao;
import org.sloth.model.Coordinate;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class JdbcCoordinateDao extends SimpleJdbcDaoSupport implements
		CoordinateDao {

	@Override
	public void get(int id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void update(Coordinate o) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void save(Coordinate o) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void delete(Coordinate c) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void delete(int id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
