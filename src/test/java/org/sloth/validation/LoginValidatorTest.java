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
import org.sloth.validation.ErrorCodes.*;
import org.sloth.web.action.Login;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import static org.junit.Assert.*;

/**
 *
 * @author Christoph Fendrich
 */

public class LoginValidatorTest {

    @Test
    public void testEmptyMailLogin() {
        LoginValidator lv = new LoginValidator();
        Login l = new Login();
        l.setMail("");
        Errors errors = new BeanPropertyBindingResult(l, "login");
        lv.validate(l, errors);
        assertTrue(errors.hasErrors());
        assertEquals(LOGIN.EMPTY_MAIL, errors.getFieldError("login").getCode());

    }

    @Test
    public void testEmptyPasswordLogin() {
        LoginValidator lv = new LoginValidator();
        Login l = new Login();
        l.setPassword("");
        Errors errors = new BeanPropertyBindingResult(l, "login");
        lv.validate(l, errors);
        assertTrue(errors.hasErrors());
         assertEquals(LOGIN.EMPTY_PASSWORD, errors.getFieldError("login").getCode());
    }

}
