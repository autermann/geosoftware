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

import static org.sloth.validation.ErrorCodes.COORDINATE.EMPTY_LATITUDE;
import static org.sloth.validation.ErrorCodes.COORDINATE.EMPTY_LONGITUDE;
import static org.sloth.validation.ErrorCodes.COORDINATE.INVALID_LATITUDE;
import static org.sloth.validation.ErrorCodes.COORDINATE.INVALID_LONGITUDE;

import org.sloth.model.Coordinate;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * A {@code Validator} for validating {@code Coordinate}s.
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
@Component
public class CoordinateValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Coordinate.class);
	}

	@Override
	public void validate(Object t, Errors e) {
		Coordinate c = (Coordinate) t;
		double lon = c.getLongitude(), lat = c.getLatitude();

		if (lon > 180.0 || lon < -180.0) {
			e.rejectValue("longitude", INVALID_LONGITUDE);
		}
		if (lat > 180.0 || lat < -180.0 || lat == Double.NaN) {
			e.rejectValue("latitude", INVALID_LATITUDE);
		}
		if (Double.isNaN(lat)) {
			e.rejectValue("latitude", EMPTY_LATITUDE);
		}
		if (Double.isNaN(lon)) {
			e.rejectValue("longitude", EMPTY_LONGITUDE);
		}
	}
}
