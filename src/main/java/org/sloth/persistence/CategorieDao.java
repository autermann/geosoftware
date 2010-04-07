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
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
public interface CategorieDao {

	/**
	 * Delete a Entity from database. Invoking this method with an Entity not
	 * known by the database will cause an {@code IllegalArgumentException}.
	 * 
	 * @param t
	 *            the Entity to be deleted
	 * @throws NullPointerException
	 *             if {@code t} is {@code null}
	 * @throws IllegalArgumentException
	 *             if {@code t} is not found in the database.
	 */
	public void delete(Categorie t) throws NullPointerException,
			IllegalArgumentException;

	/**
	 * Delete Entities from database. Invoking this method with Entities not
	 * known by the database will cause an {@code IllegalArgumentException}.
	 * 
	 * @param t
	 *            the Entities to be deleted
	 * @throws NullPointerException
	 *             if {@code t} or {@code t}'s content is {@code null}
	 * @throws IllegalArgumentException
	 *             if Entities are not found in the database.
	 */
	public void delete(Collection<Categorie> t) throws NullPointerException,
			IllegalArgumentException;

	/**
	 * Query for all Entities.
	 * 
	 * @return all Entities found
	 */
	public Collection<Categorie> getAll();

	/**
	 * Query for a Entity with a known {@code id}.
	 * 
	 * @param id
	 *            the id
	 * @return the @code Entity with the specified id, if no matching Entity
	 *         found {@code null} is returned.
	 */
	public Categorie getById(Long id);

	/**
	 * Returns the {@code Categorie} with given {@code title}.
	 * 
	 * @param title
	 *            the title
	 * @return the matching {@code Catgeorie} or {@code null}
	 * @throws NullPointerException
	 *             if {@code title} is {@code null}
	 * 
	 */
	public Categorie getByTitle(String title) throws NullPointerException;

	/**
	 * Save a categorie in the database. {@link Categorie#id} will be generated.
	 * 
	 * @param t
	 *            the {@code Categorie} to be saved
	 * @throws NullPointerException
	 *             if {@code t} is {@code null}
	 */
	public void save(Categorie t) throws NullPointerException;

	/**
	 * Update a Entity. Invoking this method with an Entity not known by the
	 * database will cause an {@code IllegalArgumentException}.
	 * 
	 * @param t
	 *            the Entity to be updated
	 * @throws NullPointerException
	 *             if {@code t} is {@code null}
	 * @throws IllegalArgumentException
	 *             if {@code t} is not found in the database.
	 */
	public void update(Categorie t) throws NullPointerException,
			IllegalArgumentException;

}
