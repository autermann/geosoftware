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

import org.sloth.web.action.UserEditFormAction;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import static org.sloth.util.ValidationUtils.*;
import static org.sloth.validation.ErrorCodes.USER_EDIT.*;

@Component
public class UserEditFormValidator extends AbstractUserActionValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UserEditFormAction.class);
	}

	@Override
	public void validate(Object t, Errors e) {
		UserEditFormAction a = (UserEditFormAction) t;
		if (!a.getEditingUser().getId().equals(a.getOldUser().getId())) {
			test(a, e);
		} else if (!notNullAndNotEmpty(a.getActualPassword())) {
			e.rejectValue("actualPassword", EMPTY_ACTUAL_PASSWORD);
		} else if (this.passwordService.check(a.getOldUser().getPassword(), a.getActualPassword())) {
			test(a, e);
			if (!a.getNewGroup().equals(a.getOldUser().getUserGroup())) {
				e.rejectValue("newGroup", CAN_NOT_CHANGE_OWN_GROUP);
			}
		} else {
			e.rejectValue("actualPassword", WRONG_ACTUAL_PASSWORD);
		}
	}

	private void test(UserEditFormAction a, Errors e) {
		rejectIfEmptyOrWhitespace(e, "newName", EMPTY_NEW_NAME);
		rejectIfTooLong(e, "newName", TOO_LONG_NEW_NAME, 255);
		rejectIfEmptyOrWhitespace(e, "newFamilyName", EMPTY_NEW_FAMILY_NAME);
		rejectIfTooLong(e, "newFamilyName", TOO_LONG_NEW_FAMILY_NAME, 255);
		rejectIfEmptyOrWhitespace(e, "newMail", EMPTY_NEW_MAIL);
		String newMail = a.getNewMail();
		if (!newMail.matches(REGEX)) {
			e.rejectValue("newMail", INVALID_NEW_MAIL);
		} else if (!newMail.equals(a.getOldUser().getMail())
				&& !this.userService.isMailAddressAvailable(newMail)) {
			e.rejectValue("newMail", NOT_UNIQUE_NEW_MAIL);
		}
		String newPassword = a.getNewPassword();
		String newPasswordRepeat = a.getNewPasswordRepeat();
		if (notNullAndNotEmpty(newPassword)) {
			if (!notNullAndNotEmpty(newPasswordRepeat)) {
				e.rejectValue("newPasswordRepeat", EMPTY_NEW_PASSWORD_REPEAT);
			} else if (!newPassword.equals(newPasswordRepeat)) {
				e.rejectValue("newPasswordRepeat", WRONG_NEW_PASSWORD_REPEAT);
			} else if (this.passwordService.meetsRecommendation(newPassword)) {
				a.setNewPasswordHash(this.passwordService.hash(newPassword));
			} else {
				e.rejectValue("newPassword", BAD_SECURED_NEW_PASSWORD);
			}
		} else if (notNullAndNotEmpty(newPasswordRepeat)) {
			e.rejectValue("newPassword", EMPTY_NEW_PASSWORD);
		}
	}
}
