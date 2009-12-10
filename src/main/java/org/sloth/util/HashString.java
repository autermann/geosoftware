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
package org.sloth.util;

import java.security.MessageDigest;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Christian Autermann
 */
public final class HashString {

	/**
	 * 
	 * @param plain
	 * @return
	 */
	public static String hash(String plain) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA");
			md.update(plain.getBytes("UTF-8"));
		} catch (Exception e) {
			Log.throwing(e);
		}
		return (new BASE64Encoder()).encode(md.digest());
	}

	private HashString() {}
	
	public static void main(String[] args){
		String[] strings = {"a","b","ab"};
		for (String s : strings)
			System.out.println(hash(s));
	}
}
