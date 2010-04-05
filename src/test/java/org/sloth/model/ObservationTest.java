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

public class ObservationTest {

	@Test
	public void testGetterAndSetter() {
		assertTrue(TestUtil.verifyMutable(new Observation()));
	}

	@Test
	public void testConstructor() {
		Observation a = new Observation();
		assertNotNull(a.getCreationDate());
		assertNull(a.getCategorie());
		assertNull(a.getUser());
		assertNull(a.getCoordinate());
		assertNull(a.getDescription());
		assertNull(a.getTitle());
		assertNull(a.getId());
		assertEquals(0, a.getVersion());
		Observation b = new Observation("title", "description", new User(), new Categorie(), new Coordinate());
		assertNotNull(b.getCreationDate());
		assertNotNull(b.getCategorie());
		assertNotNull(b.getUser());
		assertNotNull(b.getCoordinate());
		assertNull(b.getId());
		assertEquals("description", b.getDescription());
		assertEquals("title", b.getTitle());
		assertEquals(0, b.getVersion());
	}

	@Test
	public void testHashCode() {
		Observation a = new Observation();
		int aHash = a.hashCode();
		a.setUser(new User());
		assertTrue(a.hashCode() != aHash);
		aHash = a.hashCode();
		a.setCategorie(new Categorie());
		assertTrue(a.hashCode() != aHash);
		aHash = a.hashCode();
		a.setTitle("title");
		assertTrue(a.hashCode() != aHash);
		aHash = a.hashCode();
		a.setDescription("description");
		assertTrue(a.hashCode() != aHash);
		aHash = a.hashCode();
		a.setCoordinate(new Coordinate());
		assertTrue(a.hashCode() != aHash);
		aHash = a.hashCode();
		a.setId(new Long(21));
		assertTrue(a.hashCode() != aHash);
		aHash = a.hashCode();
		a.setVersion(123);
		assertTrue(a.hashCode() != aHash);
		aHash = a.hashCode();
		a.setCreationDate(new Date(System.currentTimeMillis() + 20));
		assertTrue(a.hashCode() != aHash);
		aHash = a.hashCode();
	}

	@Test
	public void testEquals() throws Exception {
		Observation a = new Observation();
		Observation b = new Observation();
		assertEquals(a, b);
		User u = new User();
		a.setUser(u);
		assertTrue(!a.equals(b));
		b.setUser(u);
		assertEquals(a, b);
		Coordinate c = new Coordinate();
		a.setCoordinate(c);
		assertTrue(!a.equals(b));
		b.setCoordinate(c);
		assertEquals(a, b);
		Categorie ca = new Categorie();
		a.setCategorie(ca);
		assertTrue(!a.equals(b));
		b.setCategorie(ca);
		assertEquals(a, b);
		Long id = new Long(2);
		a.setId(id);
		assertTrue(!a.equals(b));
		b.setId(id);
		assertEquals(a, b);
		int v = 2;
		a.setVersion(v);
		assertTrue(!a.equals(b));
		b.setVersion(v);
		assertEquals(a, b);
		String t = new String("title");
		a.setTitle(t);
		assertTrue(!a.equals(b));
		b.setTitle(t);
		assertEquals(a, b);
		String d = new String("description");
		a.setDescription(d);
		assertTrue(!a.equals(b));
		b.setDescription(d);
		assertEquals(a, b);
		Date cd = new Date(System.currentTimeMillis()+123);
		a.setCreationDate(cd);
		assertTrue(!a.equals(b));
		b.setCreationDate(cd);
		assertEquals(a, b);
	}
}
