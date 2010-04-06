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

import java.util.List;
import org.junit.Test;
import org.sloth.model.Observation;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import static org.sloth.EntityFactory.*;
import static org.sloth.validation.ErrorCodes.OBSERVATION.*;
import static org.junit.Assert.*;

/**
 *
 * @author Christoph Fendrich
 */

public class ObservationValidatorTest {

    @Test
    public void testEmptyTitle() {
        ObservationValidator ov = new ObservationValidator();
        ov.setCategorieValidator(new CoordinateValidator());
        Observation o = getObservation(getCategorie(), getUser());
        o.setTitle("");
        Errors errors = new BeanPropertyBindingResult(o, "observation");
        ov.validate(o, errors);
        assertTrue(errors.hasErrors());
        List<ObjectError> list = errors.getAllErrors();
        assertEquals(EMPTY_TITLE, list.iterator().next().getCode());
    }

    @Test
    public void testEmptyDescription() {
        ObservationValidator ov = new ObservationValidator();
        ov.setCategorieValidator(new CoordinateValidator());
        Observation o = getObservation(getCategorie(), getUser());
        o.setDescription("");
        Errors errors = new BeanPropertyBindingResult(o, "observation");
        ov.validate(o, errors);
        assertTrue(errors.hasErrors());
        List<ObjectError> list = errors.getAllErrors();
        assertEquals(EMPTY_DESCRIPTION, list.iterator().next().getCode());
        }
    
    @Test
    public void testTooLongTitle() {
        ObservationValidator ov = new ObservationValidator();
        ov.setCategorieValidator(new CoordinateValidator());
        Observation o = getObservation(getCategorie(), getUser());
        StringBuffer buf = new StringBuffer();
            for (int i=0; i<256; i++) buf.append("a");
        o.setTitle(buf.toString());
        Errors errors = new BeanPropertyBindingResult(o, "observation");
        ov.validate(o, errors);
        assertTrue(errors.hasErrors());
        List<ObjectError> list = errors.getAllErrors();
        assertEquals(TOO_LONG_TITLE, list.iterator().next().getCode());
        }

    @Test
    public void testTooLongDescription() {
        ObservationValidator ov = new ObservationValidator();
        ov.setCategorieValidator(new CoordinateValidator());
        Observation o = getObservation(getCategorie(), getUser());
        StringBuffer buf = new StringBuffer();
            for (int i=0; i<1001; i++) buf.append("a");
        o.setDescription(buf.toString());
        Errors errors = new BeanPropertyBindingResult(o, "observation");
        ov.validate(o, errors);
        assertTrue(errors.hasErrors());
        List<ObjectError> list = errors.getAllErrors();
        assertEquals(TOO_LONG_DESCRIPTION, list.iterator().next().getCode());
        }

    @Test
    public void testEmptyCategorie() {
        ObservationValidator ov = new ObservationValidator();
        ov.setCategorieValidator(new CoordinateValidator());
        Observation o = getObservation(getCategorie(), getUser());
        o.setCategorie(null);
        Errors errors = new BeanPropertyBindingResult(o, "observation");
        ov.validate(o, errors);
        assertTrue(errors.hasErrors());
        List<ObjectError> list = errors.getAllErrors();
        assertEquals(EMPTY_CATEGORIE, list.iterator().next().getCode());
        }

    @Test
    public void testEmptyUser() {
        ObservationValidator ov = new ObservationValidator();
        ov.setCategorieValidator(new CoordinateValidator());
        Observation o = getObservation(getCategorie(), getUser());
        o.setUser(null);
        Errors errors = new BeanPropertyBindingResult(o, "observation");
        ov.validate(o, errors);
        assertTrue(errors.hasErrors());
        List<ObjectError> list = errors.getAllErrors();
        assertEquals(EMPTY_USER, list.iterator().next().getCode());
        }

}
