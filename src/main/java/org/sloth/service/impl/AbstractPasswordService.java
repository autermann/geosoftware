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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.service.PasswordService;
import static org.sloth.util.Config.getProperty;

public abstract class AbstractPasswordService implements PasswordService {

	protected static final Logger logger = LoggerFactory.getLogger(
			AbstractPasswordService.class);
	protected final int LENGTH;
	protected final boolean DIGIT;
	protected final boolean LOWER_CASE;
	protected final boolean UPPER_CASE;
	protected final boolean NON_ALPHA_NUM;
	protected String REGEX;

	public AbstractPasswordService() {
		String length = getProperty("password.length");
		String digit = getProperty("password.digit");
		String lowercase = getProperty("password.lowercase");
		String uppercase = getProperty("password.uppercase");
		String nonalphanum = getProperty("password.nonalphanum");

		LENGTH = (length == null) ? 1 : Integer.valueOf(length);
		DIGIT = (digit == null) ? false : Boolean.valueOf(digit);
		LOWER_CASE = (lowercase == null) ? false : Boolean.valueOf(lowercase);
		UPPER_CASE = (uppercase == null) ? false : Boolean.valueOf(uppercase);
		NON_ALPHA_NUM = (nonalphanum == null) ? false : Boolean.valueOf(nonalphanum);

		StringBuilder builder = new StringBuilder("^(?=.{");
		builder.append(LENGTH);
		builder.append(",})");
		if (DIGIT)
			builder.append("(?=.*[0-9])");
		if (LOWER_CASE)
			builder.append("(?=.*[a-z])");
		if (UPPER_CASE)
			builder.append("(?=.*[A-Z])");
		if (NON_ALPHA_NUM)
			builder.append("(?=.*[^A-Za-z0-9])");
		builder.append(".*$");

		REGEX = builder.toString();
	}

	@Override
	public boolean meetsRecommendation(String plain) {
		return plain.matches(REGEX);
	}
}
