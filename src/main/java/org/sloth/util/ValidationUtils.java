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
package org.sloth.util;

import static org.springframework.util.StringUtils.hasText;

import org.springframework.validation.Errors;

/**
 * Utility class for validation.
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
public class ValidationUtils extends
		org.springframework.validation.ValidationUtils {

	/**
	 * Convenient method to check whether the specified {@code String} is
	 * {@code null} or whitespace.
	 * 
	 * @param field
	 *            the {@code String} to be tested
	 * @return {@code true} if it is {@code null} or whitespace and {@code
	 *         false} otherwise.
	 */
	public static boolean isNotEmptyOrWhitespace(String field) {
		if (field == null || !hasText(field)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Convenient method to check if an {@code Object} is {@code null}.
	 * 
	 * @param o
	 *            the {@code Object} to be tested
	 * @return {@code true} if {@code o} is {@code null} and {@code false}
	 *         otherwise.
	 */
	public static boolean notNull(Object o) {
		return o != null;
	}

	/**
	 * 
	 * @param errors
	 *            the {@link Errors} instance that should store the errors (must
	 *            not be <code>null</code>)
	 * @param field
	 *            the field name to check
	 * @param errorCode
	 *            the error code, interpretable as message key
	 * @return {@code true} if field is not {@code null}, otherwise {@code
	 *         false}
	 */
	public static boolean rejectIfNull(Errors errors, String field,
			String errorCode) {
		if (notNull(errors.getFieldValue(field))) {
			return true;
		} else {
			errors.rejectValue(field, errorCode);
			return false;
		}
	}

	/**
	 * 
	 * @param errors
	 *            the {@link Errors} instance that should store the errors (must
	 *            not be <code>null</code>)
	 * @param field
	 *            the field name to check
	 * @param errorCode
	 *            the error code, interpretable as message key
	 * @param maxLength
	 *            the maximum length of {@code field}
	 */
	public static void rejectIfTooLong(Errors errors, String field,
			String errorCode, int maxLength) {
		if (errors.getFieldValue(field).toString().length() > maxLength) {
			errors.rejectValue(field, errorCode);
		}
	}
}
