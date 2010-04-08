/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sloth.persistence.impl;

import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;
import org.sloth.exception.EntityAlreadyKnownException;
import org.sloth.exception.FieldLengthConstraintViolationException;
import org.sloth.exception.NotNullConstraintViolationException;
import static org.junit.Assert.*;
import org.sloth.model.Categorie;
import org.sloth.model.Observation;
import org.sloth.model.User;
import org.sloth.persistence.CategorieDao;
import org.sloth.persistence.ObservationDao;
import org.sloth.persistence.UserDao;
import static org.sloth.EntityFactory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration
public class CategorieDaoImplTest extends AbstractTransactionalJUnit4SpringContextTests {

	private UserDao userDao = null;
	private CategorieDao categorieDao = null;
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
	}

	@Test
	public void testGetAllCategories() {
		assertTrue(categorieDao.getAll().size() == 0);
		categorieDao.save(getCategorie());
		assertTrue(categorieDao.getAll().size() == 1);
	}

	@Test
	public void testGetCategorieById() {
		assertNull(categorieDao.getById(Long.valueOf((long) Math.random() * 100)));
		Categorie c = getCategorie();
		categorieDao.save(c);
		assertNotNull(c.getId());
		assertNotNull(categorieDao.getById(c.getId()));
		assertEquals(c, categorieDao.getById(c.getId()));
	}

	@Test(expected = NullPointerException.class)
	public void testGetCategorieByNullId() {
		categorieDao.getById(null);
	}

	@Test(expected = NullPointerException.class)
	public void testUpdateNullCategorie() {
		categorieDao.update(null);
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void testUpdateNotSavedCategorie() {
		categorieDao.update(getCategorie());
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void testUpdateNotSavedCategorieWithId() {
		categorieDao.update(getCategorie(213L));
	}

	@Test
	public void testUpdateCategorie() {
		Categorie c = getCategorie();
		categorieDao.save(c);
		assertNotNull(c.getId());
		c.setTitle("new Title");
		categorieDao.update(c);
		assertEquals("new Title", categorieDao.getById(c.getId()).getTitle());
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void testDeleteNotSavedCategorie() {
		categorieDao.delete(getCategorie());
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void testDeleteNotSavedCategorieWithId() {
		categorieDao.delete(getCategorie(213L));
	}

	@Test
	public void testDeleteCategorie() {
		Categorie c = getCategorie();
		categorieDao.save(c);
		assertNotNull(c.getId());
		assertNotNull(categorieDao.getById(c.getId()));
		categorieDao.delete(c);
		assertNull(categorieDao.getById(c.getId()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteNotSavedCategorieList() {
		LinkedList<Categorie> l = new LinkedList<Categorie>();
		for (int i = 0; i < 10; i++) {
			l.add(getCategorie());
		}
		categorieDao.delete(l);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteNotSavedCategorieListWithId() {
		LinkedList<Categorie> l = new LinkedList<Categorie>();
		for (int i = 0; i < 10; i++) {
			l.add(getCategorie((long) i));
		}
		categorieDao.delete(l);
	}

	@Test(expected = NullPointerException.class)
	public void testDeleteCategorieListWithNullValue() {
		LinkedList<Categorie> l = new LinkedList<Categorie>();
		l.add(null);
		categorieDao.delete(l);
	}

	@Test
	public void testDeleteCategorieList() {
		LinkedList<Categorie> l = new LinkedList<Categorie>();
		for (int i = 0; i < 10; i++) {
			l.add(getCategorie());
		}
		for (Categorie c : l) {
			categorieDao.save(c);
		}
		categorieDao.delete(l);
		for (Categorie c : l) {
			assertNull(categorieDao.getById(c.getId()));
		}
	}

	@Test(expected = NullPointerException.class)
	public void testSaveNullCategorie() {
		categorieDao.save(null);
	}

	@Test
	public void testSaveAlreadyKnownCategorie() {
		Categorie c = getCategorie();
		categorieDao.save(c);
		try {
			categorieDao.save(c);
		} catch (InvalidDataAccessApiUsageException e) {
			assertTrue(e.contains(EntityAlreadyKnownException.class));
			return;
		}
		fail();
	}

	@Test
	public void testSaveCategorie() {
		Categorie c = getCategorie();
		categorieDao.save(c);
		assertNotNull(c.getId());
		assertEquals(c, categorieDao.getById(c.getId()));
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testSaveCategoreWithNullTitle() {
		Categorie c = getCategorie();
		c.setTitle(null);
		categorieDao.save(c);
	}

	@Test(expected = FieldLengthConstraintViolationException.class)
	public void testSaveCategoreWithTooLongTitle() {
		Categorie c = getCategorie();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 256; i++) {
			buf.append("a");
		}
		c.setTitle(buf.toString());
		categorieDao.save(c);
	}

	@Test(expected = FieldLengthConstraintViolationException.class)
	public void testSaveCategoreWithTooLongIconFileName() {
		Categorie c = getCategorie();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 256; i++) {
			buf.append("a");
		}
		c.setIconFileName(buf.toString());
		categorieDao.save(c);
	}

	@Test(expected = FieldLengthConstraintViolationException.class)
	public void testSaveCategoreWithTooLongDescription() {
		Categorie c = getCategorie();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 1001; i++) {
			buf.append("a");
		}
		c.setIconFileName(buf.toString());
		categorieDao.save(c);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testSaveCategoreWithNullDescription() {
		Categorie c = getCategorie();
		c.setDescription(null);
		categorieDao.save(c);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testSaveCategoreWithNullIconFileName() {
		Categorie c = getCategorie();
		c.setIconFileName(null);
		categorieDao.save(c);
	}

	@Test(expected = JpaSystemException.class)
	public void testSaveCategoreWithAlreadySavedTitle() {
		Categorie c1 = getCategorie();
		Categorie c2 = getCategorie();
		c1.setTitle(c2.getTitle());
		categorieDao.save(c1);
		categorieDao.save(c2);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testUpdateCategoreWithNullTitle() {
		Categorie c = getCategorie();
		categorieDao.save(c);
		c.setTitle(null);
		categorieDao.update(c);
	}

	@Test(expected = FieldLengthConstraintViolationException.class)
	public void testUpdateCategoreWithTooLongTitle() {
		Categorie c = getCategorie();
		categorieDao.save(c);
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 256; i++) {
			buf.append("a");
		}
		c.setTitle(buf.toString());
		categorieDao.update(c);
	}

	@Test(expected = FieldLengthConstraintViolationException.class)
	public void testUpdateCategoreWithTooLongIconFileName() {
		Categorie c = getCategorie();
		categorieDao.save(c);
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 256; i++) {
			buf.append("a");
		}
		c.setIconFileName(buf.toString());
		categorieDao.update(c);
	}

	@Test(expected = FieldLengthConstraintViolationException.class)
	public void testUpdateCategoreWithTooLongDescription() {
		Categorie c = getCategorie();
		categorieDao.save(c);
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 1001; i++) {
			buf.append("a");
		}
		c.setIconFileName(buf.toString());
		categorieDao.update(c);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testUpdateCategoreWithNullDescription() {
		Categorie c = getCategorie();
		categorieDao.save(c);
		c.setDescription(null);
		categorieDao.update(c);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testUpdateCategoreWithNullIconFileName() {
		Categorie c = getCategorie();
		categorieDao.save(c);
		c.setIconFileName(null);
		categorieDao.update(c);
	}

	@Test(expected = JpaSystemException.class)
	public void testUpdateCategoreWithAlreadySavedTitle() {
		Categorie c1 = getCategorie();
		Categorie c2 = getCategorie();
		categorieDao.save(c1);
		categorieDao.save(c2);
		c1.setTitle(c2.getTitle());
		categorieDao.update(c1);

	}

	@Test(expected = NullPointerException.class)
	public void testGetCategorieByNullTitle() {
		categorieDao.getByTitle(null);
	}

	@Test
	public void testGetCategorieByNotKnownTitle() {
		assertNull(categorieDao.getByTitle("asdf"));
	}

	@Test
	public void testGetCategorieByTitle() {
		Categorie c = getCategorie();
		categorieDao.save(c);
		assertNotNull(categorieDao.getByTitle(c.getTitle()));
	}

	@Test
	public void testDeleteCategorieCascade() {
		User u = getUser();
		Categorie c = getCategorie();
		Observation o = getObservation(c, u);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		categorieDao.delete(c);
		assertNull(categorieDao.getById(c.getId()));
		assertNull(observationDao.getById(o.getId()));
		assertNotNull(userDao.getById(u.getId()));
	}
}
