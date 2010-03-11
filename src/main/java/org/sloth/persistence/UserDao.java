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
 * @todo
 * @author auti
 */
public interface UserDao {

	/**
	 * @todo
	 * @return
	 */
	public Collection<User> getAll();

	/**
	 * @todo
	 * @param id
	 * @return
	 */
	public User get(long id);

	/**
	 * @todo
	 * @param t
	 */
	public void update(User t);

	/**
	 * @todo
	 * @param t
	 */
	public void delete(User t);

	/**
	 * @todo
	 * @param t
	 */
	public void save(User t);

	/**
	 * @todo
	 */
	public void flush();

	/**
	 * @todo
	 * @param id
	 */
	public void delete(long id);

	/**
	 * @todo
	 * @param mail
	 * @return
	 */
	public User get(String mail);

}
