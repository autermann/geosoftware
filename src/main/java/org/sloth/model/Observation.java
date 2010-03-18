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
public class Observation extends BaseEntity implements Serializable {

	/**
	 * @todo
	 */
	@Transient
	static final long serialVersionUID = -2729214423734969225L;
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
	@JoinColumn(nullable = false, name = "CATEGORIE_ID")
	private Categorie categorie;
	@Embedded
	@JoinColumn(nullable = false)
	private Coordinate coordinate;

	/**
	 * @todo
	 * @param title
	 * @param description
	 * @param user
	 * @param categorie
	 * @param coordinate
	 */
	public Observation(String title, String description, User user,
					   Categorie categorie, Coordinate coordinate) {
		setCreationDate(new Date());
		setTitle(title);
		setDescription(description);
		setUser(user);
		setCategorie(categorie);
		setCoordinate(coordinate);
	}

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
	public Categorie getCategorie() {
		return categorie;
	}

	/**
	 * @todo
	 * @param oc
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
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
		hash = 59 * hash + (this.getId() != null ? this.getId().hashCode() :0);
		hash = 59 * hash + (this.getTitle() != null ? this.getTitle().hashCode() : 0);
		hash = 59 * hash + (this.getDescription() != null ? this.getDescription().
				hashCode() : 0);
		hash = 59 * hash + (this.getUser() != null ? this.getUser().hashCode()
							: 0);
		hash = 59 * hash + (this.getCreationDate() != null ? this.
				getCreationDate().hashCode() : 0);
		hash = 59 * hash + (this.getCategorie() != null ? this.getCategorie().
				hashCode() : 0);
		hash = 59 * hash + (this.getCoordinate() != null ? this.getCoordinate().
				hashCode() : 0);
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
		buf.append(getCategorie());
		buf.append(")");
		return buf.toString();
	}
}
