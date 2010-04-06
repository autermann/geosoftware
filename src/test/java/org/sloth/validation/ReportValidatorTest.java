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
import org.sloth.model.Report;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import static org.sloth.validation.ErrorCodes.REPORT.*;
import static org.junit.Assert.*;

/**
 *
 * @author Christoph Fendrich
 */

public class ReportValidatorTest {

    @Test
    public void testEmptyDescription() {
        ReportValidator rv = new ReportValidator();
        Report r = new Report();
        r.setDescription("");
        Errors errors = new BeanPropertyBindingResult(r, "report");
        rv.validate(r, errors);
        assertTrue(errors.hasErrors());
        assertEquals(EMPTY_DESCRIPTION, errors.getFieldError("report").getCode());
    }
    
    @Test
    public void testTooLongDescription() {
        ReportValidator rv = new ReportValidator();
        Report r = new Report();
        StringBuffer buf = new StringBuffer();
            for (int i=0; i<101; i++) buf.append("a");
        r.setDescription(buf.toString());
        Errors errors = new BeanPropertyBindingResult(r, "report");
        rv.validate(r, errors);
        assertTrue(errors.hasErrors());
        assertEquals(TOO_LONG_DESCRIPTION, errors.getFieldError("report").getCode());
    }

    @Test
    public void testEmptyAuthor() {
        ReportValidator rv = new ReportValidator();
        Report r = new Report();
        r.setAuthor(null);
        Errors errors = new BeanPropertyBindingResult(r, "report");
        rv.validate(r, errors);
        assertTrue(errors.hasErrors());
        assertEquals(EMPTY_AUTHOR, errors.getFieldError("report").getCode());
    }

    @Test
    public void testEmptyObservation() {
        ReportValidator rv = new ReportValidator();
        Report r = new Report();
        r.setObservation(null);
        Errors errors = new BeanPropertyBindingResult(r, "report");
        rv.validate(r, errors);
        assertTrue(errors.hasErrors());
        assertEquals(EMPTY_OBSERVATION, errors.getFieldError("report").getCode());
    }

}
