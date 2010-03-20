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
package org.sloth.persistence;

import java.util.Collection;
import org.sloth.model.Categorie;
import org.sloth.model.Observation;
import org.sloth.model.User;

/**
 * Data Access Object for {@link Observation}.
 *
 * @author Christian Autermann
 */
public interface ObservationDao extends Dao<Observation> {

	/**
	 * Query for all {@code Observation}s in a {@code Categorie}.
	 *
	 * @param c the {@code Categorie}
	 * @return all {@code Observation}s in {@code c}
	 * @throws NullPointerException if {@code c} is {@code null}
	 * @throws IllegalArgumentException if {@code c} is not in the database.
	 */
	public Collection<Observation> getByCategorie(Categorie c) throws NullPointerException,
			IllegalArgumentException;

	/**
	 * Query for all {@code Observation}s created by a {@code User}.
	 * 
	 * @param u the {@code User}
	 * @return all {@code Observation}s created by {@code u}
	 * @throws NullPointerException if {@code u} is {@code null}
	 * @throws IllegalArgumentException if {@code u} is not in the database.
	 */
	public Collection<Observation> getByUser(User u) throws NullPointerException,
			IllegalArgumentException;
}
