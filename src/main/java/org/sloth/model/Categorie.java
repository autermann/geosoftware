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
 * @version 1.0
 * @since 1.0
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
	 * Creates a new <code>Categorie</code> with specified title and description
	 * @param title the title
	 * @param description the description
	 */
	public Categorie(String title, String description) {
		setTitle(title);
		setDescription(description);
	}

	/**
	 * Creates a new <code>Categorie</code> with <code>null</code> as default value.
	 */
	public Categorie() {
		/* nothing to do here */
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	@SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else {
			return this.hashCode() == obj.hashCode();
		}
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 41 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
		hash = 41 * hash + (this.getIconFileName() != null ? this.getIconFileName().hashCode() : 0);
		hash = 41 * hash + (this.getTitle() != null ? this.getTitle().hashCode() : 0);
		hash = 41 * hash + (this.getDescription() != null ? this.getDescription().hashCode() : 0);
		return hash;
	}

	@Override
	public String toString() {
		return this.getTitle();
	}

	@Override
	public void validate() throws ConstraintViolationException {
		if (this.getDescription() == null
				|| this.getTitle() == null
				|| this.getIconFileName() == null
				|| this.getIconFileName().isEmpty()
				|| this.getDescription().isEmpty()
				|| this.getTitle().isEmpty()) {
			throw new NotNullConstraintViolationException();
		}
		if (this.getDescription().length() > 1000
				|| this.getTitle().length() > 255
				|| this.getIconFileName().length() > 255) {
			throw new FieldLengthConstraintViolationException();
		}
	}

	/**
	 * @return the iconFileName
	 */
	public String getIconFileName() {
		return iconFileName;
	}

	/**
	 * @param iconFileName the iconFileName to set
	 */
	public void setIconFileName(String iconFileName) {
		this.iconFileName = iconFileName;
	}
}
