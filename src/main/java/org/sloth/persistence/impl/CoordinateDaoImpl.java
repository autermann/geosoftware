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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.sloth.persistence.CoordinateDao;
import org.sloth.model.Coordinate;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CoordinateDaoImpl implements CoordinateDao {
	EntityManager em;

	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	private EntityManager getEntityManager() {
        return em;
    }

	@Override
	public Coordinate get(long id) {
		return getEntityManager().find(Coordinate.class, id);
	}

	@Override
	public void update(Coordinate o) {
		getEntityManager().merge(o);
	}

	@Override
	public void save(Coordinate o) {
		getEntityManager().persist(o);
	}

	@Override
	public void delete(Coordinate c) {
		getEntityManager().remove(c);
	}

	@Override
	public void delete(long id) {
		delete(get(id));
	}

}
