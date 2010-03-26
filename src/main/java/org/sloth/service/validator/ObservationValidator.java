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

import org.sloth.model.Observation;
import org.springframework.validation.Errors;

public class ObservationValidator {

	public void validate(Observation obj,
						 Errors errors) {
		if (obj.getCategorie() == null)
			errors.rejectValue("categorie", "field.needed");
		if (obj.getCoordinate() == null)
			errors.rejectValue("coordinate", "field.needed");
		if (obj.getCreationDate() == null)
			errors.rejectValue("creationDate", "field.needed");
		if (obj.getUser() == null)
			errors.rejectValue("user", "field.needed");
		if (obj.getTitle() == null)
			errors.rejectValue("title", "field.needed");
		else if (obj.getTitle().trim().isEmpty())
			errors.rejectValue("title", "field.needed");
		else if (obj.getDescription().length() > 1000)
			errors.rejectValue("title", "field.tooLong");
		if (obj.getDescription() == null)
			errors.rejectValue("description", "field.needed");
		else if (obj.getDescription().trim().isEmpty())
			errors.rejectValue("description", "field.needed");
		else if (obj.getDescription().length() > 1000)
			errors.rejectValue("description", "field.tooLong");
	}
}
