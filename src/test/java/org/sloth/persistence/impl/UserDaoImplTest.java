/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sloth.persistence.impl;

import java.util.Collection;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;
import org.sloth.exception.EntityAlreadyKnownException;
import org.sloth.exception.FieldLengthConstraintViolationException;
import org.sloth.exception.NotNullConstraintViolationException;
import static org.junit.Assert.*;
import org.sloth.model.Categorie;
import org.sloth.model.Observation;
import org.sloth.model.Report;
import org.sloth.model.User;
import org.sloth.persistence.CategorieDao;
import org.sloth.persistence.ObservationDao;
import org.sloth.persistence.ReportDao;
import org.sloth.persistence.UserDao;
import static org.sloth.EntityFactory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration
public class UserDaoImplTest extends AbstractTransactionalJUnit4SpringContextTests {

	private UserDao userDao = null;
	private CategorieDao categorieDao = null;
	private ReportDao reportDao = null;
	private ObservationDao observationDao = null;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setCategorieDao(CategorieDao categorieDao) {
		this.categorieDao = categorieDao;
	}

	@Autowired
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

	@Autowired
	public void setObservationDao(ObservationDao observationDao) {
		this.observationDao = observationDao;
	}

	@Before
	public void before() {
		this.deleteFromTables("USERS_TEST", "CATEGORIES_TEST", "REPORTS_TEST", "OBSERVATIONS_TEST");
	}

	@Test
	public void selfTest() {
		assertNotNull(applicationContext);
		assertNotNull(userDao);
		assertNotNull(categorieDao);
		assertNotNull(observationDao);
		assertNotNull(reportDao);
	}

	@Test
	public void testGetAllUsers() {
		assertTrue(userDao.getAll().size() == 0);
		for (int i = 1; i <= 10; i++) {
			userDao.save(getUser());
			assertTrue(userDao.getAll().size() == i);
		}
	}

	@Test
	public void testGetUserById() {
		assertNull(userDao.getById(Long.valueOf((long) Math.random() * 100)));
		User c = getUser();
		userDao.save(c);
		assertNotNull(c.getId());
		assertEquals(c, userDao.getById(c.getId()));
	}

	@Test(expected = NullPointerException.class)
	public void testGetUserByNullId() {
		userDao.getById(null);
	}

	@Test(expected = NullPointerException.class)
	public void testGetUserByNullMail() {
		userDao.getByMail(null);
	}

	@Test
	public void testGetUserByMail() {
		User u = getUser();
		userDao.save(u);
		assertEquals(u, userDao.getByMail(u.getMail()));
	}

