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
package org.sloth;

import org.sloth.model.Categorie;
import org.sloth.model.Coordinate;
import org.sloth.model.Group;
import org.sloth.model.Observation;
import org.sloth.model.User;

public abstract class EntityFactory {

	private static long userCount = 0;
	private static long categorieCount = 0;
	private static long observationCount = 0;

	private static User getUser(Group g) {
		User u = new User();
		u.setFamilyName("Family Name of User " + userCount);
		u.setName("Name of User " + userCount);
		u.setMail("user" + userCount + "@users.de");
		u.setPassword("password" + userCount);
		u.setUserGroup(g);
		userCount++;
		return u;
	}

	public static User getUser() {
		return getUser(Group.USER);
	}

	public static User getAdminUser() {
		return getUser(Group.ADMIN);
	}

	public static User getWFSUSer() {
		return getUser(Group.WFS);
	}

	public static User getUser(Long id) {
		return getUser(id, Group.USER);
	}

	public static User getAdminUser(Long id) {
		return getUser(id, Group.ADMIN);
	}

	public static User getWFSUSer(Long id) {
		return getUser(id, Group.WFS);
	}

	private static User getUser(Long id, Group g) {
		User u = getUser(g);
		u.setId(id);
		return u;
	}

	public static Observation getObservation(Categorie c, User u) {
		Observation o = new Observation();
		o.setCategorie(c);
		o.setCoordinate(new Coordinate(observationCount, observationCount));
		o.setDescription("Description of Observation " + observationCount);
		o.setTitle("Title of Observation " + observationCount);
		o.setUser(u);
		observationCount++;
		return o;
	}

	public static Categorie getCategorie() {
		Categorie c = new Categorie();
		c.setDescription("Description of Categorie " + categorieCount);
		c.setTitle("Title of Categorie " + categorieCount);
		c.setIconFileName("File" + categorieCount + ".png");
		categorieCount++;
		return c;
	}

	public static Observation getObservation(Long id, Categorie c, User u) {
		Observation o = getObservation(c, u);
		o.setId(id);
		return o;
	}

	public static Categorie getCategorie(Long id) {
		Categorie c = getCategorie();
		c.setId(id);
		return c;
	}

	public static void reset() {
		categorieCount = observationCount = userCount = 0;
	}
}
