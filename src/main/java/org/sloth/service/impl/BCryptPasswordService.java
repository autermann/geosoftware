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

import org.sloth.util.BCrypt;
import org.springframework.stereotype.Service;
import static org.sloth.util.BCrypt.*;

/**
 * Realizes the password hashing with an OpenBSD-style Blowfish password hashing
 * algorithm using the scheme described in "A Future-Adaptable Password Scheme"
 * by Niels Provos and David Mazieres.
 *
 * @see BCrypt
 * @author Christian Autermann
 */
@Service
public class BCryptPasswordService extends AbstractPasswordService {

	public BCryptPasswordService() {
	}

	@Override
	public String hash(String plain) {
		return hashpw(plain, gensalt());
	}

	@Override
	public boolean check(String hash,
						 String plain) {
		return checkpw(plain, hash);
	}
}
