/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sloth.validation;

import org.sloth.model.Coordinate;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import static org.sloth.validation.ErrorCodes.*;

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
			e.rejectValue("longitude", COORDINATE.INVALID_LONGITUDE);
		}
		if (lat > 180.0 || lat < -180.0 || lat == Double.NaN) {
			e.rejectValue("latitude", COORDINATE.INVALID_LATITUDE);
		}
		if (Double.isNaN(lat)) {
			e.rejectValue("latitude", COORDINATE.EMPTY_LATITUDE);
		}
		if (Double.isNaN(lon)) {
			e.rejectValue("longitude", COORDINATE.EMPTY_LONGITUDE);
		}
	}
}
