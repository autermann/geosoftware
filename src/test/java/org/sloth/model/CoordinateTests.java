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

import org.junit.After;
import org.sloth.model.Coordinate;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class CoordinateTests {

	private Coordinate a, b, c, d;
	private final double lonA = 231847D, latA = 1234423D;
	private final double lonB = -12347D, latB = -1231234134423D;
	private final double lonC = 1234455687D, latC = -123424D;
	private final double lonD = 1234455687D, latD = -123424D;

	@Before
	public void setUp() throws Exception {
		a = new Coordinate(lonA, latA);
		b = new Coordinate(lonB, latB);
		c = new Coordinate(lonC, latC);
		d = new Coordinate(lonD, latD);
	}

	@Test
	public void latitude() throws Exception {
		Coordinate u = new Coordinate();
		assertEquals("checking default value of latitude", u.getLatitude(),
				0, 0);
		u.setLatitude(latA);
		assertEquals("checking correct setting of latitude", u.getLatitude(),
				latA, 0);

	}

	@Test
	public void longitude() throws Exception {
		Coordinate u = new Coordinate();
		assertEquals(u.getLongitude(), 0, 0);
		u.setLongitude(lonA);
		assertEquals(u.getLongitude(), lonA, 0);
	}

	@Test
	public void hash() throws Exception {

		assertEquals(c.hashCode(), d.hashCode());
		assertTrue(a.hashCode() != b.hashCode());
		assertTrue(b.hashCode() != c.hashCode());
		assertTrue(a.hashCode() != c.hashCode());
		int hash1 = a.hashCode();
		int hash2 = a.hashCode();
		assertEquals(hash1, hash2);
	}

	@Test
	public void equal() throws Exception {
		assertTrue(c.equals(d));
		assertTrue(d.equals(c));
		assertTrue(!a.equals(b));
		assertTrue(!b.equals(a));
		assertTrue(!a.equals(c));
		assertTrue(!c.equals(a));
		assertTrue(!a.equals(d));
		assertTrue(!d.equals(a));
	}

	@Test
	public void string() throws Exception {
		assertEquals("(" + lonA + "," + latA + ")", a.toString());
	}

	@After
	public void bla() {
	}

	@Test(expected = NullPointerException.class)
	public void exc() throws Exception {
		throw new NullPointerException();
	}
}
