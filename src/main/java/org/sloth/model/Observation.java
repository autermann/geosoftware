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
package org.sloth.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

/**
 * Represents an Observation, whic is defined by his
 * @author Christian Autermann
 * @version 1.0
 * @since 1.0
 */
@Entity
public class Observation {

	@Id
	@GeneratedValue
	private long id;
	private String title;
	private String description;
	@ManyToOne
	private User user;
	@Temporal(DATE)
	private Date creationTime;
	@OneToOne
	private ObservationCategorie observationCategorie;
	@OneToOne
	private Coordinate coordinate;

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
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
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
	public Date getCreationTime() {
		return creationTime;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
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
		hash = (int) (59 * hash + this.id);
		hash = 59 * hash + (this.title != null ? this.title.hashCode() : 0);
		hash = 59 * hash + (this.description != null
							? this.description.hashCode() : 0);
		hash = 59 * hash + (this.user != null ? this.user.hashCode() : 0);
		hash = 59 * hash + (this.creationTime != null ? this.creationTime.
				hashCode() : 0);
		hash = 59 * hash + (this.observationCategorie != null ? this.observationCategorie.
				hashCode() : 0);
		hash = 59 * hash + (this.coordinate != null ? this.coordinate.hashCode()
							: 0);
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
		buf.append(getCreationTime());
		buf.append(" in ");
		buf.append(getObservationCategorie());
		buf.append(")");
		return buf.toString();
	}

}