	@Test(expected = NullPointerException.class)
	public void testUpdateNullUser() {
		userDao.update(null);
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void testUpdateNotSavedUser() {
		userDao.update(getUser());
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void testUpdateNotSavedUserWithId() {
		userDao.update(getUser(42L));
	}

	@Test
	public void testUpdateUser() {
		User u = getUser();
		userDao.save(u);
		String name = "asdf";
		u.setName(name);
		userDao.update(u);
		assertEquals(name, userDao.getById(u.getId()).getName());
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testUpdateUserWithNullCreationDate() {
		User u = getUser();
		userDao.save(u);
		u.setCreationDate(null);
		userDao.update(u);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testUpdateUserWithNullFamilyName() {
		User u = getUser();
		userDao.save(u);
		u.setFamilyName(null);
		userDao.update(u);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testUpdateUserWithNullName() {
		User u = getUser();
		userDao.save(u);
		u.setName(null);
		userDao.update(u);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testUpdateUserWithNullMail() {
		User u = getUser();
		userDao.save(u);
		u.setMail(null);
		userDao.update(u);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testUpdateUserWithNullPassword() {
		User u = getUser();
		userDao.save(u);
		u.setPassword(null);
		userDao.update(u);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testUpdateUserWithNullGroup() {
		User u = getUser();
		userDao.save(u);
		u.setUserGroup(null);
		userDao.update(u);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testSaveUserWithNullCreationDate() {
		User u = getUser();
		u.setCreationDate(null);
		userDao.save(u);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testSaveUserWithNullFamilyName() {
		User u = getUser();
		u.setFamilyName(null);
		userDao.save(u);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testSaveUserWithNullName() {
		User u = getUser();
		u.setName(null);
		userDao.save(u);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testSaveUserWithNullMail() {
		User u = getUser();
		u.setMail(null);
		userDao.save(u);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testSaveUserWithNullPassword() {
		User u = getUser();
		u.setPassword(null);
		userDao.save(u);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testSaveUserWithNullGroup() {
		User u = getUser();
		u.setUserGroup(null);
		userDao.save(u);
	}

	@Test(expected = FieldLengthConstraintViolationException.class)
	public void testUpdateUserWithTooLongMailAddress() {
		User u = getUser();
		userDao.save(u);
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 256; i++) {
			buf.append("a");
		}
		u.setMail(buf.toString());
		userDao.update(u);
	}

	@Test(expected = FieldLengthConstraintViolationException.class)
	public void testUpdateUserWithTooLongName() {
		User u = getUser();
		userDao.save(u);
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 256; i++) {
			buf.append("a");
		}
		u.setName(buf.toString());
		userDao.update(u);
	}

	@Test(expected = FieldLengthConstraintViolationException.class)
	public void testUpdateUserWithTooLongFamilyName() {
		User u = getUser();
		userDao.save(u);
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 256; i++) {
			buf.append("a");
		}
		u.setFamilyName(buf.toString());
		userDao.update(u);
	}

	@Test(expected = FieldLengthConstraintViolationException.class)
	public void testUpdateUserWithTooLongPassword() {
		User u = getUser();
		userDao.save(u);
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 256; i++) {
			buf.append("a");
		}
		u.setPassword(buf.toString());
		userDao.update(u);
	}

	@Test(expected = FieldLengthConstraintViolationException.class)
	public void testSaveUserWithTooLongMailAddress() {
		User u = getUser();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 256; i++) {
			buf.append("a");
		}
		u.setMail(buf.toString());
		userDao.save(u);
	}

	@Test(expected = FieldLengthConstraintViolationException.class)
	public void testSaveUserWithTooLongName() {
		User u = getUser();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 256; i++) {
			buf.append("a");
		}
		u.setName(buf.toString());
		userDao.save(u);
	}

	@Test(expected = FieldLengthConstraintViolationException.class)
	public void testSaveUserWithTooLongFamilyName() {
		User u = getUser();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 256; i++) {
			buf.append("a");
		}
		u.setFamilyName(buf.toString());
		userDao.save(u);
	}

	@Test(expected = FieldLengthConstraintViolationException.class)
	public void testSaveUserWithTooLongPassword() {
		User u = getUser();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 256; i++) {
			buf.append("a");
		}
		u.setPassword(buf.toString());
		userDao.save(u);
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void testDeleteNotKnownUserUser() {
		userDao.delete(getUser());
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void testDeleteNotKnownUserUserWithId() {
		userDao.delete(getUser(42L));
	}

	@Test
	public void testDeleteUser() {
		User u = getUser();
		userDao.save(u);
		assertNotNull(userDao.getById(u.getId()));
		userDao.delete(u);
		assertNull(userDao.getById(u.getId()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteNotSavedUserList() {
		LinkedList<User> l = new LinkedList<User>();
		for (int i = 0; i < 10; i++) {
			l.add(getUser());
		}
		userDao.delete(l);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteNotSavedUserListWithId() {
		LinkedList<User> l = new LinkedList<User>();
		for (int i = 0; i < 10; i++) {
			l.add(getUser(42L));
		}
		userDao.delete(l);
	}

	@Test(expected = NullPointerException.class)
	public void testDeleteNotSavedUserListWithNullValue() {
		LinkedList<User> l = new LinkedList<User>();
		l.add(null);
		userDao.delete(l);
	}

	@Test
	public void testDeleteUserList() {
		for (int i = 0; i < 10; i++) {
			userDao.save(getUser());
		}
		Collection<User> all = userDao.getAll();
		assertEquals(10, all.size());
		userDao.delete(all);
		assertEquals(0, userDao.getAll().size());
	}

	@Test(expected = NullPointerException.class)
	public void testSaveNullUser() {
		userDao.save(null);
	}

	@Test
	public void testSaveAlreadyKnownUser() {
		User u = getUser();
		userDao.save(u);
		try {
			userDao.save(u);
		} catch (InvalidDataAccessApiUsageException e) {
			assertTrue(e.contains(EntityAlreadyKnownException.class));
			return;
		}
		fail();
	}

	@Test
	public void testSaveUser() {
		User u = getUser();
		userDao.save(u);
		assertNotNull(u.getId());
		assertEquals(u, userDao.getById(u.getId()));
	}

	@Test
	public void testDeleteUserCascade() {
		Categorie c = getCategorie();
		categorieDao.save(c);
		User u = getUser();
		userDao.save(u);
		Observation o = getObservation(c, u);
		observationDao.save(o);
		Report r = getReport(u, o);
		reportDao.save(r);
		userDao.delete(u);
		assertNull(reportDao.getById(r.getId()));
		assertNull(observationDao.getById(o.getId()));
	}
}
