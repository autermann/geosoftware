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
import java.util.List;
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
	 * Query for all Entities.
	 *
	 * @return all Entities found
	 */
	public Collection<Observation> getAll();

	/**
	 * Query for a Entity with a known {@code id}.
	 *
	 * @param id
	 *            the id
	 * @return the @code Entity with the specified id, if no matching Entity
	 *         found {@code null} is returned.
	 */
	public Observation getById(Long id);

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
	public void update(Observation t) throws NullPointerException,
											 IllegalArgumentException;

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
	public void delete(Observation t) throws NullPointerException,
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
	public void delete(Collection<Observation> t) throws NullPointerException,
														 IllegalArgumentException;

	/**
	 * Save a {@code Observation} in the database. {@link Observation#id} will
	 * be generated.
	 *
	 * @param t
	 *            the {@code Observation} to be saved
	 * @throws NullPointerException
	 *             if {@code t} is {@code null}
	 */
	public void save(Observation t) throws NullPointerException;

	/**
	 * Query for all {@code Observation}s in a {@code Categorie}.
	 * 
	 * @param c
	 *            the {@code Categorie}
	 * @return all {@code Observation}s in {@code c}
	 * @throws NullPointerException
	 *             if {@code c} is {@code null}
	 * @throws IllegalArgumentException
	 *             if {@code c} is not in the database.
	 */
	public Collection<Observation> getByCategorie(Categorie c)
			throws NullPointerException, IllegalArgumentException;

	/**
	 * Query for all {@code Observation}s created by a {@code User}.
	 * 
	 * @param u
	 *            the {@code User}
	 * @return all {@code Observation}s created by {@code u}
	 * @throws NullPointerException
	 *             if {@code u} is {@code null}
	 * @throws IllegalArgumentException
	 *             if {@code u} is not in the database.
	 */
	public Collection<Observation> getByUser(User u)
			throws NullPointerException, IllegalArgumentException;

	/**
	 * Query for the newest {@code Observation}s. If less then {@code count}
	 * {@code Observation}s are available all {@code Observation}s will be
	 * returned.
	 * 
	 * @param count
	 *            specifies how many {@code Observation}s will be returned.
	 * @return {@code count} or less {@code Observation}s; orderd by their
	 *         {@code creationDate}
	 * @see Observation#getCreationDate()
	 * 
	 */
	public List<Observation> getNewestObservations(int count);

	public Collection<Observation> getByKeyWord(String keyword)
			throws NullPointerException;

}
