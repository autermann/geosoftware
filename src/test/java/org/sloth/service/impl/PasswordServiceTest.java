/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sloth.service.impl;

import java.util.Random;
import org.junit.Test;
import org.sloth.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import static org.junit.Assert.*;

@ContextConfiguration
public class PasswordServiceTest extends AbstractJUnit4SpringContextTests {

	private PasswordService passwordService;
	private Random rnd = new Random();

	@Autowired
	public void setPasswordService(PasswordService passwordService) {
		this.passwordService = passwordService;
	}

	@Test
	public void selfTest() {
		assertNotNull(passwordService);
	}

	@Test
	public void testHashLength() {
		StringBuffer buffer = new StringBuffer("");
		int length = passwordService.hash(buffer.toString()).length();
		for (int i = 0; i < 100; i++) {
			buffer.append("a");
			assertEquals(length, passwordService.hash(buffer.toString()).length());
		}
	}

	@Test
	public void testCheck() {
		for (int i = 0; i < 100; i++) {
			String pwd = randomString(rnd.nextInt(50));
			assertTrue(passwordService.check(passwordService.hash(pwd), pwd));
		}
	}

	public String randomString(int length) {
		char[] pwd = new char[length];
		for (int i = 0; i < pwd.length; i++)
			pwd[i] = (char) (33 + rnd.nextInt(94));
		return new String(pwd);
	}
}
