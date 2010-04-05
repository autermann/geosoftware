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

@Component
public class UserEditFormValidator extends AbstractUserActionValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UserEditFormAction.class);
	}

	@Override
	public void validate(Object t, Errors e) {
		UserEditFormAction a = (UserEditFormAction) t;
		if (!a.getEditingUser().getId().equals(a.getOldUser().getId())) { // editing someone else
			test(a, e);
		} else if (!notNullAndNotEmpty(a.getActualPassword())) {
			e.rejectValue("actualPassword", "field.useredit.actualpassword.empty");
		} else if (this.passwordService.check(a.getOldUser().getPassword(), a.getActualPassword())) {
			test(a, e);
			if (!a.getNewGroup().equals(a.getOldUser().getUserGroup())) {
				e.rejectValue("newGroup", "field.useredit.newGroup.canNotChangeOwnGroup");
			}
		} else {
			e.rejectValue("actualPassword", "field.useredit.actualpassword.wrong");
		}
	}

	private void test(UserEditFormAction a, Errors e) {
		rejectIfEmptyOrWhitespace(e, "newName", "field.useredit.newName.empty");
		rejectIfTooLong(e, "newName", "field.useredit.newName.tooLong", 255);
		rejectIfEmptyOrWhitespace(e, "newFamilyName", "field.useredit.newFamilyName.empty");
		rejectIfTooLong(e, "newFamilyName", "field.useredit.newFamilyName.tooLong", 255);
		rejectIfEmptyOrWhitespace(e, "newMail", "field.useredit.newMail.empty");
		String newMail = a.getNewMail();
		if (!newMail.matches(REGEX)) {
			e.rejectValue("newMail", "field.useredit.newMail.invaild");
		} else if (!newMail.equals(a.getOldUser().getMail()) // changed ...
				&& !this.userService.isMailAddressAvailable(newMail)) { // but not available
			e.rejectValue("newMail", "field.useredit.newMail.notUnique");
		}
		String newPassword = a.getNewPassword();
		String newPasswordRepeat = a.getNewPasswordRepeat();
		if (notNullAndNotEmpty(newPassword)) {
			if (!notNullAndNotEmpty(newPasswordRepeat)) {
				e.rejectValue("newPasswordRepeat", "field.useredit.newPasswordRepeat.empty");
			} else if (!newPassword.equals(newPasswordRepeat)) {
				e.rejectValue("newPasswordRepeat", "field.useredit.newPasswordRepeat.wrong");
			} else if (this.passwordService.meetsRecommendation(newPassword)) {
				a.setNewPasswordHash(this.passwordService.hash(newPassword));
			} else {
				e.rejectValue("newPassword", "field.useredit.newPassword.lowSecurity");
			}
		} else if (notNullAndNotEmpty(newPasswordRepeat)) {
			e.rejectValue("newPassword", "field.useredit.newPassword.empty");
		}
	}
}