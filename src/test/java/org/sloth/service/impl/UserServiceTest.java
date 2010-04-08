/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sloth.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.sloth.model.User;
import org.sloth.persistence.UserDao;
import org.sloth.service.PasswordService;
import static org.junit.Assert.*;
import static org.sloth.EntityFactory.*;

/**
 * 
 * @author auti
 */
public class UserServiceTest {

	private UserDao userDao;
	private UserServiceImpl userService;
	private PasswordService passwordService;

	@Before
	public void setUp() {
		userService = new UserServiceImpl();
		passwordService = new BCryptPasswordService();
		userDao = new InMemoryUserDao();
		userService.setPasswordService(passwordService);
		userService.setUserDao(userDao);
		reset();
	}

	public UserServiceTest() {
	}

	/**
	 * Test of getUsers method, of class UserServiceImpl.
	 */
	@Test
	public void testGetUsers() {
		User u = getUser();
		u.setPassword("password");
		userService.registrate(u);
		assertTrue(passwordService.check(u.getPassword(), "password"));
		assertEquals(1, userService.getUsers().size());
	}

	/**
	 * Test of get method, of class UserServiceImpl.
	 */
	@Test
	public void testGet_String() {
		User u = getUser();
		userService.registrate(u);
		assertNull(userService.get("asdf"));
		assertEquals(u, userService.get(u.getMail()));
	}

	/**
	 * Test of get method, of class UserServiceImpl.
	 */
	@Test
	public void testGet_Long() {
		User u = getUser();
		userService.registrate(u);
		assertNotNull(u.getId());
		assertEquals(u, userService.get(u.getId()));
		assertNull(userService.get(42L));
	}

	/**
	 * Test of update method, of class UserServiceImpl.
	 */
	@Test
	public void testUpdate() {
		User u = getUser();
		userService.registrate(u);
		assertNotNull(u.getId());
		u.setFamilyName("New Family Name");
		userService.update(u);
		assertEquals("New Family Name", userService.get(u.getId()).getFamilyName());
	}

	/**
	 * Test of delete method, of class UserServiceImpl.
	 */
	@Test
	public void testDelete_Long() {
		User u = getUser();
		userService.registrate(u);
		assertNotNull(u.getId());
		userService.delete(u.getId());
		assertNull(userService.get(u.getId()));
	}

	/**
	 * Test of delete method, of class UserServiceImpl.
	 */
	@Test
	public void testDelete_User() {
		User u = getUser();
		userService.registrate(u);
		assertNotNull(u.getId());
		userService.delete(u);
		assertNull(userService.get(u.getId()));
	}

	/**
	 * Test of registrate method, of class UserServiceImpl.
	 */
	@Test
	public void testRegistrate() {
		User u = getUser();
		u.setPassword("password");
		userService.registrate(u);
		assertTrue(passwordService.check(u.getPassword(), "password"));
		assertNotNull(u.getId());
	}

	/**
	 * Test of login method, of class UserServiceImpl.
	 */
	@Test
	public void testLogin() {
		User u = getUser();
		String pw = u.getPassword();
		userService.registrate(u);
		User dbu = userService.login(u.getMail(), pw);
		assertNotNull(dbu);
		assertEquals(u, dbu);

		assertNull(userService.login("adsf", "asdfaadfd"));
		assertNull(userService.login(u.getMail(), u.getPassword()));
	}
}
