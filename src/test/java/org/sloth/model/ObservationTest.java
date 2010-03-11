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

import java.util.Date;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class ObservationTest {

	private Observation a, b, c;
//	private final Long idA, idB, idC;
	private final String titleA = "Title of A", titleB = "Title of A", titleC = "Title of C";
	private final String descriptionA = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
	private final String descriptionB = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
	private final String descriptionC = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod";
//	private final User userA, userB, userC;

//  Coordinate Values
	private final double lonA = 231847D, latA = 1234423D;
	private final double lonB = -12347D, latB = -1231234134423D;

	private final Coordinate coordA = new Coordinate(lonA, latA);
	private final Coordinate coordB = new Coordinate(lonA, latA);
	private final Coordinate coordC = new Coordinate(lonB, latB);

	@Before
	public void setUp() throws Exception {

		a = new Observation();
		b = new Observation();
		c = new Observation();

	}

//	/**
//	 * Test stub to prevent "no test found"-errors...
//	 */
//	@org.junit.Test(expected=UnsupportedOperationException.class)
//	public void stub(){
//		throw new UnsupportedOperationException("Not supported yet.");
//	}

	/**
	 * Test of getTitle method, of class Observation.
	 */
	@Test
	public void testGetTitleNoTitle() {
		System.out.println("getTitle NO Title");
		// No Title Is Set:
		String expResult = null;
		String result = a.getTitle();
		assertEquals(expResult, result);
	}

	/**
	 * Test of setTitle method, of class Observation.
	 */
	@Test
	public void testSetTitle() {
		System.out.println("setTitle");
		String title = titleA;
		a.setTitle(title);
		assertEquals(titleA, a.getTitle());
	}
	
	@Test
	public void testGetTitleWithTitle() {
		System.out.println("getTitle WITH Title");
		System.out.println("setTitle");
		String title = titleA;
		a.setTitle(title);
		String expResult = titleA;
		String result = a.getTitle();
		assertEquals(expResult, result);
	}



	/**
	 * Test of getDescription method, of class Observation.
	 */
	@Test
	public void testGetDescription() {
		System.out.println("getDescription NO Description");
		String expResult = null;
		String result = a.getDescription();
		assertEquals(expResult, result);
	}

	/**
	 * Test of setDescription method, of class Observation.
	 */
	@Test
	public void testSetDescription() {
		System.out.println("setDescription");
		String description = "";
		Observation instance = new Observation();
		instance.setDescription(description);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getUser method, of class Observation.
	 */
	@Test
	public void testGetUser() {
		System.out.println("getUser");
		Observation instance = new Observation();
		User expResult = null;
		User result = instance.getUser();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setUser method, of class Observation.
	 */
	@Test
	public void testSetUser() {
		System.out.println("setUser");
		User user = null;
		Observation instance = new Observation();
		instance.setUser(user);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getCreationDate method, of class Observation.
	 */
	@Test
	public void testGetCreationDate() {
		System.out.println("getCreationDate");
		Observation instance = new Observation();
		Date expResult = null;
		Date result = instance.getCreationDate();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setCreationDate method, of class Observation.
	 */
	@Test
	public void testSetCreationDate() {
		System.out.println("setCreationDate");
		Date creationDate = null;
		Observation instance = new Observation();
		instance.setCreationDate(creationDate);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getObservationCategorie method, of class Observation.
	 */
	@Test
	public void testGetObservationCategorie() {
		System.out.println("getObservationCategorie");
		Observation instance = new Observation();
		ObservationCategorie expResult = null;
		ObservationCategorie result = instance.getObservationCategorie();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setObservationCategorie method, of class Observation.
	 */
	@Test
	public void testSetObservationCategorie() {
		System.out.println("setObservationCategorie");
		ObservationCategorie oc = null;
		Observation instance = new Observation();
		instance.setObservationCategorie(oc);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getCoordinate method, of class Observation.
	 */
	@Test
	public void testGetCoordinate() {
		System.out.println("getCoordinate");
		Observation instance = new Observation();
		Coordinate expResult = null;
		Coordinate result = instance.getCoordinate();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setCoordinate method, of class Observation.
	 */
	@Test
	public void testSetCoordinate() {
		System.out.println("setCoordinate");
		Coordinate coordinate = null;
		Observation instance = new Observation();
		instance.setCoordinate(coordinate);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of equals method, of class Observation.
	 */
	@Test
	public void testEquals() {
		System.out.println("equals");
		Object obj = null;
		Observation instance = new Observation();
		boolean expResult = false;
		boolean result = instance.equals(obj);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of hashCode method, of class Observation.
	 */
	@Test
	public void testHashCode() {
		System.out.println("hashCode");
		Observation instance = new Observation();
		int expResult = 0;
		int result = instance.hashCode();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of toString method, of class Observation.
	 */
	@Test
	public void testToString() {
		System.out.println("toString");
		Observation instance = new Observation();
		String expResult = "";
		String result = instance.toString();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getId method, of class Observation.
	 */
	@Test
	public void testGetId() {
		System.out.println("getId");
		Observation instance = new Observation();
		long expResult = 0L;
		long result = instance.getId();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setId method, of class Observation.
	 */
	@Test
	public void testSetId() {
		System.out.println("setId");
		long id = 0L;
		Observation instance = new Observation();
		instance.setId(id);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of isNew method, of class Observation.
	 */
	@Test
	public void testIsNew() {
		System.out.println("isNew");
		Observation instance = new Observation();
		boolean expResult = false;
		boolean result = instance.isNew();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
