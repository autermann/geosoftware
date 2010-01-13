/*
 * Copyright (C) 2009  Stefan Arndt, Christian Autermann, Dustin Demuth,
 * 					 Christoph Fendrich, Christian Paluschek
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
package org.sloth.pojo;

/**
 * Represents an non hierachical categorie for observations.
 *
 * @see Observation
 * @author Christian Autermann
 * @version 1.0
 * @since 1.0
 */
public class ObservationCategorie {

	private String title = null;
	private String description = null;

	/**
	 * Creates a new ObservationCategorie with <code>null</code> as default
	 * values for <code>title</code> and <code>description</code>.
	 */
	public ObservationCategorie() {
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
		hash = 41 * hash + (this.getTitle() != null ?
			this.getTitle().hashCode() : 0);
		hash = 41 * hash + (this.getDescription() != null ?
			this.getDescription().hashCode() : 0);
		return hash;
	}

	@Override
	public String toString() {
		return this.getTitle();
	}


}
