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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * Representing a user. Every user has an unique ID, an mail adress, a name,
 * family name and a password. It stores also the date of creation and the
 * group of the user.
 * 
 * @author Christian Autermann
 * @see Group
 */
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames="MAIL_ADDRESS"))
public class User extends BaseEntity implements Serializable {

	/*
	 * @see Serializable
	 */
	@Transient
	static final long serialVersionUID = -9018246759776935301L;
	@Column(nullable = false, unique = true, name = "MAIL_ADDRESS")
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
	@Column(name = "USER_GROUP")
	@Enumerated(EnumType.STRING)
	private Group userGroup;

	/**
	 * Creates a new User, while setting the corresponfig values.
	 * @param mail the mail address
	 * @param name the name
	 * @param familyName the family name
	 * @param password the password
	 * @param group the group
	 */
	public User(String mail, String name, String familyName, String password,
				Group group) {
		this();
		setMail(mail);
		setName(name);
		setPassword(password);
		setFamilyName(familyName);
		setUserGroup(group);
	}

	/**
	 * Creates a new <code>User</code> with <code>-1</code> as <code>>id</code>
	 * and <code>null</code> for all other members.
	 */
	public User() {
		setCreationDate(new Date());
	}

	/**
	 * @return the eMail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
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
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
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
		hash = 37 * hash + (this.getId() != null ? this.getId().hashCode() :0);
		hash = 37 * hash + (getMail() != null ? getMail().hashCode() : 0);
		hash = 37 * hash + (getName() != null ? getName().hashCode() : 0);
		hash = 37 * hash + (getFamilyName() != null ? getFamilyName().hashCode()
							: 0);
		hash = 37 * hash
			   + (getPassword() != null ? getPassword().hashCode() : 0);
		hash = 37 * hash + (getCreationDate() != null ? getCreationDate().
				hashCode() : 0);
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
	 * @param group the userRight to set
	 */
	public void setUserGroup(Group group) {
		this.userGroup = group;
	}

}
