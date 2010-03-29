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

import org.sloth.model.Observation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import static org.sloth.util.ValidatorUtils.*;

@Component
public class ObservationValidator implements Validator {

	private CoordinateValidator coordinateValidator;

	@Autowired
	public void setCategorieValidator(CoordinateValidator coordinateValidator) {
		this.coordinateValidator = coordinateValidator;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Observation.class);
	}

	@Override
	public void validate(Object t,
						 Errors e) {
		rejectIfEmptyOrWhitespace(e, "title", "field.observation.title.empty");
		rejectIfEmptyOrWhitespace(e, "description", "field.observation.description.empty");
		rejectIfTooLong(e, "title", "field.observation.title.tooLong", 255);
		rejectIfTooLong(e, "description", "field.observation.description.tooLong", 1000);
		rejectIfNull(e, "categorie", "field.observation.categorie.empty");
		rejectIfNull(e, "user", "field.observation.user.empty");
		e.setNestedPath("coordinate");
		invokeValidator(coordinateValidator, ((Observation) t).getCoordinate(), e);
		e.setNestedPath(null);
	}
}
