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

import org.sloth.model.User;

/**
 * Data Access Object for {@link User}.
 *	
 * @author Christian Autermann
 */
public interface UserDao extends Dao<User>{

	/**
	 * Query for a {@code User} with known mail address.
	 *
	 * @param mail the {@code mail}-address
	 * @return the matching {@code User}, or {@code null} if not found
	 * @throws NullPointerException if {@code mail} is {@code null}
	 * @throws IllegalArgumentException if {@code u} is not in the database.
	 */
	public User getByMail(String mail);
}
