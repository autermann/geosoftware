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

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Observation.class)
public class Observation_ {
	public static volatile SingularAttribute<Observation, Coordinate> coordinate;
	public static volatile SingularAttribute<Observation, ObservationCategorie> observationCategorie;
	public static volatile SingularAttribute<Observation, Date> creationDate;
	public static volatile SingularAttribute<Observation, String> description;
	public static volatile SingularAttribute<Observation, String> title;
	public static volatile SingularAttribute<Observation, Long> id;
	public static volatile SingularAttribute<Observation, User> user;
}
