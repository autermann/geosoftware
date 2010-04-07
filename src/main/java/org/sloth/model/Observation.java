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

import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import org.sloth.exceptions.ConstraintViolationException;
import org.sloth.exceptions.FieldLengthConstraintViolationException;
import org.sloth.exceptions.NotNullConstraintViolationException;

/**
 * Class to represent an Observation in an {@code Categorie} created by a
 * {@code User}.
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
@Entity(name = "OBSERVATIONS")
public class Observation extends BaseEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = -2729214423734969225L;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false, length = 1000)
	private String description;
	@Temporal(TIMESTAMP)
	@Column(nullable = false, name = "CREATION_DATE")
	private Date creationDate;
	@ManyToOne
	@JoinColumn(nullable = false, name = "USER_ID")
	private User user;
	@ManyToOne
	@JoinColumn(nullable = false, name = "CATEGORIE_ID")
	private Categorie categorie;
	@Embedded
	@JoinColumn(nullable = false)
	private Coordinate coordinate;

	/**
	 * Creates a new {@code Observation} with the actual time as the {@code
	 * creationDate} and {@code null} as default value for all other properties.
	 */
	public Observation() {
		this.creationDate = new Date();
	}

	/**
	 * Creates a new {@code Observation}.
	 * 
	 * @param title
	 *            the title
	 * @param description
	 *            the description
	 * @param user
	 *            the user
	 * @param categorie
	 *            the categorie
	 * @param coordinate
	 *            the coordinate
	 */
	public Observation(String title, String description, User user,
			Categorie categorie, Coordinate coordinate) {
		this.creationDate = new Date();
		this.title = title;
		this.description = description;
		this.user = user;
		this.categorie = categorie;
		this.coordinate = coordinate;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Observation) {
			return this.hashCode() == obj.hashCode();
		} else {
			return false;
		}
	}

	/**
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}

	/**
	 * @return the coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * @return the timestamp
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 59 * hash + this.getVersion();
		hash = 59 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
		hash = 59 * hash + (this.title != null ? this.title.hashCode() : 0);
		hash = 59 * hash
				+ (this.description != null ? this.description.hashCode() : 0);
		hash = 59 * hash + (this.user != null ? user.hashCode() : 0);
		hash = 59
				* hash
				+ (this.creationDate != null ? this.creationDate.hashCode() : 0);
		hash = 59 * hash
				+ (this.categorie != null ? this.categorie.hashCode() : 0);
		hash = 59 * hash
				+ (this.coordinate != null ? this.coordinate.hashCode() : 0);
		return hash;
	}

	/**
	 * @param categorie
	 *            the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/**
	 * @param coordinate
	 *            the coordinate to set
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
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

	@Override
	public void validate() throws ConstraintViolationException {
		if (coordinate == null || creationDate == null || description == null
				|| title == null || user == null || description.isEmpty()
				|| title.isEmpty()) {
			throw new NotNullConstraintViolationException();
		}
		if (title.length() > 255 || description.length() > 1000) {
			throw new FieldLengthConstraintViolationException();
		}
	}

	@Override
	public Object clone() {
		Observation o = new Observation();
		o.setCategorie(categorie);
		o.setCoordinate((Coordinate) coordinate.clone());
		o.setDescription(description);
		o.setId(new Long(getId()));
		o.setTitle(title);
		o.setVersion(getVersion());
		o.setCreationDate(creationDate);
		o.setUser(user);
		return o;

	}
}
