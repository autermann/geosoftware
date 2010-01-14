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

package org.sloth.service.impl;

import java.security.MessageDigest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sloth.service.PasswordManager;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Christian Autermann
 */
public class PasswordManagerImpl implements PasswordManager {

	private static final Log logger = LogFactory.getLog(PasswordManager.class);


	@Override
	public boolean meetsRecommendation(String plain) {
		/* 8 characters or more; atleast one majuscule, minuscule,
		 * digit and one non alphanumeric character */
		return plain.matches("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).*$");
	}

	@Override
	public boolean test(String one, String two) {
		return one.equals(two);
	}
	
	@Override
	public String hash(String plain) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA");
			md.update(plain.getBytes("UTF-8"));
		} catch (Exception e) {
			logger.fatal("can not hash passwords", e);
		}
		return (new BASE64Encoder()).encode(md.digest());
	}

}
