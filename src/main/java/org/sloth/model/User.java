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
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import static javax.persistence.EnumType.*;
import static javax.persistence.TemporalType.*;
import org.sloth.exceptions.ConstraintViolationException;
import org.sloth.exceptions.FieldLengthConstraintViolationException;
import org.sloth.exceptions.NotNullConstraintViolationException;

/**
 * Representing a user. Every user has an unique ID, an mail adress, a name,
 * family name and a password. It stores also the date of creation and the group
 * of the user.
 * 
 * @author Christian Autermann
 * @see Group
 */
@Entity(name = "USERS")
public class User extends BaseEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = -9018246759776935301L;
	@Column(unique = true, nullable = false, name = "MAIL_ADDRESS")
	private String mail;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, name = "FAMILY_NAME")
	private String familyName;
	@Column(nullable = false)
	private String password;
	@Temporal(TIMESTAMP)
	@Column(nullable = false, name = "CREATION_DATE")
	private Date creationDate;
	@Enumerated(STRING)
	@Column(nullable = false, name = "USER_GROUP")
	private Group userGroup;

	/**
	 * Creates a new User, while setting the corresponfig values.
	 * 
	 * @param mail
	 *            the mail address
	 * @param name
	 *            the name
	 * @param familyName
	 *            the family name
	 * @param password
	 *            the password
	 * @param group
	 *            the group
	 */
	public User(String mail, String name, String familyName, String password,
			Group group) {
		this();
		this.creationDate = new Date();
		this.mail = mail;
		this.name = name;
		this.familyName = familyName;
		this.password = password;
		this.userGroup = group;
	}

	/**
	 * Creates a new <code>User</code> with <code>-1</code> as <code>>id</code>
	 * and <code>null</code> for all other members.
	 */
	public User() {
		this.creationDate = new Date();
	}

	/**
	 * @return the eMail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail
	 *            the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
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
	 * @param familyName
	 *            the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof User) {
			return (obj instanceof User) && obj.hashCode() == this.hashCode();
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 37 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
		hash = 37 * hash + (this.mail != null ? this.mail.hashCode() : 0);
		hash = 37 * hash + (this.name != null ? this.name.hashCode() : 0);
		hash = 37 * hash + (this.familyName != null ? this.familyName.hashCode() : 0);
		hash = 37 * hash + (this.password != null ? this.password.hashCode() : 0);
		hash = 37 * hash + (this.creationDate != null ? this.creationDate.hashCode() : 0);
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
	 * @return the group
	 */
	public Group getUserGroup() {
		return userGroup;
	}

	/**
	 * @param group
	 *            the userRight to set
	 */
	public void setUserGroup(Group group) {
		this.userGroup = group;
	}

	@Override
	public void validate() throws ConstraintViolationException {
		if (this.creationDate == null
				|| this.familyName == null
				|| this.name == null
				|| this.mail == null
				|| this.password == null
				|| this.userGroup == null
				|| this.name.isEmpty()
				|| this.familyName.isEmpty()
				|| this.mail.isEmpty()
				|| this.password.isEmpty()) {
			throw new NotNullConstraintViolationException();
		}
		if (this.familyName.length() > 255
				|| this.mail.length() > 255
				|| this.password.length() > 255
				|| this.name.length() > 255) {
			throw new FieldLengthConstraintViolationException();
		}
	}
}
