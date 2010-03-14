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
 */
public interface UserDao {

	/**
	 * Query for all {@code User}s.
	 * 
	 * @return all {@code User}s found
	 */
	public Collection<User> getAll();

	/**
	 * Query for a {@code User} with a known {@code id}.
	 *
	 * @param id the id
	 * @return the {@code User} with the specified id, if no 
	 * matching {@code User} found {@code null} is returned.
	 */
	public User get(long id);

	/**
	 * Update a {@code User}. Invoking this method with an
	 * {@code User} not known by the database will cause an
	 * {@code IllegalArgumentException}.
	 *
	 * @param t	the {@code User} to be updated
	 * @throws NullPointerException	if {@code t} is {@code null}
	 * @throws IllegalArgumentException	if {@code t} is not found in
	 * the database.
	 */
	public void update(User t)throws NullPointerException,
			IllegalArgumentException;

	/**
	 * Delete a {@code User} from database. Invoking this method with an
	 * {@code User} not known by the database will cause an
	 * {@code IllegalArgumentException}.
	 *
	 * @param t the {@code User} to be deleted
	 * @throws NullPointerException if {@code t} is {@code null}
	 * @throws IllegalArgumentException if {@code t} is not found in the
	 * database.
	 */
	public void delete(User t)throws NullPointerException,
			IllegalArgumentException;

	/**
	 * Save a {@code User} in the database. {@link User#id} will
	 * be generated.
	 *
	 * @param t the {@code User} to be saved
	 * @throws NullPointerException if {@code t} is {@code null}
	 */
	public void save(User t) throws NullPointerException;

	/**
	 * Query for a {@code User} with known mail address.
	 *
	 * @param mail the {@code mail}-address
	 * @return the matching {@code User}, or {@code null} if not found
	 * @throws NullPointerException if {@code mail} is {@code null}
	 * @throws IllegalArgumentException if {@code u} is not in the database.
	 */
	public User get(String mail);
}
