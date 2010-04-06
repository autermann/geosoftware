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
package org.sloth.persistence.impl;

import java.util.Collection;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;
import org.sloth.exceptions.EntityAlreadyKnownException;
import org.sloth.exceptions.FieldLengthConstraintViolationException;
import org.sloth.exceptions.NotNullConstraintViolationException;
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
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration
public class DaoTest extends AbstractTransactionalJUnit4SpringContextTests {

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

	public void testGetAllObservations() {
		assertTrue(observationDao.getAll().size() == 0);
		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		assertTrue(categorieDao.getAll().size() == 1);
	}

	@Test
	public void testGetObservationById() {
		assertNull(observationDao.getById(Long.valueOf((long) Math.random() * 100)));
		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		assertNotNull(o.getId());
		assertNotNull(observationDao.getById(o.getId()));
		assertEquals(o, observationDao.getById(o.getId()));
	}

	@Test(expected = NullPointerException.class)
	public void testGetObservationByNullId() {
		observationDao.getById(null);
	}

	@Test(expected = NullPointerException.class)
	public void testUpdateNullObservation() {
		observationDao.update(null);
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void testUpdateNotSavedObservation() {
		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.update(o);
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void testUpdateNotSavedObservationWithId() {
		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(42L, c, u);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.update(o);
	}

	@Test
	public void testUpdateObservation() {
		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		assertNotNull(o.getId());
		o.setTitle("new Title");
		observationDao.update(o);
		assertEquals("new Title", observationDao.getById(o.getId()).getTitle());
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void testDeleteNotSavedObservation() {
		observationDao.delete(getObservation(getCategorie(42L), getUser(42L)));
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void testDeleteNotSavedObservationWithId() {
		observationDao.delete(getObservation(42L, getCategorie(42L), getUser(42L)));
	}

	@Test
	public void testDeleteObservation() {
		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		assertNotNull(o.getId());
		assertNotNull(observationDao.getById(o.getId()));
		observationDao.delete(o);
		assertNull(observationDao.getById(o.getId()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteNotSavedObservationList() {
		LinkedList<Observation> l = new LinkedList<Observation>();
		for (int i = 0; i < 10; i++) {
			l.add(getObservation(getCategorie(42L), getUser(42L)));
		}
		observationDao.delete(l);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteNotSavedObservationListWithId() {
		LinkedList<Observation> l = new LinkedList<Observation>();
		for (int i = 0; i < 10; i++) {
			l.add(getObservation((long) i, getCategorie(42L), getUser(42L)));
		}
		observationDao.delete(l);
	}

	@Test(expected = NullPointerException.class)
	public void testDeleteObservationListWithNullValue() {
		LinkedList<Observation> l = new LinkedList<Observation>();
		l.add(null);
		observationDao.delete(l);
	}

	@Test
	public void testDeleteObservationList() {
		LinkedList<Observation> l = new LinkedList<Observation>();
		User u = getUser();
		Categorie c = getCategorie();
		userDao.save(u);
		categorieDao.save(c);
		for (int i = 0; i < 10; i++) {
			l.add(getObservation(c, u));
		}
		for (Observation o : l) {
			observationDao.save(o);
		}
		observationDao.delete(l);
		for (Observation o : l) {
			assertNull(observationDao.getById(o.getId()));
		}
	}

	@Test(expected = NullPointerException.class)
	public void testSaveNullObservation() {
		observationDao.save(null);
	}

	@Test
	public void testSaveAlreadyKnownObservation() {
		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		categorieDao.save(c);
		userDao.save(u);
		observationDao.save(o);
		try {
			observationDao.save(o);
		} catch (InvalidDataAccessApiUsageException e) {
			assertTrue(e.contains(EntityAlreadyKnownException.class));
			return;
		}
		fail();
	}

	@Test
	public void testSaveObservation() {
		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		categorieDao.save(c);
		userDao.save(u);
		observationDao.save(o);
		assertNotNull(o.getId());
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testSaveObservationWithNullTitle() {
		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		categorieDao.save(c);
		userDao.save(u);
		o.setTitle(null);
		observationDao.save(o);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testSaveObservationWithNullDescription() {
		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		categorieDao.save(c);
		userDao.save(u);
		o.setDescription(null);
		observationDao.save(o);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testSaveObservationWithNullUser() {
		Categorie c = getCategorie();
		Observation o = getObservation(c, null);
		categorieDao.save(c);
		observationDao.save(o);
	}

	@Test(expected = JpaSystemException.class)
	public void testSaveObservationWithNullCategorie() {
		User u = getUser();
		Observation o = getObservation(null, u);
		userDao.save(u);
		observationDao.save(o);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testSaveObservationWithNullCoordinate() {
		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		categorieDao.save(c);
		userDao.save(u);
		o.setCoordinate(null);
		observationDao.save(o);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testSaveObservationWithNullCreationDate() {
		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		categorieDao.save(c);
		userDao.save(u);
		o.setCreationDate(null);
		observationDao.save(o);
	}

	@Test(expected = FieldLengthConstraintViolationException.class)
	public void testSaveObservationWithTooLongTitle() {
		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		categorieDao.save(c);
		userDao.save(u);
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 256; i++) {
			buf.append("a");
		}
		o.setTitle(buf.toString());
		observationDao.save(o);
	}

	@Test(expected = FieldLengthConstraintViolationException.class)
	public void testSaveObservationWithTooLongDescription() {
		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		categorieDao.save(c);
		userDao.save(u);
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 1001; i++) {
			buf.append("a");
		}
		o.setDescription(buf.toString());
		observationDao.save(o);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testUpdateObservationWithNullTitle() {
		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		categorieDao.save(c);
		userDao.save(u);
		observationDao.save(o);
		o.setTitle(null);
		observationDao.update(o);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testUpdateObservationWithNullDescription() {
		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		categorieDao.save(c);
		userDao.save(u);
		observationDao.save(o);
		o.setDescription(null);
		observationDao.update(o);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testUpdateObservationWithNullUser() {
		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		categorieDao.save(c);
		userDao.save(u);
		observationDao.save(o);
		o.setUser(null);
		observationDao.update(o);
	}

	@Test(expected = JpaSystemException.class)
	public void testUpdateObservationWithNullCategorie() {
		User u = getUser();
		Categorie c = getCategorie();
		Observation o = getObservation(c, u);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		o.setCategorie(null);
		observationDao.update(o);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testUpdateObservationWithNullCoordinate() {
		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		categorieDao.save(c);
		userDao.save(u);
		observationDao.save(o);
		o.setCoordinate(null);
		observationDao.update(o);

	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testUpdateObservationWithNullCreationDate() {
		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		categorieDao.save(c);
		userDao.save(u);
		observationDao.save(o);
		o.setCreationDate(null);
		observationDao.update(o);
	}

	@Test(expected = FieldLengthConstraintViolationException.class)
	public void testUpdateObservationWithTooLongTitle() {
		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		categorieDao.save(c);
		userDao.save(u);
		observationDao.save(o);
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 256; i++) {
			buf.append("a");
		}
		o.setTitle(buf.toString());
		observationDao.update(o);
	}

	@Test(expected = FieldLengthConstraintViolationException.class)
	public void testUpdateObservationWithTooLongDescription() {
		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		categorieDao.save(c);
		userDao.save(u);
		observationDao.save(o);
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 1001; i++) {
			buf.append("a");
		}
		o.setDescription(buf.toString());
		observationDao.update(o);
	}

	@Test
	public void testDeleteObservationCascade() {
		Categorie c = getCategorie();
		categorieDao.save(c);
		User u = getUser();
		userDao.save(u);
		Observation o = getObservation(c, u);
		observationDao.save(o);
		Report r = getReport(u, o);
		reportDao.save(r);
		observationDao.delete(o);
		assertNull(reportDao.getById(r.getId()));
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
