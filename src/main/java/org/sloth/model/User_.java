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

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Metamodel class for the {@link Entity} {@link User}
 * 
 * @see User
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
@StaticMetamodel(User.class)
public class User_ {

	/**
	 * Metamodel-Attribute for {@link User#id}
	 * 
	 * @see User#getId()
	 * @see User#setId(java.lang.Long)
	 */
	public static volatile SingularAttribute<User, Long> id;
	/**
	 * Metamodel-Attribute for {@link User#mail}
	 * 
	 * @see User#getMail()
	 * @see User#setMail(java.lang.String)
	 */
	public static volatile SingularAttribute<User, String> mail;
	/**
	 * Metamodel-Attribute for {@link User#name}
	 * 
	 * @see User#getName()
	 * @see User#setName(java.lang.String)
	 */
	public static volatile SingularAttribute<User, String> name;
	/**
	 * Metamodel-Attribute for {@link User#familyName}
	 * 
	 * @see User#getFamilyName()
	 * @see User#setFamilyName(java.lang.String)
	 */
	public static volatile SingularAttribute<User, String> familyName;
	/**
	 * Metamodel-Attribute for {@link User#password}
	 * 
	 * @see User#getPassword()
	 * @see User#setPassword(java.lang.String)
	 */
	public static volatile SingularAttribute<User, String> password;
	/**
	 * Metamodel-Attribute for {@link User#creationDate}
	 * 
	 * @see User#getCreationDate()
	 * @see User#setCreationDate(java.util.Date)
	 */
	public static volatile SingularAttribute<User, Date> creationDate;
	/**
	 * Metamodel-Attribute for {@link User#userGroup}
	 * 
	 * @see User#getUserGroup()
	 * @see User#setUserGroup(org.sloth.model.Group)
	 */
	public static volatile SingularAttribute<User, Group> userGroup;
}
