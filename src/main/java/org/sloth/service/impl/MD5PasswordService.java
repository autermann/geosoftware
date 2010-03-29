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
package org.sloth.service.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Realizes password hashing through MD5-Hashing. Due security vulnerabilities
 * deprecepated.
 * 
 * @deprecated not future safe.
 * @see BCryptPasswordService
 * @author Christian Autermann
 */
@Deprecated
public class MD5PasswordService extends AbstractPasswordService {

	@Override
	public String hash(String plain) {
		String hash = null;
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(plain.getBytes());
			BigInteger i = new BigInteger(1, m.digest());
			hash = String.format("%1$032X", i);
		} catch (NoSuchAlgorithmException e) {
			logger.trace("can not hash passwords", e);
		}
		return hash;
	}

	@Override
	public boolean check(String hash, String plain) {
		return hash(plain).equals(hash);
	}
}
