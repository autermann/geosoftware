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

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.junit.Before;
import org.junit.Test;
import org.sloth.exceptions.EntityAlreadyKnownException;
import org.sloth.exceptions.FieldLengthConstraintViolationException;
import static org.junit.Assert.*;
import org.sloth.model.Categorie;
import org.sloth.model.Observation;
import org.sloth.model.User;
import org.sloth.model.Group;
import org.sloth.persistence.CategorieDao;
import org.sloth.persistence.ObservationDao;
import org.sloth.persistence.UserDao;
import static org.sloth.test.EntityFactory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration
public class DaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	private JpaTransactionManager transactionManager = null;
	private EntityManagerFactory entityManagerFactory = null;
	private UserDao userDao = null;
	private CategorieDao categorieDao = null;
	private ObservationDao observationDao = null;
	private EntityManager entityManager = null;
	private EntityTransaction entityTransaction = null;

	@Autowired
	public void setTransactionManager(JpaTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	@Autowired
	public void setEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

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
	public void setUp() {
		deleteFromTables("USERS_TEST", "CATEGORIES_TEST", "OBSERVATIONS_TEST");
	}

	public void tearDown() {
		deleteFromTables("USERS_TEST", "CATEGORIES_TEST", "OBSERVATIONS_TEST");
	}

	private void testRows() {
		assertEquals(userDao.getAll().size(),
					 countRowsInTable("USERS_TEST"));
		assertEquals(categorieDao.getAll().size(),
					 countRowsInTable("CATEGORIES_TEST"));
		assertEquals(observationDao.getAll().size(),
					 countRowsInTable("OBSERVATIONS_TEST"));
	}

	@Test
	public void selfTest() {
		assertNotNull(applicationContext);
		assertNotNull(userDao);
		assertNotNull(categorieDao);
		assertNotNull(observationDao);
		assertNotNull(transactionManager);
		assertNotNull(entityManagerFactory);
	}

	/**
	 * Will fail if executed on HSQLDB...
	 * known bug in EclipseLink/HSQLDB...
	 */
	@Test
	public void duplicateMailAddress() {
		/* ugly workaround to getById in another transaction... */
		this.simpleJdbcTemplate.update(
				"INSERT INTO USERS_TEST (ID, MAIL_ADDRESS, CREATION_DATE, NAME, USER_GROUP, FAMILY_NAME, PASSWORD)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)", 999L, "default@example.tld",
				new Date(), "TEST", Group.USER.toString(), "TEST", "password");
		User u = getUser();
		u.setMail("default@example.tld");
		try {
			userDao.save(u);
		} catch(JpaSystemException e) {
			assertTrue(e.contains(
					MySQLIntegrityConstraintViolationException.class));
			return;
		}
		fail("no exception");
	}

	@Test
	public void saveUser() {
		testRows();
		User u = getUser();
		assertTrue(u.isNew());
		userDao.save(u);
		assertTrue(!u.isNew());
		testRows();
		assertEquals(u, userDao.getById(u.getId()));
	}

	@Test
	public void saveCategorie() {
		testRows();
		Categorie c = getCategorie();
		assertTrue(c.isNew());
		categorieDao.save(c);
		assertTrue(!c.isNew());
		testRows();
		assertEquals(c, categorieDao.getById(c.getId()));
	}

	@Test
	public void saveObservation() {
		testRows();
		User u = getUser();
		Categorie c = getCategorie();
		Observation o = getObservation(c, u);
		assertTrue(u.isNew());
		assertTrue(c.isNew());
		assertTrue(o.isNew());
		userDao.save(u);
		categorieDao.save(c);
		observationDao.save(o);
		assertTrue(!u.isNew());
		assertTrue(!c.isNew());
		assertTrue(!o.isNew());
		testRows();
		assertEquals(o, observationDao.getById(o.getId()));
	}

	/*
	 * IllegalArgumentException Tests - Not Known Objects - UserDao
	 */
	@Test(expected = IllegalArgumentException.class)
	public void deleteNotPersistedUser() {
		userDao.delete(getUser());
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateNotPersistedUser() {
		userDao.update(getUser());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteNotPersistedUserWithId() {
		userDao.delete(getUser(42L));
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateNotPersistedUserWithId() {
		userDao.update(getUser(42L));
	}

	/*
	 * IllegalArgumentException Tests - Not Known Objects - CategorieDao
	 */
	@Test(expected = IllegalArgumentException.class)
	public void deleteNotPersistedCategorie() {
		categorieDao.delete(getCategorie());
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateNotPersistedCategorie() {
		categorieDao.update(getCategorie());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteNotPersistedCategorieWithId() {
		categorieDao.delete(getCategorie(42L));
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateNotPersistedCategorieWithId() {
		categorieDao.update(getCategorie(42L));
	}

	/*
	 * IllegalArgumentException Tests - Not Known Objects - ObservationDao
	 */
	@Test(expected = IllegalArgumentException.class)
	public void deleteNotPersistedObservation() {
		observationDao.delete(getObservation(getCategorie(), getUser()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateNotPersistedObservation() {
		observationDao.update(getObservation(getCategorie(), getUser()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteNotPersistedObservationWithId() {
		observationDao.delete(getObservation(getCategorie(42L), getUser()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateNotPersistedObservationWithId() {
		observationDao.update(getObservation(getCategorie(42L), getUser()));
	}

	/*
	 * NullPointerException Tests - ObservationDao
	 */
	@Test(expected = NullPointerException.class)
	public void updateNullObservation() {
		observationDao.update(null);
	}

	@Test(expected = NullPointerException.class)
	public void saveNullObservation() {
		observationDao.save(null);
	}

	@Test(expected = NullPointerException.class)
	public void deleteNullObservation() {
		observationDao.delete(null);
	}

	@Test(expected = NullPointerException.class)
	public void getObservationByNullCategorie() {
		observationDao.getByCategorie(null);
	}

	@Test(expected = NullPointerException.class)
	public void getObservationByNullUser() {
		observationDao.getByUser(null);
	}

	@Test(expected = NullPointerException.class)
	public void getObservationByNullId() {
		observationDao.getById(null);
	}

	/*
	 * NullPointerException Tests - CategorieDao
	 */
	@Test(expected = NullPointerException.class)
	public void updateNullCategorie() {
		categorieDao.update(null);
	}

	@Test(expected = NullPointerException.class)
	public void saveNullCategorie() {
		categorieDao.save(null);
	}

	@Test(expected = NullPointerException.class)
	public void deleteNullCategorie() {
		categorieDao.delete(null);
	}

	@Test(expected = NullPointerException.class)
	public void getCategorieByNullTitle() {
		categorieDao.getByTitle(null);
	}

	@Test(expected = NullPointerException.class)
	public void getCategorieByNullId() {
		categorieDao.getById(null);
	}

	/*
	 * NullPointerException Tests - UserDao
	 */
	@Test(expected = NullPointerException.class)
	public void updateNullUser() {
		userDao.update(null);
	}

	@Test(expected = NullPointerException.class)
	public void saveNullUser() {
		userDao.save(null);
	}

	@Test(expected = NullPointerException.class)
	public void deleteNullUser() {
		userDao.delete(null);
	}

	@Test(expected = NullPointerException.class)
	public void getUserByNullMail() {
		userDao.getByMail(null);
	}

	@Test(expected = NullPointerException.class)
	public void getUserByNullId() {
		userDao.getById(null);
	}

	/*
	 * IllegalArgumentException Tests - Saving Already Known Objects
	 */
	@Test
	public void saveAlreadyPersistedObservation() {
		User u = getUser();
		Categorie c = getCategorie();
		Observation o = getObservation(c, u);
		categorieDao.save(c);
		userDao.save(u);
		observationDao.save(o);
		try {
			observationDao.save(o);
		} catch(InvalidDataAccessApiUsageException e) {
			assertTrue(e.contains(EntityAlreadyKnownException.class));
			return;
		}
		fail();
	}

	@Test
	public void saveAlreadyPersistedUser() {
		User u = getUser();
		userDao.save(u);
		try {
			userDao.save(u);
		} catch(InvalidDataAccessApiUsageException e) {
			assertTrue(e.contains(EntityAlreadyKnownException.class));
			return;
		}
		fail();
	}

	@Test
	public void saveAlreadyPersistedEntity() {
		Categorie c = getCategorie();
		categorieDao.save(c);
		try {
			categorieDao.save(c);
		} catch(InvalidDataAccessApiUsageException e) {
			assertTrue(e.contains(EntityAlreadyKnownException.class));
			return;
		}
		fail();
	}

	/*
	 * ConstraintViolationException Tests - User
	 */
	@Test
	public void tooLongName() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 255; i++) {
			buf.append("a");
		}
		assertEquals(buf.toString().length(), 255);
		User uNormal = getUser();
		uNormal.setName(buf.toString());
		User uAbNormal = getUser();
		uAbNormal.setName(buf.toString() + "a");
		userDao.save(uNormal);
		try {
			userDao.save(uAbNormal);
		} catch(FieldLengthConstraintViolationException e) {
			return;
		}
		fail();
	}


	@Test
	public void getCategorieByTitle(){
		Categorie c = getCategorie();
		categorieDao.save(c);
		assertEquals(c, categorieDao.getByTitle(c.getTitle()));
	}

}

