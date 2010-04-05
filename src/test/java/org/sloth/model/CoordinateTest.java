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

public class CoordinateTest {

	@Test
	public void testGetterAndSetter() {
		assertTrue(TestUtil.verifyMutable(new Coordinate()));
	}

	@Test
	public void testConstructor() {
		Coordinate a = new Coordinate();
		assertEquals(0d, a.getLatitude(), 0d);
		assertEquals(0d, a.getLongitude(), 0d);
		double lon = 231, lat = 234;
		Coordinate b = new Coordinate(lon, lat);
		assertEquals(lon, b.getLongitude(), 0);
		assertEquals(lat, b.getLatitude(), 0);
	}

	@Test
	public void testHashCode() throws Exception {
		Coordinate a = new Coordinate();
		int aHash = a.hashCode();
		assertEquals(aHash, new Coordinate().hashCode());
		a.setLongitude(2);
		assertTrue(a.hashCode() != aHash);
		aHash = a.hashCode();
		a.setLatitude(123);
		assertTrue(a.hashCode() != aHash);
	}

	@Test
	public void testEquals() throws Exception {
		Coordinate a = new Coordinate(), b = new Coordinate();
		assertEquals(a, b);
		a.setLongitude(5);
		assertTrue(!a.equals(b));
		b.setLongitude(5);
		assertEquals(a, b);
		a.setLatitude(5);
		assertTrue(!a.equals(b));
		b.setLatitude(5);
		assertEquals(a, b);
	}

	@Test
	public void testToString() throws Exception {
		double lon = 15, lat = 10;
		assertEquals("(" + lon + "," + lat + ")", new Coordinate(lon, lat).toString());
	}
}
