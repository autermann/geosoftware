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

import org.sloth.model.User;
import org.sloth.service.PasswordService;
import org.sloth.service.UserService;
import org.sloth.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

/**
 * @todo
 * @author auti
 */
public class UserValidator extends Validator<User> {

	private PasswordService passwordService;
	private UserService userService;
	private static String mailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";

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

	@Override
	public void validate(User u,
						 Errors errors) {
		logger.info("Validating User: {}", u);
		rejectIfEmptyOrWhitespace(errors, "familyName", "field.required");
		rejectIfEmptyOrWhitespace(errors, "name", "field.required");
		rejectIfEmptyOrWhitespace(errors, "password", "field.required");
		rejectIfEmptyOrWhitespace(errors, "mail", "field.required");
		if (!passwordService.meetsRecommendation(u.getPassword()))
			errors.rejectValue("password", "field.badpassword");
		if (!u.getMail().trim().matches(mailRegex))
			errors.rejectValue("mail", "field.invalidMailAddress");
		if (userService.get(u.getMail()) != null) {
			errors.rejectValue("mail", "field.mail.notunique");
			logger.info("Duplicate Mail Address");
		}
	}
}
