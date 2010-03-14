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

/**
 * Data Access Object for {@link Categorie}.
 * 
 * @author Christian Autermann
 */
public interface CategorieDao {

	/**
	 * Query for all {@code Categorie}s.
	 * @return all {@code Categorie}s found
	 */
	public Collection<Categorie> getAll();

	/**
	 * Query for a {@code Categorie} with a known {@code id}.
	 * @param id the id
	 * @return the {@code Categorie} with the specified id, if no
	 * matching {@code Categorie} found {@code null} is returned.
	 */
	public Categorie get(long id);

	/**
	 * Update a {@code Categorie}. Invoking this method with an {@code Categorie} not known by
	 * the database will cause an {@code IllegalArgumentException}.
	 * @param t the {@code Categorie} to be updated
	 * @throws NullPointerException if {@code t} is {@code null}
	 * @throws IllegalArgumentException if {@code t} is not found in the database.
	 */
	public void update(Categorie t) throws NullPointerException, IllegalArgumentException;

	/**
	 * Delete a {@code Categorie} from database. Invoking this method with an {@code Categorie}
	 * not known by the database will cause an {@code IllegalArgumentException}.
	 * @param t the {@code Categorie} to be deleted
	 * @throws NullPointerException if {@code t} is {@code null}
	 * @throws IllegalArgumentException if {@code t} is not found in the database.
	 */
	public void delete(Categorie t) throws NullPointerException, IllegalArgumentException;

	/**
	 * Save a categorie in the database. {@link Categorie#id} will be generated.
	 * @param t the {@code Categorie} to be saved
	 * @throws NullPointerException if {@code t} is {@code null}
	 */
	public void save(Categorie t) throws NullPointerException;

}
