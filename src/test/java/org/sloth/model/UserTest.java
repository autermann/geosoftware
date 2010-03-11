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

public class UserTest {

	private User aUser = new User();
	private final String nameA = "test1";
	private final String familyNameA = "User1";
	private final String eMailA = "test1.User1@domain.tld";
	private final String hPwdA = "qwertz";
	private final Long idA = 12345L;
	private final UserRight urA = new UserRight();

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test of geteMail method, of class User.
	 */
	@Test
	public void testGeteMail() {
		System.out.println("geteMail");
		String expResult = null;
		String result = aUser.geteMail();
		assertEquals(expResult, result);

		aUser.seteMail(eMailA);
		assertEquals(eMailA, aUser.geteMail());

	}

	/**
	 * Test of seteMail method, of class User.
	 */
	@Test
	public void testSeteMail() {
		System.out.println("seteMail");
		aUser.seteMail(eMailA);
		assertEquals(eMailA, aUser.geteMail());
	}

	/**
	 * Test of getName method, of class User.
	 */
	@Test
	public void testGetName() {
		System.out.println("getName");
		String expResult = null;
		String result = aUser.getName();
		assertEquals(expResult, result);

		aUser.setName(nameA);
		assertEquals(nameA, aUser.getName());


	}

	/**
	 * Test of setName method, of class User.
	 */
	@Test
	public void testSetName() {
		System.out.println("setName");

		aUser.setName(nameA);
		assertEquals(nameA, aUser.getName());
	}

	/**
	 * Test of getFamilyName method, of class User.
	 */
	@Test
	public void testGetFamilyName() {
		System.out.println("getFamilyName");
		String expResult = null;
		String result = aUser.getFamilyName();
		assertEquals(expResult, result);

		aUser.setFamilyName(familyNameA);
		assertEquals(familyNameA, aUser.getFamilyName());
	}

	/**
	 * Test of setFamilyName method, of class User.
	 */
	@Test
	public void testSetFamilyName() {

		aUser.setFamilyName(familyNameA);
		assertEquals(familyNameA, aUser.getFamilyName());
	}

	/**
	 * Test of getHashedPassword method, of class User.
	 */
	@Test
	public void testGetHashedPassword() {
		System.out.println("getHashedPassword");
		String expResult = null;
		String result = aUser.getHashedPassword();
		assertEquals(expResult, result);

		aUser.setHashedPassword(hPwdA);
		assertEquals(hPwdA, aUser.getHashedPassword());

	}

	/**
	 * Test of setHashedPassword method, of class User.
	 */
	@Test
	public void testSetHashedPassword() {
		aUser.setHashedPassword(hPwdA);
		assertEquals(hPwdA, aUser.getHashedPassword());
	}

	/**
	 * Test of getCreationDate method, of class User.
	 */
	@Test
	public void testGetCreationDate() {
		System.out.println("getCreationDate");
		Date expResult = new Date();
		Date result = aUser.getCreationDate();
		assertEquals(expResult, result);

	}

	/**
	 * Test of setCreationDate method, of class User.
	 */
	@Test
	public void testSetCreationDate() {
		System.out.println("setCreationDate");
		Date creationDate = new Date();
		aUser.setCreationDate(creationDate);
		assertEquals(creationDate, aUser.getCreationDate());
	}

	/**
	 * Test of equals method, of class User.
	 */
	@Test
	public void testEquals() {
		System.out.println("Equals");
		User bUser = new User();
		User cUser = new User();
		aUser.setFamilyName(familyNameA);
		aUser.setName(nameA);

		bUser.setFamilyName(familyNameA);
		bUser.setName(nameA);

		cUser.setFamilyName(familyNameA);
		cUser.setName("child");

		assertTrue(!cUser.equals(aUser));
		assertTrue(bUser.equals(aUser));

	}

	/**
	 * Test of hashCode method, of class User.
	 */
	@Test
	public void testHashCode() {
		System.out.println("HashCode");
		User bUser = new User();
		aUser.setFamilyName(familyNameA);
		aUser.setName(nameA);
		assertFalse(aUser.hashCode() == bUser.hashCode());

	}

	/**
	 * Test of toString method, of class User.
	 */
	@Test
	public void testToString() {
		System.out.println("toString");
		aUser.setFamilyName(familyNameA);
		aUser.setName(nameA);
		aUser.setId(idA);
		String expresult = familyNameA + ", " + nameA + " (" + idA.toString() + ")";

		assertEquals(expresult, aUser.toString());

	}

	/**
	 * Test of getUserRight method, of class User.
	 */
	@Test
	public void testGetUserRight() {
		System.out.println("getUserRight");
		assertEquals(aUser.getUserRight(), null);
		aUser.setUserRight(urA);
		assertEquals(aUser.getUserRight(), urA);

	}

	/**
	 * Test of setUserRight method, of class User.
	 */
	@Test
	public void testSetUserRight() {
		System.out.println("setUserRight");
		aUser.setUserRight(urA);
		assertEquals(aUser.getUserRight(), urA);
	}

	/**
	 * Test of getId method, of class User.
	 */
	@Test
	public void testGetId() {
		System.out.println("getId");
		long expResult = 0L;
		long result = aUser.getId();
		assertEquals(expResult, result);

		aUser.setId(idA);
		expResult = idA;
		result = aUser.getId();
		assertEquals(expResult, result);

	}

	/**
	 * Test of setId method, of class User.
	 */
	@Test
	public void testSetId() {
		System.out.println("setId");
		aUser.setId(idA);
		Long expResult = idA;
		Long result = aUser.getId();
		assertEquals(expResult, result);
	}

	/**
	 * Test of isNew method, of class User.
	 */
	@Test
	public void testIsNew() {
		System.out.println("isNew");
		assertTrue(aUser.isNew());
		aUser.setId(idA);
		assertFalse(aUser.isNew());

	}
}
