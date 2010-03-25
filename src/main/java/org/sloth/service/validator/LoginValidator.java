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

import org.sloth.service.Login;
import org.springframework.validation.Errors;
//TODO JavaDoc
public class LoginValidator extends Validator<Login> {

	@Override
	public void validate(Login t,
						 Errors errors) {
		if (t == null)
			throw new NullPointerException("Can't validate null.");
		else
			logger.info("Validating: {}", t);
		if (t.getMail() == null || t.getMail().trim().isEmpty())
			errors.rejectValue("mail", "field.required");
		if (t.getPassword() == null || t.getPassword().trim().isEmpty())
			errors.rejectValue("password", "field.required");
	}
}
