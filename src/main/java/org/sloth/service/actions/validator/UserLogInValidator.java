/*
 * Copyright (C) 2009  Stefan Arndt, Christian Autermann, Dustin Demuth,
 * 					 Christoph Fendrich, Christian Paluschek
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
package org.sloth.service.actions.validator;

import org.sloth.service.PasswordManager;
import org.sloth.service.UserManager;
import org.sloth.service.actions.UserLogIn;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserLogInValidator implements Validator {

	UserManager um;
	PasswordManager pm;

	public UserLogInValidator(UserManager um, PasswordManager pm){
		this.um = um;
		this.pm = pm;
	}

	@Override
	public boolean supports(Class type) {
		return type.equals(UserLogIn.class);
	}

	@Override
	public void validate(Object o, Errors errors) {
		UserLogIn login = (UserLogIn) o;
		pm.test(um.getUser(login.getMail()).getHashedPassword(),
				pm.hash(login.getPassword()));
		
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
