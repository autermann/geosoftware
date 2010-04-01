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

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import org.sloth.exceptions.ConstraintViolationException;

/**
 * Represtents an Entity with a {@code Long} as Id.
 * 
 * @author Christian Autermann
 */
@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	@Column(name = "VERSION")
	private int version;

	/**
	 * Validates this Entity.
	 *
	 * @throws ConstraintViolationException
	 *             if a database constraint is violated.
	 */
	@PrePersist
	@PreUpdate
	public abstract void validate() throws ConstraintViolationException;

	/**
	 * Returns 0 if the id is <code>null</code> and the id otherwise
	 * 
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Returns wether this {@code BaseEntity} has an Id.
	 * 
	 * @return <code>true</code> if the id is <code>null</code> and
	 *         <code>false</code> otherwise.
	 */
	public boolean isNew() {
		return (this.id == null);
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}
}
