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
package org.sloth.model;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class ObservationCategorieTest {

    private ObservationCategorie a, b, c;
    private final String titleA = "Title of A";
    private final String titleB = "Title of A";
    private final String titleC = "Title of C";
    private final String descriptionA = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
    private final String descriptionB = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
    private final String descriptionC = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod";

    @Before
    public void setUp() throws Exception {
        a = new ObservationCategorie();
        b = new ObservationCategorie();
        c = new ObservationCategorie();
    }

    /**
     * Test of getTitle and setTitle method, of class ObservationCategorie.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitel");
        String expResult = null;
        String result = a.getTitle();
        assertEquals(expResult, result);
        System.out.println("setTitel");
        expResult = titleA;
        a.setTitle(titleA);
        result = a.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription and setDescription method,
     * of class ObservationCategorie.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String expResult = null;
        String result = a.getDescription();
        assertEquals(expResult, result);
        System.out.println("setDescription");
        expResult = descriptionA;
        a.setDescription(descriptionA);
        result = a.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ObservationCategorie.
     */
    @Test
    public void testEquals() {
        System.out.println("testEquals");
        a.setDescription(descriptionA);
        b.setDescription(descriptionB);

        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertTrue(!c.equals(a));
        assertTrue(!c.equals(b));
        assertTrue(!b.equals(c));
        assertTrue(!a.equals(c));
    }

    /**
     * Test of hashCode method, of class ObservationCategorie.
     */
    @Test
    public void testHashCode() {
        System.out.println("testHashCode");
        assertTrue(c.hashCode() == a.hashCode());
        a.setDescription(descriptionA);
        System.out.println(c.hashCode());
        System.out.println(a.hashCode());
        assertTrue(c.hashCode() != a.hashCode());
    }

    /**
     * Test of toString method, of class ObservationCategorie.
     */
    @Test
    public void testToString() {
        a.setDescription(descriptionA);
        a.setTitle(titleA);
        assertEquals(a.toString(), titleA);
    }

    /**
     * Test of getId method, of class ObservationCategorie.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        long expResult = 0L;
        long result = a.getId();
        assertEquals(expResult, result);

    }

    /**
     * Test of setId method, of class ObservationCategorie.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 12345L;
        a.setId(id);
        assertEquals(a.getId(), id);
    }

    /**
     * Test of isNew method, of class ObservationCategorie.
     */
    @Test
    public void testIsNew() {
        System.out.println("isNew");
        boolean expResult = true;
        boolean result = a.isNew();
        assertEquals(expResult, result);

        a.setId(12345L);
        expResult = false;
        result = a.isNew();
        assertEquals(expResult, result);

    }
}
