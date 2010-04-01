/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sloth.util;

import static org.springframework.util.StringUtils.*;
import org.springframework.validation.Errors;

/**
 *
 * @author auti
 */
public class ValidationUtils extends org.springframework.validation.ValidationUtils {

	public static void rejectIfTooLong(Errors errors, String field,
			String errorCode, int maxLength) {
		if (errors.getFieldValue(field).toString().length() > maxLength) {
			errors.rejectValue(field, errorCode);
		}
	}

	public static boolean rejectIfNull(Errors errors, String field,
			String errorCode) {
		if (notNull(errors.getFieldValue(field))) {
			return true;
		} else {
			errors.rejectValue(field, errorCode);
			return false;
		}
	}

	public static boolean isNotEmptyOrWhitespace(String field) {
		if (field == null || !hasText(field)) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean notNull(Object o) {
		return o != null;
	}

	public static boolean notNullAndNotEmpty(String s) {
		return (s != null) && hasText(s);
	}
}
