/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sloth.validator;

import org.sloth.model.Coordinate;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
			e.rejectValue("longitude", "field.coordinate.longitude.invalid");
		}
		if (lat > 180.0 || lat < -180.0) {
			e.rejectValue("longitude", "field.coordinate.latitude.invalid");
		}
	}

}
