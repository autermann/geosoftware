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

import org.sloth.model.Categorie;
import org.sloth.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import static org.sloth.util.ValidationUtils.*;
import static org.sloth.validation.ErrorCodes.CATEGORIE.*;

@Component
public class CategorieValidator implements Validator {

	private ObservationService observationService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Categorie.class);
	}

	@Autowired
	public void setObservationService(ObservationService observationService) {
		this.observationService = observationService;
	}

	@Override
	public void validate(Object t, Errors e) {
		rejectIfEmptyOrWhitespace(e, "title", EMPTY_TITLE);
		rejectIfEmptyOrWhitespace(e, "description", EMPTY_DESCRIPTION);
		rejectIfEmptyOrWhitespace(e, "iconFileName", EMPTY_ICON_FILE_NAME);
		rejectIfTooLong(e, "title", TOO_LON_TITLE, 255);
		rejectIfTooLong(e, "description", TOO_LONG_DESCRIPTION, 255);
		rejectIfTooLong(e, "iconFileName", TOO_LONG_ICON_FILE_NAME, 255);
		Categorie c = (Categorie) t;
		Categorie orig = this.observationService.getCategorieByTitle(c.getTitle());
		if (notNull(orig) && (c.isNew() || (c.getId() != orig.getId()))) {
			e.rejectValue("title", NOT_UNIQUE_TITLE);
		}

	}
}
