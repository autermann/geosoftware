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
	private final User userA = new User();

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
		System.out.println("getDescription");
		String expResult = null;
		String result = a.getDescription();
		assertEquals(expResult, result);
		expResult = descriptionA;
		a.setDescription(descriptionA);
		result = a.getDescription();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getUser method, of class Observation.
	 */
	@Test
	public void testGetUser() {
		System.out.println("getUser");
		User expResult = null;
		User result = a.getUser();
		assertEquals(expResult, result);

		a.setUser(userA);
		expResult = userA;
		result = a.getUser();
		assertEquals(expResult, result);
	}

	/**
	 * Test of setUser method, of class Observation.
	 */
	@Test
	public void testSetUser() {
		System.out.println("setUser");
		a.setUser(userA);
		assertEquals(userA, a.getUser());

	}

	/**
	 * Test of getCreationDate method, of class Observation.
	 */
	@Test
	public void testGetCreationDate() {
		System.out.println("getCreationDate");
		Date expResult = new Date();
		Date result = a.getCreationDate();
		assertEquals(expResult, result);
	}

	/**
	 * Test of setCreationDate method, of class Observation.
	 */
	@Test
	public void testSetCreationDate() {
		System.out.println("setCreationDate");
		Date creationDate = null;
		a.setCreationDate(creationDate);
		assertEquals(a.getCreationDate(), creationDate);
	}

	/**
	 * Test of getObservationCategorie method, of class Observation.
	 */
	@Test
	public void testGetObservationCategorie() {
		System.out.println("getObservationCategorie");
		ObservationCategorie expResult = null;
		ObservationCategorie result = a.getObservationCategorie();
		assertEquals(expResult, result);

		ObservationCategorie oc = new ObservationCategorie();
		a.setObservationCategorie(oc);

		assertEquals(a.getObservationCategorie(), oc);
		
	}

	/**
	 * Test of setObservationCategorie method, of class Observation.
	 */
	@Test
	public void testSetObservationCategorie() {
		System.out.println("setObservationCategorie");
		
		ObservationCategorie oc = new ObservationCategorie();
		a.setObservationCategorie(oc);

		assertEquals(a.getObservationCategorie(), oc);
	}

	/**
	 * Test of getCoordinate method, of class Observation.
	 */
	@Test
	public void testGetCoordinate() {
		System.out.println("getCoordinate");
		Coordinate expResult = null;
		Coordinate result = a.getCoordinate();
		assertEquals(expResult, result);
		expResult = coordA;
		a.setCoordinate(coordA);
		result = a.getCoordinate();
		assertEquals(expResult, result);

	}

	/**
	 * Test of setCoordinate method, of class Observation.
	 */
	@Test
	public void testSetCoordinate() {
		System.out.println("setCoordinate");
		Coordinate coordinate = null;
		a.setCoordinate(coordinate);
		assertEquals(a.getCoordinate(), null);
	}

	/**
	 * Test of equals method, of class Observation.
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
	 * Test of hashCode method, of class Observation.
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
	 * Test of toString method, of class Observation.
	 */
	@Test
	public void testToString() {
		a.setDescription(descriptionA);
		a.setCoordinate(coordA);
		a.setTitle(titleA);
		Date k = new Date();
		String ks = k.toString();
		assertEquals(a.toString(), "["+a.getClass()+"]("+a.getId()+" - \""+titleA+"\" by "+a.getUser()+" @"+coordA.toString()+"|"+ks+" in "+a.getObservationCategorie()+")");
	}

	/**
	 * Test of getId method, of class Observation.
	 */
	@Test
	public void testGetId() {
		System.out.println("getId");
		long expResult = 0L;
		long result = a.getId();
		assertEquals(expResult, result);

	}

	/**
	 * Test of setId method, of class Observation.
	 */
	@Test
	public void testSetId() {
		System.out.println("setId");
		long id = 12345L;
		a.setId(id);
		assertEquals(a.getId(), id);
	}

	/**
	 * Test of isNew method, of class Observation.
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