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

import javax.persistence.Embeddable;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Metamodel class for the {@link Embeddable} {@link Coordinate}
 * 
 * @see Coordinate
 * @author Christian Autermann
 */
@StaticMetamodel(Coordinate.class)
public class Coordinate_ {

	/**
	 * Metamodel-Attribute for {@link Coordinate#longitude}
	 * 
	 * @see Coordinate#getLongitude()
	 * @see Coordinate#setLongitude(double)
	 */
	public static volatile SingularAttribute<Coordinate, Double> longitude;
	/**
	 * Metamodel-Attribute for {@link Coordinate#latitude}
	 * 
	 * @see Coordinate#getLatitude()
	 * @see Coordinate#setLatitude(double)
	 */
	public static volatile SingularAttribute<Coordinate, Double> latitude;
}
