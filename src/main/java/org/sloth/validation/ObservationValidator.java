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

import static org.sloth.util.ValidationUtils.rejectIfNull;
import static org.sloth.util.ValidationUtils.rejectIfTooLong;
import static org.sloth.util.ValidationUtils.isNotEmptyOrWhitespace;

import static org.sloth.validation.ErrorCodes.OBSERVATION.EMPTY_CATEGORIE;
import static org.sloth.validation.ErrorCodes.OBSERVATION.EMPTY_DESCRIPTION;
import static org.sloth.validation.ErrorCodes.OBSERVATION.EMPTY_TITLE;
import static org.sloth.validation.ErrorCodes.OBSERVATION.EMPTY_USER;
import static org.sloth.validation.ErrorCodes.OBSERVATION.TOO_LONG_DESCRIPTION;
import static org.sloth.validation.ErrorCodes.OBSERVATION.TOO_LONG_TITLE;
import static org.springframework.validation.ValidationUtils.invokeValidator;
import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

import org.sloth.model.Observation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * A {@code Validator} for validating {@code Observation}s.
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 *
 */
@Component
public class ObservationValidator implements Validator {

	private CoordinateValidator coordinateValidator;

	@Autowired
	public void setCoordinateValidator(CoordinateValidator coordinateValidator) {
		this.coordinateValidator = coordinateValidator;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Observation.class);
	}

	@Override
	public void validate(Object t, Errors e) {
		rejectIfEmptyOrWhitespace(e, "title", EMPTY_TITLE);
		rejectIfEmptyOrWhitespace(e, "description", EMPTY_DESCRIPTION);
		rejectIfTooLong(e, "title", TOO_LONG_TITLE, 255);
		rejectIfTooLong(e, "description", TOO_LONG_DESCRIPTION, 1000);
		rejectIfNull(e, "categorie", EMPTY_CATEGORIE);
		rejectIfNull(e, "user", EMPTY_USER);
		e.setNestedPath("coordinate");
		invokeValidator(this.coordinateValidator, ((Observation) t)
				.getCoordinate(), e);
		e.setNestedPath(null);
	}
}
