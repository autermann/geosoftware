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

import org.sloth.model.User;

/**
 * Data Access Object for {@link User}.
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
public interface UserDao {

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
	public void delete(Collection<User> t) throws NullPointerException,
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
	public void delete(User t) throws NullPointerException,
			IllegalArgumentException;

	/**
	 * Query for all Entities.
	 * 
	 * @return all Entities found
	 */
	public Collection<User> getAll();

	/**
	 * Query for a Entity with a known {@code id}.
	 * 
	 * @param id
	 *            the id
	 * @return the @code Entity with the specified id, if no matching Entity
	 *         found {@code null} is returned.
	 */
	public User getById(Long id);

	/**
	 * Query for a {@code User} with known mail address.
	 * 
	 * @param mail
	 *            the {@code mail}-address
	 * @return the matching {@code User}, or {@code null} if not found
	 * @throws NullPointerException
	 *             if {@code mail} is {@code null}
	 * @throws IllegalArgumentException
	 *             if {@code u} is not in the database.
	 */
	public User getByMail(String mail);

	/**
	 * Save a {@code User} in the database. {@link User#id} will be generated.
	 * 
	 * @param t
	 *            the {@code User} to be saved
	 * @throws NullPointerException
	 *             if {@code t} is {@code null}
	 */
	public void save(User t) throws NullPointerException;

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
	public void update(User t) throws NullPointerException,
			IllegalArgumentException;

}
