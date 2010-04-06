/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sloth.persistence.impl;

import java.util.Collection;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;
import org.sloth.exceptions.EntityAlreadyKnownException;
import org.sloth.exceptions.EntityNotKnownException;
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
public class ObservationDaoImplTest extends AbstractTransactionalJUnit4SpringContextTests {

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

	@Test(expected = NullPointerException.class)
	public void testGetObservationByNullUser() {
		observationDao.getByUser(null);
	}

	@Test(expected = EntityNotKnownException.class)
	public void testGetObservationByNotKnownUser() {
		observationDao.getByUser(getUser());
	}

	@Test
	public void testGetObservationByUser() {
		User u1 = getUser();
		User u2 = getUser();
		Categorie c = getCategorie();
		Observation o1 = getObservation(c, u1);
		Observation o2 = getObservation(c, u2);
		categorieDao.save(c);
		userDao.save(u1);
		userDao.save(u2);
		observationDao.save(o1);
		observationDao.save(o2);
		assertEquals(o1, observationDao.getByUser(u1).iterator().next());
		assertEquals(o2, observationDao.getByUser(u2).iterator().next());
	}

	@Test(expected = NullPointerException.class)
	public void testGetObservationByNullCategorie() {
		observationDao.getByCategorie(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetObservationByNotKnownCategorie() {
		observationDao.getByCategorie(getCategorie());
	}

	@Test
	public void testGetObservationByCategorie() {
		User u = getUser();
		Categorie c1 = getCategorie();
		Categorie c2 = getCategorie();
		Observation o1 = getObservation(c1, u);
		Observation o2 = getObservation(c2, u);
		categorieDao.save(c1);
		categorieDao.save(c2);
		userDao.save(u);
		observationDao.save(o1);
		observationDao.save(o2);
		assertEquals(o1, observationDao.getByCategorie(c1).iterator().next());
		assertEquals(o2, observationDao.getByCategorie(c2).iterator().next());
	}

	@Test
	public void testGetNewestObservations() {
		User u = getUser();
		userDao.save(u);
		Categorie c = getCategorie();
		categorieDao.save(c);
		for (int i = 0; i < 20; i++) {
			observationDao.save(getObservation(c, u));
		}
		assertEquals(20, observationDao.getAll().size());
		assertEquals(0, observationDao.getNewestObservations(-1).size());
		assertEquals(10, observationDao.getNewestObservations(10).size());
		assertEquals(20, observationDao.getNewestObservations(30).size());
		assertEquals(0, observationDao.getNewestObservations(0).size());
		assertEquals(1, observationDao.getNewestObservations(1).size());
	}

	@Test(expected = NullPointerException.class)
	public void testGetObservationsByNullKeyword() {
		observationDao.getByKeyWord(null);
	}

	@Test
	public void testGetObservationByKeyword() {
		String keyword = "ZdbnksaiI1";
		String keyword2 = "2342134fgsdadc" + keyword + "asgdh";
		Categorie c1 = getCategorie();
		c1.setTitle(keyword2);
		Categorie c2 = getCategorie();
		c2.setDescription(keyword2);
		Categorie c3 = getCategorie();
		User u = getUser();
		Observation o1 = getObservation(c1, u);
		Observation o2 = getObservation(c2, u);
		Observation o3 = getObservation(c3, u);
		o3.setTitle(keyword2);
		Observation o4 = getObservation(c3, u);
		o4.setDescription(keyword2);
		Observation o5 = getObservation(c3, u);
		userDao.save(u);
		categorieDao.save(c1);
		categorieDao.save(c2);
		categorieDao.save(c3);
		observationDao.save(o1);
		observationDao.save(o2);
		observationDao.save(o3);
		observationDao.save(o4);
		observationDao.save(o5);
		Collection<Observation> list = observationDao.getByKeyWord(keyword);
		assertTrue(list.contains(o1)); // o1.categorie.title
		assertTrue(list.contains(o2)); // o2.categorie.description
		assertTrue(list.contains(o3)); // o3.title
		assertTrue(list.contains(o4)); // o3.description
		assertTrue(!list.contains(o5)); // none
	}
}
