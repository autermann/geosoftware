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

package org.sloth.validation;

import org.junit.Test;
import org.sloth.model.Coordinate;
import org.sloth.validation.ErrorCodes.COORDINATE;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import static org.junit.Assert.*;

/**
 *
 * @author Christoph Fendrich
 */

public class CoordinateValidatorTest {

    @Test
    public void testInvalideLongitudeLower() {
        CoordinateValidator cv = new CoordinateValidator();
        Coordinate c = new Coordinate();
        c.setLongitude(-181);
        Errors errors = new BeanPropertyBindingResult(c, "coordinate");
        cv.validate(c, errors);
        assertTrue(errors.hasErrors());
         assertEquals(COORDINATE.INVALID_LONGITUDE, errors.getFieldError("longitude").getCode());
    }

    @Test
    public void testInvalidLongitudeHigher() {
        CoordinateValidator cv = new CoordinateValidator();
        Coordinate c = new Coordinate();
        c.setLongitude(181);
        Errors errors = new BeanPropertyBindingResult(c, "coordinate");
        cv.validate(c, errors);
        assertTrue(errors.hasErrors());
         assertEquals(COORDINATE.INVALID_LONGITUDE, errors.getFieldError("longitude").getCode());
    }

    @Test
    public void testEmptyLongitude() {
        CoordinateValidator cv = new CoordinateValidator();
        Coordinate c = new Coordinate();
        c.setLongitude(Double.NaN);
        Errors errors = new BeanPropertyBindingResult(c, "coordinate");
        cv.validate(c, errors);
        assertTrue(errors.hasErrors());
        assertEquals(COORDINATE.EMPTY_LONGITUDE, errors.getFieldError("longitude").getCode());
    }

    @Test
    public void testInvalideLatitudeLower() {
        CoordinateValidator cv = new CoordinateValidator();
        Coordinate c = new Coordinate();
        c.setLatitude(-181);
        Errors errors = new BeanPropertyBindingResult(c, "coordinate");
        cv.validate(c, errors);
        assertTrue(errors.hasErrors());
        assertEquals(COORDINATE.INVALID_LATITUDE, errors.getFieldError("latitude").getCode());
    }

    @Test
    public void testInvalideLatitudeHigher() {
        CoordinateValidator cv = new CoordinateValidator();
        Coordinate c = new Coordinate();
        c.setLatitude(181);
        Errors errors = new BeanPropertyBindingResult(c, "coordinate");
        cv.validate(c, errors);
        assertTrue(errors.hasErrors());
        assertEquals(COORDINATE.INVALID_LATITUDE, errors.getFieldError("latitude").getCode());
    }

    @Test
    public void testEmptyLatitude() {
        CoordinateValidator cv = new CoordinateValidator();
        Coordinate c = new Coordinate();
        c.setLatitude(Double.NaN);
        Errors errors = new BeanPropertyBindingResult(c, "coordinate");
        cv.validate(c, errors);
        assertTrue(errors.hasErrors());
        assertEquals(COORDINATE.EMPTY_LATITUDE, errors.getFieldError("latitude").getCode());
    }

}
