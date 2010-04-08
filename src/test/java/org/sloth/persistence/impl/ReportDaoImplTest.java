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
import org.sloth.exception.EntityAlreadyKnownException;
import org.sloth.exception.EntityNotKnownException;
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
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration
public class ReportDaoImplTest extends AbstractTransactionalJUnit4SpringContextTests {

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
	public void testGetReportById() {
		assertNull(reportDao.getById(Long.valueOf((long) Math.random() * 100)));
		User u = getUser();
		Categorie c = getCategorie();
		Observation o = getObservation(c, u);
		Report r = getReport(u, o);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		reportDao.save(r);
		assertNotNull(r.getId());
		assertNotNull(reportDao.getById(r.getId()));
		assertEquals(r, reportDao.getById(r.getId()));
	}

	@Test(expected = NullPointerException.class)
	public void testGetReportByNullId() {
		reportDao.getById(null);
	}

	@Test(expected = NullPointerException.class)
	public void testUpdateNullReport() {
		reportDao.update(null);
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void testUpdateNotKnownReport() {
		reportDao.save(getReport(getUser(), getObservation(getCategorie(), getUser())));
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void testUpdateNotKnownReportWithId() {
		reportDao.save(getReport(42L, getUser(42L), getObservation(getCategorie(42L), getUser(42L))));
	}

	@Test
	public void testUpdateReport() {
		User u = getUser();
		Categorie c = getCategorie();
		Observation o = getObservation(c, u);
		Report r = getReport(u, o);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		reportDao.save(r);
		r.setDescription("asdf");
		assertEquals("asdf", reportDao.getById(r.getId()).getDescription());
	}

	@Test(expected = EntityNotKnownException.class)
	public void testDeleteNotSavedReport() {
		reportDao.delete(getReport(getUser(), getObservation(getCategorie(), getUser())));
	}

	@Test(expected = EntityNotKnownException.class)
	public void testDeleteNotSavedReportWithId() {
		reportDao.delete(getReport(42L, getUser(42L), getObservation(getCategorie(42L), getUser(42L))));
	}

	@Test
	public void testDeleteReport() {
		User u = getUser();
		Categorie c = getCategorie();
		Observation o = getObservation(c, u);
		Report r = getReport(u, o);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		reportDao.save(r);
		reportDao.delete(r);
		assertNull(reportDao.getById(r.getId()));
	}

	@Test
	public void testDeleteReportList() {
		assertEquals(0, reportDao.getAll().size());
		User u = getUser();
		Categorie c = getCategorie();
		Observation o = getObservation(c, u);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		LinkedList<Report> l = new LinkedList<Report>();
		for (int i = 0; i < 10; i++) {
			Report r = getReport(u, o);
			l.add(r);

			reportDao.save(r);
		}
		assertEquals(10, reportDao.getAll().size());
		reportDao.delete(l);
		assertEquals(0, reportDao.getAll().size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteNotSavedReportList() {
		LinkedList<Report> l = new LinkedList<Report>();
		for (int i = 0; i < 10; i++) {
			l.add(getReport(getUser(), getObservation(getCategorie(), getUser())));
		}
		reportDao.delete(l);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteNotSavedReportListWithId() {
		LinkedList<Report> l = new LinkedList<Report>();
		for (int i = 0; i < 10; i++) {
			l.add(getReport(42L, getUser(42L), getObservation(getCategorie(42L), getUser(41L))));
		}
		reportDao.delete(l);
	}

	@Test(expected = NullPointerException.class)
	public void testDeleteNotSavedReportListWithNullValue() {
		LinkedList<Report> l = new LinkedList<Report>();
		l.add(null);
		reportDao.delete(l);
	}

	@Test(expected = NullPointerException.class)
	public void testSaveNullReport() {
		reportDao.save(null);
	}

	@Test
	public void testSaveAlreadyKnownCategorie() {
		User u = getUser();
		Categorie c = getCategorie();
		Observation o = getObservation(c, u);
		Report r = getReport(u, o);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		reportDao.save(r);
		try {
			reportDao.save(r);
		} catch (InvalidDataAccessApiUsageException e) {
			assertTrue(e.contains(EntityAlreadyKnownException.class));
			return;
		}
		fail();
	}

	@Test
	public void testSaveCategorie() {
		User u = getUser();
		Categorie c = getCategorie();
		Observation o = getObservation(c, u);
		Report r = getReport(u, o);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		reportDao.save(r);
		assertEquals(r, reportDao.getById(r.getId()));
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testSaveReportWithNullDescription() {
		User u = getUser();
		Categorie c = getCategorie();
		Observation o = getObservation(c, u);
		Report r = getReport(u, o);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		r.setDescription(null);
		reportDao.save(r);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testSaveReportWithNullUser() {
		User u = getUser();
		Categorie c = getCategorie();
		Observation o = getObservation(c, u);
		Report r = getReport(null, o);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		reportDao.save(r);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testSaveReportWithNullObservation() {
		User u = getUser();
		Report r = getReport(u, null);
		userDao.save(u);
		reportDao.save(r);
	}

	@Test(expected = JpaSystemException.class)
	public void testSaveReportWithNullCreationDate() {
		User u = getUser();
		Categorie c = getCategorie();
		Observation o = getObservation(c, u);
		Report r = getReport(u, o);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		r.setCreationDate(null);
		reportDao.save(r);
	}

	@Test(expected = JpaSystemException.class)
	public void testSaveReportWithNullLastUpdateDate() {
		User u = getUser();
		Categorie c = getCategorie();
		Observation o = getObservation(c, u);
		Report r = getReport(u, o);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		r.setLastUpdateDate(null);
		reportDao.save(r);
	}

	@Test(expected = FieldLengthConstraintViolationException.class)
	public void testSaveReportWithTooLongDescription() {
		User u = getUser();
		Categorie c = getCategorie();
		Observation o = getObservation(c, u);
		Report r = getReport(u, o);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 1001; i++) {
			buf.append("a");
		}
		r.setDescription(buf.toString());
		reportDao.save(r);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testUpdateReportWithNullDescription() {
		User u = getUser();
		Categorie c = getCategorie();
		Observation o = getObservation(c, u);
		Report r = getReport(u, o);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		reportDao.save(r);
		r.setDescription(null);
		reportDao.update(r);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testUpdateReportWithNullUser() {
		User u = getUser();
		Categorie c = getCategorie();
		Observation o = getObservation(c, u);
		Report r = getReport(u, o);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		reportDao.save(r);
		r.setAuthor(null);
		reportDao.update(r);
	}

	@Test(expected = NotNullConstraintViolationException.class)
	public void testUpdateReportWithNullObservation() {
		User u = getUser();
		Categorie c = getCategorie();
		Observation o = getObservation(c, u);
		Report r = getReport(u, o);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		reportDao.save(r);
		r.setObservation(null);
		reportDao.update(r);
	}

	@Test(expected = JpaSystemException.class)
	public void testUpdateReportWithNullCreationDate() {
		User u = getUser();
		Categorie c = getCategorie();
		Observation o = getObservation(c, u);
		Report r = getReport(u, o);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		reportDao.save(r);
		r.setCreationDate(null);
		reportDao.update(r);
	}

	@Test(expected = JpaSystemException.class)
	public void testUpdateReportWithNullLastUpdateDate() {
		User u = getUser();
		Categorie c = getCategorie();
		Observation o = getObservation(c, u);
		Report r = getReport(u, o);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		reportDao.save(r);
		r.setLastUpdateDate(null);
		reportDao.update(r);
	}

	@Test(expected = FieldLengthConstraintViolationException.class)
	public void testUpdateReportWithTooLongDescription() {
		User u = getUser();
		Categorie c = getCategorie();
		Observation o = getObservation(c, u);
		Report r = getReport(u, o);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		reportDao.save(r);
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 1001; i++) {
			buf.append("a");
		}
		r.setDescription(buf.toString());
		reportDao.update(r);
	}

	@Test
	public void testGetProcessed() {
		User u = getUser();
		Categorie c = getCategorie();
		Observation o = getObservation(c, u);
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		for (int i = 0; i < 10; i++) {
			Report r = getReport(u, o);
			r.setProcessed(false);
			reportDao.save(r);
		}
		for (int i = 0; i < 15; i++) {
			Report r = getReport(u, o);
			r.setProcessed(true);
			reportDao.save(r);
		}
		assertEquals(10, reportDao.getUnprocessed().size());
		assertEquals(15, reportDao.getProcessed().size());

	}
}

