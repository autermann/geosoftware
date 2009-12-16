/*
 * Copyright (C) 2009  Stefan Arndt, Christian Autermann, Dustin Demuth,
 *                     Christoph Fendrich, Christian Paluschek
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
package org.sloth.data;

import org.sloth.util.HashString;

/**
 *
 * @author Christian Autermann
 */
public class User {
	private String name = "Guest", familyName = "", eMail = "not@available.tld", passwort = "";
	private int rights = GUEST_RIGHTS;
	/**
	 *
	 */
	public static final int GUEST_RIGHTS = 0;
	/**
	 *
	 */
	public static final int STANDARD_RIGHTS = 1;
	/**
	 *
	 */
	public static final int WFS_RIGHTS = 2;
	/**
	 *
	 */
	public static final int ADMIN_RIGHTS = 3;
	/**
	 *
	 */
	public static final User GUEST = new User();

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
	 * @return the passwort
	 */
	public String getPasswort() {
		return passwort;
	}

	/**
	 * @param passwort the passwort to set
	 */
	public void setPasswort(String passwort) {
		this.passwort = HashString.hash(passwort);
	}

	/**
	 * @return the rights
	 */
	public int getRights() {
		return rights;
	}

	/**
	 * @param rights the rights to set
	 */
	public void setRights(int rights) {
		this.rights = rights;
	}

	/**
	 * 
	 * @param passwd
	 * @return
	 */
	public boolean testPassword(String passwd) {
		return this.passwort.equals(HashString.hash(passwd));
	}
}
