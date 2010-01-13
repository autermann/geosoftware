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

import java.util.Calendar;

/**
 * Represents an Observation, whic is defined by his
 * @author Christian Autermann
 * @version 1.0
 * @since 1.0
 */
public class Observation {

	private int id = -1;
	private String title = null;
	private String description = null;
	private User user = null;
	private Calendar timestamp = null;
	private ObservationCategorie observationCategorie = null;
	private Coordinate coordinate = null;

	/**
	 * Creates a new observation. The ID is initialized with <code>-1</code> and
	 * all others with <code>null</code>.
	 */
	public Observation() {
		/* nothing to do here */
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the timestamp
	 */
	public Calendar getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the observationCategorie
	 */
	public ObservationCategorie getObservationCategorie() {
		return observationCategorie;
	}

	/**
	 * @param observationCategorie the observationCategorie to set
	 */
	public void setObservationCategorie(ObservationCategorie oc) {
		this.observationCategorie = oc;
	}

	/**
	 * @return the coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * @param coordinate the coordinate to set
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
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
		hash = 59 * hash + this.id;
		hash = 59 * hash + (this.title != null ?
			this.title.hashCode() : 0);
		hash = 59 * hash + (this.description != null ?
			this.description.hashCode() : 0);
		hash = 59 * hash + (this.user != null ?
			this.user.hashCode() : 0);
		hash = 59 * hash + (this.timestamp != null ?
			this.timestamp.hashCode() : 0);
		hash = 59 * hash + (this.observationCategorie != null ?
			this.observationCategorie.hashCode() : 0);
		hash = 59 * hash + (this.coordinate != null ? 
			this.coordinate.hashCode() : 0);
		return hash;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("[");
		buf.append(this.getClass());
		buf.append("](");
		buf.append(getId());
		buf.append(" - \"");
		buf.append(title);
		buf.append("\" by ");
		buf.append(getUser());
		buf.append(" @");
		buf.append(getCoordinate());
		buf.append("|");
		buf.append(getTimestamp());
		buf.append(" in ");
		buf.append(getObservationCategorie());
		buf.append(")");
		return buf.toString();
	}
}
