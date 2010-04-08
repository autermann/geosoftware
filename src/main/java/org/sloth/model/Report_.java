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
 * Metamodel class for the {@link Entity} {@link Report}
 * 
 * @see Report
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
@StaticMetamodel(Report.class)
public class Report_ {

	/**
	 * Metamodel-Attribute for {@link Report#author}
	 * 
	 * @see Report#getAuthor()
	 * @see Report#setAuthor(org.sloth.model.User)
	 */
	public static volatile SingularAttribute<Report, User> author;
	/**
	 * Metamodel-Attribute for {@link Report#description}
	 * 
	 * @see Report#getDescription()
	 * @see Report#setDescription(java.lang.String)
	 */
	public static volatile SingularAttribute<Report, String> description;
	/**
	 * Metamodel-Attribute for {@link Report#id}
	 * 
	 * @see Report#getId()
	 * @see Report#setId(java.lang.Long)
	 */
	public static volatile SingularAttribute<Report, Long> id;
	/**
	 * Metamodel-Attribute for {@link Report#observation}
	 * 
	 * @see Report#getObservation()
	 * @see Report#setObservation(org.sloth.model.Observation)
	 */
	public static volatile SingularAttribute<Report, Observation> observation;
	/**
	 * Metamodel-Attribute for {@link Report#processed}
	 * 
	 * @see Report#isProcessed()
	 * @see Report#setProcessed(boolean)
	 */
	public static volatile SingularAttribute<Report, Boolean> processed;
	/**
	 * Metamodel-Attribute for {@link Report#creationDate}
	 * 
	 * @see Report#getCreationDate()
	 * @see Report#setCreationDate(java.util.Date)
	 */
	public static volatile SingularAttribute<Report, Date> creationDate;
	/**
	 * Metamodel-Attribute for {@link Report#lastUpdateDate}
	 * 
	 * @see Report#getLastUpdateDate()
	 * @see Report#setLastUpdateDate(java.util.Date)
	 */
	public static volatile SingularAttribute<Report, Date> lastUpdateDate;
}
