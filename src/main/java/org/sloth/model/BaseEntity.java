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
package org.sloth.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class BaseEntity {

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Returns 0 if the id is <code>null</code> and the id otherwise
	 * @return the id
	 */
	public long getId() {
		return (this.id == null) ? 0 : this.id;
	}

	/**
	 * @param id the id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Returns wether this {@code BaseEntity} has an Id.
	 * @return <code>true</code> if the id is
	 * <code>null</code> and <code>false</code>
	 * otherwise.
	 *
	 * @return if this {@code Categorie} is new
	 */
	public boolean isNew() {
		return (this.id == null);
	}
}
