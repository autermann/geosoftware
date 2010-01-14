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
package org.sloth.pojo;

import org.sloth.model.UserRight;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 *
 * @author Christian Autermann
 */
public class UserRightTests {

	private UserRight r1, r2, r3;
	private final String description1 = "sdfgxchvasdfasdfabhsk";
	private final String description2 = "sdfgxchvjbknabhsaadfasdfasdfasdk";
	private final String title1 = "kdflnjbandsfjk.n as.jkdf df";
	private final String title2 = "kdadbfhasdf ,adsflsjhasdf";
	private final int value1 = 1, value2 = 2;

	@Before
	public void setUp() {
		r1 = new UserRight(title1, description1, value1);
		r2 = new UserRight(title2, description2, value2);
		r3 = new UserRight(title1, description1, value1);
	}

	@Test
	public void equals() {
		assertEquals(r1, r3);
		assertTrue(!r1.equals(r2));
	}

	@Test
	public void hash() {
		assertEquals(r1.hashCode(), r3.hashCode(), 0);
	}

	@Test
	public void defaultValues(){
		UserRight r4 = new UserRight();
		assertTrue(r4.getDescription() == null);
		assertTrue(r4.getTitle() == null);
		assertTrue(r4.getValue() == -1);
	}

	@Test
	public void value(){
		assertTrue(UserRight.AMDINISTRATOR_VALUE > UserRight.USER_VALUE);
		assertTrue(UserRight.USER_VALUE > UserRight.GUEST_VALUE);
	}
}
