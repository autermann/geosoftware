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

import com.gtcgroup.testutil.TestUtil;
import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Test;

public class UserTest {

	@Test
	public void testGetterAndSetter() {
		assertTrue(TestUtil.verifyMutable(new User(), null, 1, 0));
		User u = new User();
		assertNull(u.getUserGroup());
		u.setUserGroup(Group.USER);
		assertEquals(u.getUserGroup(), Group.USER);
		u.setUserGroup(null);
		assertNull(u.getUserGroup());
	}

	@Test
	public void testConstructor() {
		User a = new User();
		assertNotNull(a.getCreationDate());
		assertNull(a.getFamilyName());
		assertNull(a.getName());
		assertNull(a.getId());
		assertNull(a.getPassword());
		assertNull(a.getUserGroup());
		assertNull(a.getMail());
		assertEquals(0, a.getVersion());
		User b = new User("mail", "name", "familyName", "password", Group.USER);
		assertNotNull(b.getCreationDate());
		assertEquals("familyName", b.getFamilyName());
		assertEquals("name", b.getName());
		assertNull(b.getId());
		assertEquals("password", b.getPassword());
		assertEquals(Group.USER, b.getUserGroup());
		assertEquals("mail", b.getMail());
		assertEquals(0, b.getVersion());

	}

	@Test
	public void testHashCode() {
		User u = new User();
		int aHash = u.hashCode();
		u.setName("name");
		assertTrue(u.hashCode() != aHash);
		aHash = u.hashCode();
		u.setFamilyName("familyName");
		assertTrue(u.hashCode() != aHash);
		aHash = u.hashCode();
		u.setMail("mail");
		assertTrue(u.hashCode() != aHash);
		aHash = u.hashCode();
		u.setPassword("password");
		assertTrue(u.hashCode() != aHash);
		aHash = u.hashCode();
		u.setId(new Long(123));
		assertTrue(u.hashCode() != aHash);
		aHash = u.hashCode();
		u.setVersion(123);
		assertTrue(u.hashCode() != aHash);
		aHash = u.hashCode();
		u.setCreationDate(new Date(System.currentTimeMillis() + 21));
		assertTrue(u.hashCode() != aHash);
		aHash = u.hashCode();
		u.setUserGroup(Group.USER);
		assertTrue(u.hashCode() != aHash);
		aHash = u.hashCode();
	}

	@Test
	public void testEquals() throws Exception {
		User a = new User();
		User b = new User();
		assertEquals(a, b);
		a.setName("name");
		assertTrue(!a.equals(b));
		b.setName("name");
		assertEquals(a, b);
		a.setFamilyName("familyName");
		assertTrue(!a.equals(b));
		b.setFamilyName("familyName");
		assertEquals(a, b);
		a.setMail("mail");
		assertTrue(!a.equals(b));
		b.setMail("mail");
		assertEquals(a, b);
		a.setPassword("password");
		assertTrue(!a.equals(b));
		b.setPassword("password");
		assertEquals(a, b);
		a.setId(new Long(123));
		assertTrue(!a.equals(b));
		b.setId(new Long(123));
		assertEquals(a, b);
		a.setVersion(123);
		assertTrue(!a.equals(b));
		b.setVersion(123);
		assertEquals(a, b);
		Date d = new Date(System.currentTimeMillis() + 123);
		a.setCreationDate(d);
		assertTrue(!a.equals(b));
		b.setCreationDate(d);
		assertEquals(a, b);
		a.setUserGroup(Group.USER);
		assertTrue(!a.equals(b));
		b.setUserGroup(Group.USER);
		assertEquals(a, b);
	}
}
