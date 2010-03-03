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

import org.sloth.service.actions.ObservationCreation;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import static org.springframework.validation.ValidationUtils.*;

public class ObservationCreationValidator implements Validator {

	@Override
	public boolean supports(Class type) {
		return type.equals(ObservationCreation.class);
	}

	@Override
	public void validate(Object o, Errors errors) {
		rejectIfEmptyOrWhitespace(errors, "title", "title.empty");
		rejectIfEmptyOrWhitespace(errors, "description", "description.empty");
		ObservationCreation oc = (ObservationCreation) o;
		if (oc.getUserId() > 0) {
			errors.rejectValue("userId","userId.negative");
		}
	}

}
