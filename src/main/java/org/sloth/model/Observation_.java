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
 * Metamodel class for the {@link Entity} {@link Observation}
 * 
 * @see Observation
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
@StaticMetamodel(Observation.class)
public class Observation_ {

	/**
	 * Metamodel-Attribute for {@link Observation#coordinate}
	 * 
	 * @see Observation#getCoordinate()
	 * @see Observation#setCoordinate(org.sloth.model.Coordinate)
	 */
	public static volatile SingularAttribute<Observation, Coordinate> coordinate;
	/**
	 * Metamodel-Attribute for {@link Observation#categorie}
	 * 
	 * @see Observation#getCategorie()
	 * @see Observation#setCategorie(org.sloth.model.Categorie)
	 */
	public static volatile SingularAttribute<Observation, Categorie> categorie;
	/**
	 * Metamodel-Attribute for {@link Observation#creationDate}
	 * 
	 * @see Observation#getCreationDate()
	 * @see Observation#setCreationDate(java.util.Date)
	 */
	public static volatile SingularAttribute<Observation, Date> creationDate;
	/**
	 * Metamodel-Attribute for {@link Observation#description}
	 * 
	 * @see Observation#getDescription()
	 * @see Observation#setDescription(java.lang.String)
	 */
	public static volatile SingularAttribute<Observation, String> description;
	/**
	 * Metamodel-Attribute for {@link Observation#title}
	 * 
	 * @see Observation#getTitle()
	 * @see Observation#setTitle(java.lang.String)
	 */
	public static volatile SingularAttribute<Observation, String> title;
	/**
	 * Metamodel-Attribute for {@link Observation#id}
	 * 
	 * @see Observation#getId()
	 * @see Observation#setId(java.lang.Long)
	 */
	public static volatile SingularAttribute<Observation, Long> id;
	/**
	 * Metamodel-Attribute for {@link Observation#user}
	 * 
	 * @see Observation#getUser()
	 * @see Observation#setUser(org.sloth.model.User)
	 */
	public static volatile SingularAttribute<Observation, User> user;
}
