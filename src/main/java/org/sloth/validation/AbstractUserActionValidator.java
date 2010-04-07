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
package org.sloth.validation;

import static org.sloth.util.ValidationUtils.isNotEmptyOrWhitespace;

import org.sloth.service.PasswordService;
import org.sloth.service.UserService;
import org.sloth.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;

/**
 * Abstract implementation of an {@code Validator} validating {@code User}
 * related {@code Object}s. It provides a {@code PasswordService}, a {@code
 * UserService} and regular expression to test mail addresses.
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
public abstract class AbstractUserActionValidator implements Validator {

	/**
	 * A regular expression to validate mail addresses.
	 */
	protected static final String REGEX;
	/**
	 * S PasswordService.
	 */
	protected PasswordService passwordService;
	/**
	 * A UserService.
	 */
	protected UserService userService;

	static {
		String regex = Config.getProperty("mail.regex");
		if (isNotEmptyOrWhitespace(regex)) {
			REGEX = regex;
		} else {
			REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
		}
	}

	/**
	 * @param passwordService
	 *            the passwordService to set
	 */
	@Autowired
	public void setPasswordService(PasswordService passwordService) {
		this.passwordService = passwordService;
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
