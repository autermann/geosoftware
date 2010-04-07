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

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.sloth.exceptions.ConstraintViolationException;
import org.sloth.exceptions.FieldLengthConstraintViolationException;
import org.sloth.exceptions.NotNullConstraintViolationException;

/**
 * Represents an non hierachical <code>Categorie</code> for Observation.
 * 
 * @see Observation
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
@Entity(name = "CATEGORIES")
public class Categorie extends BaseEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = -3532326782916715208L;
	@Column(nullable = false, unique = true)
	private String title;
	@Column(nullable = false, length = 1000)
	private String description;
	@Column(nullable = false)
	private String iconFileName;

	/**
	 * Creates a new <code>Categorie</code> with <code>null</code> as default
	 * value.
	 */
	public Categorie() {
		/* nothing to do here */
	}

	/**
	 * Creates a new <code>Categorie</code> with specified title and description
	 * 
	 * @param title
	 *            the title
	 * @param description
	 *            the description
	 * @param iconFileName
	 *            the iconFileName
	 */
	public Categorie(String title, String description, String iconFileName) {
		this.title = title;
		this.iconFileName = iconFileName;
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof Categorie) {
			return this.hashCode() == o.hashCode();
		} else {
			return false;
		}
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the iconFileName
	 */
	public String getIconFileName() {
		return iconFileName;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 41 * hash + this.getVersion();
		hash = 41 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
		hash = 41
				* hash
				+ (this.iconFileName != null ? this.iconFileName.hashCode() : 0);
		hash = 41 * hash + (this.title != null ? this.title.hashCode() : 0);
		hash = 41 * hash
				+ (this.description != null ? this.description.hashCode() : 0);
		return hash;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param iconFileName
	 *            the iconFileName to set
	 */
	public void setIconFileName(String iconFileName) {
		this.iconFileName = iconFileName;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return this.getTitle();
	}

	@Override
	public void validate() throws ConstraintViolationException {
		if (this.description == null || this.title == null
				|| this.iconFileName == null || this.iconFileName.isEmpty()
				|| this.description.isEmpty() || this.title.isEmpty()) {
			throw new NotNullConstraintViolationException();
		}
		if (this.description.length() > 1000 || this.title.length() > 255
				|| this.iconFileName.length() > 255) {
			throw new FieldLengthConstraintViolationException();
		}
	}

	@Override
	public Object clone() {
		Categorie c = new Categorie();
		c.setDescription(description);
		c.setIconFileName(iconFileName);
		c.setTitle(title);
		c.setVersion(getVersion());
		c.setId(new Long(getId()));
		return c;
	}
}
