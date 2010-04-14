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

import static org.sloth.util.Config.getProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.service.PasswordService;

/**
 * Abstract implementation of {@link PasswordService}. This class implements a
 * mechanism to check wether a password meets a given recommandation.
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
public abstract class AbstractPasswordService implements PasswordService {

	/**
	 * The {@code Logger} for this class and all subclasses.
	 */
	protected static final Logger logger = LoggerFactory
			.getLogger(AbstractPasswordService.class);
	/**
	 * The regular expression used to check the security of a password.
	 */
	protected static final String REGEX;

	/**
	 * Specifies how long a password has to be.
	 */
	public static final int LENGTH;
	/**
	 * Specifies if a password has to contain at least one digit.
	 */
	public static final boolean DIGIT;
	/**
	 * Specifies if a password has to contain at least one lower case character.
	 */
	public static final boolean LOWER_CASE;
	/**
	 * Specifies if a password has to contain at least one upper case character.
	 */
	public static final boolean UPPER_CASE;
	/**
	 * Specifies if a password has to contain at least one non ASCII character.
	 */
	public static final boolean NON_ALPHA_NUM;

	static {
		String length = getProperty("password.length");
		String digit = getProperty("password.digit");
		String lowercase = getProperty("password.lowercase");
		String uppercase = getProperty("password.uppercase");
		String nonalphanum = getProperty("password.nonalphanum");

		LENGTH = (length == null) ? 1 : Integer.valueOf(length);
		DIGIT = (digit == null) ? false : Boolean.valueOf(digit);
		LOWER_CASE = (lowercase == null) ? false : Boolean.valueOf(lowercase);
		UPPER_CASE = (uppercase == null) ? false : Boolean.valueOf(uppercase);
		NON_ALPHA_NUM = (nonalphanum == null) ? false : Boolean
				.valueOf(nonalphanum);

		StringBuilder builder = new StringBuilder("^(?=.{");
		builder.append(LENGTH);
		builder.append(",})");
		if (DIGIT) {
			builder.append("(?=.*[0-9])");
		}
		if (LOWER_CASE) {
			builder.append("(?=.*[a-z])");
		}
		if (UPPER_CASE) {
			builder.append("(?=.*[A-Z])");
		}
		if (NON_ALPHA_NUM) {
			builder.append("(?=.*[^A-Za-z0-9])");
		}
		builder.append(".*$");
		REGEX = builder.toString();
		
		logger.info("Password Requirements:");
		logger.info("Has to contain a digit: {}.", DIGIT);
		logger.info("Has to contain a lower case character: {}.", LOWER_CASE);
		logger.info("Has to contain a upper case character: {}.", UPPER_CASE);
		logger.info("Has to contain a non alphanumeric character: {}.", NON_ALPHA_NUM);
		logger.info("Has to be at least {} characters long.", LENGTH);
		logger.info("Generated regular expression is: {}", REGEX);
	}

	@Override
	public boolean meetsRecommendation(String plain) {
		return plain.matches(REGEX);
	}
}
