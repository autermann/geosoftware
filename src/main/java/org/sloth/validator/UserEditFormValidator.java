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
package org.sloth.validator;

import org.sloth.web.actions.UserEditFormAction;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import static org.sloth.util.ValidatorUtils.*;

@Component
public class UserEditFormValidator extends AbstractUserActionValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UserEditFormAction.class);
	}

	@Override
	public void validate(Object t, Errors e) {
		UserEditFormAction a = (UserEditFormAction) t;
		if (a.getEditingUser().getId().equals(a.getOldUser().getId())) { // self editing
			if (notNullAndNotEmpty(a.getActualPassword())) {
				if (passwordService.check(a.getOldUser().getPassword(), a.
						getActualPassword())) {
					test(a, e);
				} else {
					e.rejectValue("actualPassword",
								  "field.useredit.actualpassword.wrong");
				}
			} else {
				e.rejectValue("actualPassword",
							  "field.useredit.actualpassword.empty");
			}
		} else { // editing somone else
			test(a, e);
			if (!a.getNewGroup().equals(a.getOldUser().getUserGroup())) {
				e.rejectValue("newGroup",
							  "field.useredit.newGroup.canNotChangeOwnGroup");
			}
		}
	}

	private void test(UserEditFormAction a, Errors e) {
		rejectIfEmptyOrWhitespace(e, "newName", "field.useredit.newName.empty");
		rejectIfTooLong(e, "newName", "field.useredit.newName.tooLong", 255);
		rejectIfEmptyOrWhitespace(e, "newFamilyName",
								  "field.useredit.newFamilyName.empty");
		rejectIfTooLong(e, "newFamilyName",
						"field.useredit.newFamilyName.tooLong", 255);
		rejectIfEmptyOrWhitespace(e, "newMail", "field.useredit.newMail.empty");

		if (a.getNewMail().matches(REGEX)) {
			if (!a.getNewMail().equals(a.getOldUser().getMail()) //changed ...
				&& !userService.isMailAddressAvailable(a.getNewMail())) { // and available
				e.rejectValue("newMail", "field.useredit.newMail.notUnique");
			}
		} else {
			e.rejectValue("newMail", "field.useredit.newMail.invaild");
		}

		if (notNullAndNotEmpty(a.getNewPassword())) {
			if (notNullAndNotEmpty(a.getNewPasswordRepeat())) {
				if (a.getNewPassword().equals(a.getNewPasswordRepeat())) {
					if (a.getNewPassword().matches(REGEX)) {
						a.setNewPasswordHash(passwordService.hash(a.
								getNewPassword()));
					} else {
						e.rejectValue("newPassword",
									  "field.useredit.newPassword.lowSecurity");
					}
				} else {
					e.rejectValue("newPasswordRepeat",
								  "field.useredit.newPasswordRepeat.wrong");
				}
			} else {
				e.rejectValue("newPasswordRepeat",
							  "field.useredit.newPasswordRepeat.empty");
			}
		} else {
			if (notNullAndNotEmpty(a.getNewPasswordRepeat())) {
				e.rejectValue("newPassword", "field.useredit.newPassword.empty");
			}
		}
	}

}
