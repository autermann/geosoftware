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

import javax.persistence.Entity;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Metamodel class for the {@link Entity} {@link Categorie}
 * 
 * @see Categorie
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
@StaticMetamodel(Categorie.class)
public class Categorie_ {

	/**
	 * Metamodel-Attribute for {@link Categorie#title}
	 * 
	 * @see Categorie#getTitle()
	 * @see Categorie#setTitle(java.lang.String)
	 */
	public static volatile SingularAttribute<Categorie, String> title;
	/**
	 * Metamodel-Attribute for {@link Categorie#description}
	 * 
	 * @see Categorie#getDescription()
	 * @see Categorie#setDescription(java.lang.String)
	 */
	public static volatile SingularAttribute<Categorie, String> description;
	/**
	 * Metamodel-Attribute for {@link Categorie#id}
	 * 
	 * @see Categorie#getId()
	 * @see Categorie#setId(long)
	 */
	public static volatile SingularAttribute<Categorie, Long> id;
	/**
	 * Metamodel-Attribute for {@link Categorie#iconFileName}
	 * 
	 * @see Categorie#getIconFileName()
	 * @see Categorie#setIconFileName(java.lang.String)
	 */
	public static volatile SingularAttribute<Categorie, String> iconFileName;
}
