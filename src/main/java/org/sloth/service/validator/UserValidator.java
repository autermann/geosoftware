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
package org.sloth.service.validator;

import javax.naming.spi.DirStateFactory.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.Group;
import org.sloth.model.User;
import org.sloth.service.PasswordService;
import org.sloth.service.UserService;
import org.sloth.util.Config;
import org.sloth.web.user.UserEditFormAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

/**
 * @todo
 * @author auti
 */
public class UserValidator {

	private PasswordService passwordService;
	private UserService userService;
	private static String mailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
	private static final Logger logger = LoggerFactory.getLogger(UserValidator.class);

	static {
		String regex = Config.getProperty("mail.regex");
		if (regex != null && StringUtils.hasText(regex))
			mailRegex = regex;
	}

	@Autowired
	public void setPasswordManager(PasswordService passwordManager) {
		this.passwordService = passwordManager;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void validate(User u,
						 Errors errors) {
		logger.info("Validating User: {}", u);
		rejectIfEmptyOrWhitespace(errors, "familyName", "field.required");
		rejectIfEmptyOrWhitespace(errors, "name", "field.required");
		rejectIfEmptyOrWhitespace(errors, "password", "field.required");
		rejectIfEmptyOrWhitespace(errors, "mail", "field.required");
		if (!passwordService.meetsRecommendation(u.getPassword()))
			errors.rejectValue("password", "field.badpassword");
		u.setMail(u.getMail().trim().toLowerCase());
		if (!u.getMail().matches(mailRegex)) {
			errors.rejectValue("mail", "field.invalidMailAddress");
			return;
		}
		if (userService.get(u.getMail()) != null) {
			errors.rejectValue("mail", "field.mail.notunique");
			logger.info("Duplicate Mail Address");
		}
	}

	private boolean notNullNotTooLongAndNotEmpty(String test,
												 int maxLength) {
		return test != null && !test.trim().isEmpty() && (test.trim().length()
														   <= maxLength);
	}

	public User validate(UserEditFormAction a,
						 Errors errors,
						 User editingUser) {
		String newPasswordHash = null;
		String newMailAddress = null;
		String newFamilyName = null;
		String newName = null;
		Group newGroup = null;
		User u = a.getOldUser();
		boolean isAuthorized = false;
		if (editingUser.getId().equals(u.getId()))
			// users or admin editing himself
			if (a.getActualPassword().trim().isEmpty())
				errors.reject("password.needed");
			else if (passwordService.check(a.getOldUser().getPassword(), a.getActualPassword()))
				isAuthorized = true;
			else
				errors.reject("field.wrongPassword");
		else
			// admin editing someone other
			isAuthorized = true;

		if (isAuthorized) {
			// Password was right; check for new Password
			if (!a.getNewPassword().isEmpty())
				// new password was entered; check for right repeat
				if (a.getNewPassword().equals(a.getNewPasswordRepeat()))
					// repeat was right
					newPasswordHash = passwordService.hash(a.getNewPassword());
				else // repeat was false
					errors.rejectValue("newPassword", "password.badrepeat");
			// check if the mail address has changes
			if (!a.getNewMail().equals(u.getMail()))
				// mail address changed; check for right syntax
				if (!a.getNewMail().matches(mailRegex))
					errors.reject("newMail", "mail.invalidMailAddress");
				// mail address is vaild; check for availability
				else if (userService.get(a.getNewMail()) == null)
					// new mail address is unique
					newMailAddress = a.getNewMail();
				else
					// new mail address is not unique
					errors.rejectValue("newMail", "field.mail.notunique");
			// copy even if not edited... does not matter
			if (notNullNotTooLongAndNotEmpty(a.getNewFamilyName(), 255))
				newFamilyName = a.getNewFamilyName();
			else
				errors.rejectValue("newFamilyName", "field.invalid");
			if (notNullNotTooLongAndNotEmpty(a.getNewName(), 255))
				newName = a.getNewName();
			else
				errors.rejectValue("newName", "field.invalid");
		}
		if (errors.hasErrors())
			return null;
		else {
			// Update User only if there are no errors
			if (newPasswordHash != null)
				u.setPassword(newPasswordHash);
			if (newFamilyName != null)
				u.setFamilyName(newFamilyName);
			if (newName != null)
				u.setName(newName);
			if (newMailAddress != null)
				u.setMail(newMailAddress);
			if (newGroup != null)
				u.setUserGroup(newGroup);
			return u;
		}
	}
}
