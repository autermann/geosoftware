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

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * Representing a user. Every user has an unique ID, an eMail adress, a name, 
 * family name and a password. It stores also the date of creation and the
 * rights of the user.
 * 
 * @author Christian Autermann
 * @see
 * @since 1.0
 * @version 1.0
 */
@Entity
public class User implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, unique = true, name = "MAIL_ADDRESS")
	private String eMail;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, name = "FAMILY_NAME")
	private String familyName;
	@Column(nullable = false, name = "HASHED_PASSWORD")
	private String hashedPassword;
	@Temporal(TIMESTAMP)
	@Column(nullable = false, name = "CREATION_DATE")
	private Calendar creationDate;
	@ManyToOne
	@JoinColumn(nullable = false, name = "USER_RIGHT_ID")
	private UserRight userRight;

	/**
	 * Creates a new <code>User</code> with <code>-1</code> as <code>>id</code>
	 * and <code>null</code> for all other members.
	 */
	public User() {
		setCreationDate(Calendar.getInstance());
	}

	/**
	 * @return the eMail
	 */
	public String geteMail() {
		return eMail;
	}

	/**
	 * @param eMail the eMail to set
	 */
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the familyName
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * @param familyName the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * @return the hashedPassword
	 */
	public String getHashedPassword() {
		return hashedPassword;
	}

	/**
	 * @param hashedPassword the hashedPassword to set
	 */
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	/**
	 * @return the creationDate
	 */
	public Calendar getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	@SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else {
			return obj.hashCode() == this.hashCode();
		}
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 37 * hash + (int) this.getId();
		hash = 37 * hash + (this.geteMail() != null ? this.geteMail().hashCode() : 0);
		hash = 37 * hash + (this.getName() != null ? this.getName().hashCode() : 0);
		hash = 37 * hash + (this.getFamilyName() != null ? this.getFamilyName().hashCode() : 0);
		hash = 37 * hash + (this.getHashedPassword() != null ? this.getHashedPassword().hashCode() : 0);
		hash = 37 * hash + (this.getCreationDate() != null ? this.getCreationDate().hashCode() : 0);
		return hash;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(getFamilyName());
		buf.append(", ");
		buf.append(getName());
		buf.append(" (");
		buf.append(getId());
		buf.append(")");
		return buf.toString();
	}

	/**
	 * @return the userRight
	 */
	public UserRight getUserRight() {
		return userRight;
	}

	/**
	 * @param userRight the userRight to set
	 */
	public void setUserRight(UserRight userRight) {
		this.userRight = userRight;
	}

	public long getId() {
		return (this.id == null) ? 0 : this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isNew() {
		return (this.id == null);
	}
}
