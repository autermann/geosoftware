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
package org.sloth.service;

import java.util.Collection;
import org.sloth.exceptions.ConstraintViolationException;
import org.sloth.model.User;

/**
 * Service interface for handling {@code User}s.
 * 
 * @see User
 * @author Christian Autermann
 */
public interface UserService {

	/**
	 * Returns all {@code User}s known by the system. If no {@code User}s are
	 * known an empty {@code Collection} will be returned.
	 * 
	 * @return all {@code User}s
	 */
	public Collection<User> getUsers();

	/**
	 * Returns the {@code User} with the specified mail address. If no {@code
	 * User} is found {@code null} will be returned.
	 * 
	 * @param mail
	 *            the mail address
	 * @return the {@code User} or {@code null}
	 * @throws NullPointerException
	 *             if {@code mail} is {@code null}
	 */
	public User get(String mail) throws NullPointerException;

	/**
	 * Returns the {@code User} with the specified id. If no {@code User} is
	 * found {@code null} will be returned.
	 * 
	 * @param id
	 *            the id
	 * @return the {@code User} or {@code null}
	 * @throws NullPointerException
	 *             if {@code id} is {@code null}
	 */
	public User get(Long id) throws NullPointerException;

	/**
	 * Deletes the {@code User} with the specified id.
	 * 
	 * @param id
	 *            the id
	 * @throws NullPointerException
	 *             if {@code id} is {@code null}
	 * @throws IllegalArgumentException
	 *             if no matching {@code User} exists in the system
	 */
	public void delete(Long id) throws NullPointerException,
			IllegalArgumentException;

	/**
	 * Deletes a {@code User}.
	 * 
	 * @param user
	 *            the {@code User}
	 * @throws NullPointerException
	 *             if {@code user} is {@code null}
	 * @throws IllegalArgumentException
	 *             if {@code user} is not know by the system.
	 */
	public void delete(User user) throws NullPointerException,
			IllegalArgumentException;

	/**
	 * Adds a new {@code User} to the system.
	 * 
	 * @param u
	 *            the {@code User}
	 * @throws NullPointerException
	 *             if {@code u} is {@code null}
	 * @throws IllegalArgumentException
	 *             if {@code u} is already known by the system
	 * @throws ConstraintViolationException
	 *             if {@code u} violates a system constraint
	 */
	public void registrate(User u) throws NullPointerException,
			IllegalArgumentException, ConstraintViolationException;

	/**
	 * Merges the changes made to {@code u} into the system.
	 * 
	 * @param u
	 *            the {@code User}
	 * @throws NullPointerException
	 *             if {@code u} is {@code null}
	 * @throws IllegalArgumentException
	 *             if {@code u} is not known by the system
	 * @throws ConstraintViolationException
	 *             if {@code u} violates a system constraint
	 */
	public void update(User u) throws NullPointerException,
			IllegalArgumentException, ConstraintViolationException;

	/**
	 * Tests if a matching {@code User} exists in the system and return the
	 * found {@code User}. If no {@code User} found, {@code null} will be
	 * returned.
	 * 
	 * @param mail the mail address
	 * @param plainPassword the plain password
	 * @return the {@code User} or {@code null}
	 * @throws NullPointerException
	 *             if {@code login} is {@code null}
	 */
	public User login(String mail, String plainPassword) throws NullPointerException;

	public boolean isMailAddressAvailable(String mail) throws NullPointerException;

}
