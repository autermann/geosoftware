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

import static org.sloth.util.ValidationUtils.rejectIfTooLong;
import static org.sloth.validation.ErrorCodes.REGISTRATION.EMPTY_FAMILY_NAME;
import static org.sloth.validation.ErrorCodes.REGISTRATION.EMPTY_MAIL;
import static org.sloth.validation.ErrorCodes.REGISTRATION.EMPTY_MAIL_REPEAT;
import static org.sloth.validation.ErrorCodes.REGISTRATION.EMPTY_NAME;
import static org.sloth.validation.ErrorCodes.REGISTRATION.EMPTY_PASSWORD;
import static org.sloth.validation.ErrorCodes.REGISTRATION.EMPTY_PASSWORD_REPEAT;
import static org.sloth.validation.ErrorCodes.REGISTRATION.INVALID_MAIL;
import static org.sloth.validation.ErrorCodes.REGISTRATION.LOW_SECURED_PASSWORD;
import static org.sloth.validation.ErrorCodes.REGISTRATION.NOT_UNIQUE_MAIL;
import static org.sloth.validation.ErrorCodes.REGISTRATION.TOO_LONG_FAMILY_NAME;
import static org.sloth.validation.ErrorCodes.REGISTRATION.TOO_LONG_MAIL;
import static org.sloth.validation.ErrorCodes.REGISTRATION.TOO_LONG_NAME;
import static org.sloth.validation.ErrorCodes.REGISTRATION.WRONG_MAIL_REPEAT;
import static org.sloth.validation.ErrorCodes.REGISTRATION.WRONG_PASSWORD_REPEAT;
import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

import org.sloth.web.action.RegistrationFormAction;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


/**
 * A {@code Validator} for validating {@code RegistrationFormAction}s.
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 * 
 */
@Component
public class RegistrationFormValidator extends AbstractUserActionValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(RegistrationFormAction.class);
	}

	@Override
	public void validate(Object t, Errors e) {
		rejectIfEmptyOrWhitespace(e, "familyName", EMPTY_FAMILY_NAME);
		rejectIfEmptyOrWhitespace(e, "name", EMPTY_NAME);
		rejectIfEmptyOrWhitespace(e, "passwordRepeat", EMPTY_PASSWORD_REPEAT);
		rejectIfEmptyOrWhitespace(e, "mailRepeat", EMPTY_MAIL_REPEAT);
		rejectIfEmptyOrWhitespace(e, "password", EMPTY_PASSWORD);
		rejectIfEmptyOrWhitespace(e, "mail", EMPTY_MAIL);
		rejectIfTooLong(e, "name", TOO_LONG_NAME, 255);
		rejectIfTooLong(e, "familyName", TOO_LONG_FAMILY_NAME, 255);
		rejectIfTooLong(e, "mail", TOO_LONG_MAIL, 255);
		RegistrationFormAction a = (RegistrationFormAction) t;
		String password = a.getPassword(), mail = a.getMail();
		if (!password.equals(a.getPasswordRepeat())) {
			e.rejectValue("passwordRepeat", WRONG_PASSWORD_REPEAT);
		} else if (!this.passwordService.meetsRecommendation(password)) {
			e.rejectValue("password", LOW_SECURED_PASSWORD);
		}
		if (!mail.equals(a.getMailRepeat())) {
			e.rejectValue("mailRepeat", WRONG_MAIL_REPEAT);
		} else if (!mail.matches(REGEX)) {
			e.rejectValue("mail", INVALID_MAIL);
		} else if (!this.userService.isMailAddressAvailable(mail)) {
			e.rejectValue("mail", NOT_UNIQUE_MAIL);
		}
	}
}
