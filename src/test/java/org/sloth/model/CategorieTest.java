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
import static org.junit.Assert.*;
import org.junit.Test;

public class CategorieTest {

	@Test
	public void testGetterAndSetter() {
		assertTrue(TestUtil.verifyMutable(new Categorie()));
	}

	@Test
	public void testConstructor() {
		Categorie a = new Categorie("title", "description", "iconFileName");
		assertEquals("description", a.getDescription());
		assertEquals("iconFileName", a.getIconFileName());
		assertEquals("title", a.getTitle());
		assertNull(a.getId());
		assertEquals(0, a.getVersion());
		Categorie b = new Categorie();
		assertNull(b.getDescription());
		assertNull(b.getIconFileName());
		assertNull(b.getTitle());
		assertNull(b.getId());
		assertEquals(0, b.getVersion());
	}

	@Test
	public void testEquals() {
		Categorie a = new Categorie();
		Categorie b = new Categorie();
		assertEquals(a, b);
		a.setDescription("asdf");
		assertTrue(!a.equals(b));
		b.setDescription("asdf");
		assertEquals(a, b);
		a.setIconFileName("asdf");
		assertTrue(!a.equals(b));
		b.setIconFileName("asdf");
		assertEquals(a, b);
		a.setTitle("asdf");
		assertTrue(!a.equals(b));
		b.setTitle("asdf");
		assertEquals(a, b);
		a.setId(new Long(123));
		assertTrue(!a.equals(b));
		b.setId(new Long(123));
		assertEquals(a, b);
		a.setVersion(123);
		assertTrue(!a.equals(b));
		b.setVersion(123);
		assertEquals(a, b);
	}

	@Test
	public void testHashCode() {
		Categorie a = new Categorie();
		int aHash = a.hashCode();
		assertEquals(aHash, new Categorie().hashCode());
		a.setDescription("asdf");
		assertTrue(a.hashCode() != aHash);
		aHash = a.hashCode();
		a.setIconFileName("asdf");
		assertTrue(a.hashCode() != aHash);
		aHash = a.hashCode();
		a.setTitle("asdf");
		assertTrue(a.hashCode() != aHash);
		aHash = a.hashCode();
		a.setVersion(a.getVersion() + 1);
		assertTrue(a.hashCode() != aHash);
		aHash = a.hashCode();
		a.setId(new Long(123));
		assertTrue(a.hashCode() != aHash);
	}

	@Test
	public void testToString() {
		Categorie a = new Categorie();
		a.setTitle("asdf");
		assertEquals("asdf", a.toString());
	}
}
