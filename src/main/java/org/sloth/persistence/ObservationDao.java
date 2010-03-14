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
public interface ObservationDao {

	/**
	 * Query for all {@code Observation}s.
	 *
	 * @return all {@code Observation}s found
	 */
	public Collection<Observation> getAll();

	/**
	 * Query for a {@code Observation} with a known {@code id}.
	 *
	 * @param id the id
	 * @return the {@code Observation} with the specified id, if no
	 * matching {@code Observation} found {@code null} is returned.
	 */
	public Observation get(long id);

	/**
	 * Update a {@code Observation}. Invoking this method with an 
	 * {@code Observation} not known by the database will cause an
	 * {@code IllegalArgumentException}.
	 *
	 * @param t the {@code Observation} to be updated
	 * @throws NullPointerException if {@code t} is {@code null}
	 * @throws IllegalArgumentException if {@code t} is not found in
	 * the database.
	 */
	public void update(Observation t) throws NullPointerException,
			IllegalArgumentException;

	/**
	 * Delete a {@code Observation} from database. Invoking this method with an 
	 * {@code Observation} not known by the database will cause an
	 * {@code IllegalArgumentException}.
	 *
	 * @param t the {@code Observation} to be deleted
	 * @throws NullPointerException if {@code t} is {@code null}
	 * @throws IllegalArgumentException if {@code t} is not found in the
	 * database.
	 */
	public void delete(Observation t) throws NullPointerException,
			IllegalArgumentException;


	/**
	 * Save a {@code Observation} in the database. {@link Observation#id} will
	 * be generated.
	 *
	 * @param t the {@code Observation} to be saved
	 * @throws NullPointerException if {@code t} is {@code null}
	 */
	public void save(Observation t) throws NullPointerException;

	/**
	 * Query for all {@code Observation}s in a {@code Categorie}.
	 *
	 * @param c the {@code Categorie}
	 * @return all {@code Observation}s in {@code c}
	 * @throws NullPointerException if {@code c} is {@code null}
	 * @throws IllegalArgumentException if {@code c} is not in the database.
	 */
	public Collection<Observation> get(Categorie c) throws NullPointerException,
			IllegalArgumentException;

	/**
	 * Query for all {@code Observation}s created by a {@code User}.
	 * 
	 * @param u the {@code User}
	 * @return all {@code Observation}s created by {@code u}
	 * @throws NullPointerException if {@code u} is {@code null}
	 * @throws IllegalArgumentException if {@code u} is not in the database.
	 */
	public Collection<Observation> get(User u) throws NullPointerException,
			IllegalArgumentException;
}
