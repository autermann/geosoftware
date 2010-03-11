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
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * @todo
 * @author auti
 */
@Entity
public class Observation implements Serializable {

	/**
	 * @todo
	 */
	@Transient
	static final long serialVersionUID = -2729214423734969225L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String title;
	@Column(length = 1000, nullable = false)
	private String description;
	@ManyToOne
	@JoinColumn(nullable = false, name = "USER_ID")
	private User user;
	@Temporal(TIMESTAMP)
	@Column(nullable = false, name = "CREATION_TIME")
	private Date creationDate;
	@OneToOne
	@JoinColumn(nullable = false, name = "OBSERVATION_CATEGORIE_ID")
	private ObservationCategorie observationCategorie;
	@Embedded
	@JoinColumn(nullable = false)
	private Coordinate coordinate;

	/**
	 * @todo
	 */
	public Observation() {
		setCreationDate(new Date());
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
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @todo
	 * @param creationDate
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the observationCategorie
	 */
	public ObservationCategorie getObservationCategorie() {
		return observationCategorie;
	}

	/**
	 * @todo
	 * @param oc
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
		hash = 59 * hash + (int) this.getId();
		hash = 59 * hash + (this.title != null ? this.title.hashCode() : 0);
		hash = 59 * hash + (this.description != null
							? this.description.hashCode() : 0);
		hash = 59 * hash + (this.user != null ? this.user.hashCode() : 0);
		hash = 59 * hash + (this.creationDate != null ? this.creationDate.
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
		buf.append(getCreationDate());
		buf.append(" in ");
		buf.append(getObservationCategorie());
		buf.append(")");
		return buf.toString();
	}

	/**
	 * @todo
	 * @return
	 */
	public long getId() {
		return (this.id == null) ? 0 : this.id;
	}

	/**
	 * @todo
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @todo
	 * @return
	 */
	public boolean isNew() {
		return (this.id == null);
	}

}
