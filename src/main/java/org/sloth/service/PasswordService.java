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

import org.sloth.model.User;

/**
 * Service interface for handling passwords.
 * 
 * @see User#setPassword(java.lang.String)
 * @see User#getPassword()
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
public interface PasswordService {

	/**
	 * Checks wether the given password are the same.
	 * 
	 * @param hash
	 *            the hashed password
	 * @param plain
	 *            the plain password
	 * @return {@code true} if the hash of the plain password is equal to the
	 *         hashed password, otherwise {@code false}
	 */
	public boolean check(String hash, String plain);

	/**
	 * Hashs a plain text password.
	 * <p>
	 * Invoking this method on the same plain {@code String} will allways return
	 * the same hash value. This method ensures this even in different VM
	 * sessions.
	 * </p>
	 * <p>
	 * Conflicts (two different plain {@code String}s have the same hash value)
	 * shall be avoided because of security concerns.
	 * </p>
	 * 
	 * @param plain
	 *            the password
	 * @return the password hash or {@code null} if {@code} password was {@code
	 *         null}
	 */
	public String hash(String plain);

	/**
	 * Tests the strength of a plain text password.
	 * 
	 * @param plain
	 *            the password
	 * @return {@code true} if it is strong enough, {@code false} otherwise
	 */
	public boolean meetsRecommendation(String plain);
}
