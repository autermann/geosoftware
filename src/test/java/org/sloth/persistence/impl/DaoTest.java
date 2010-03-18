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
import org.sloth.model.User;
import org.sloth.persistence.CategorieDao;
import org.sloth.persistence.ObservationDao;
import org.sloth.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.orm.jpa.JpaTransactionManager;
import static org.junit.Assert.*;
import static org.sloth.model.Group.*;
import static org.sloth.service.impl.EntityFactory.*;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
public class DaoTest extends org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests {

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
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
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
		deleteFromTables("USER", "CATEGORIE", "OBSERVATION");
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

	@Test
	public void userAdd() {
		long count = countRowsInTable("USER");
		assertEquals(userDao.getAll().size(), count);
		User u = getUser(USER);
		assertTrue(u.isNew());
		userDao.save(u);
		assertEquals(count + 1, countRowsInTable("USER"));
		assertEquals(userDao.getAll().size(), count + 1);
		assertTrue(!u.isNew());
	}

	/**
	 * Will fail if executed on HSQLDB...
	 * known bug in EclipseLink/HSQLDB...
	 */
	@Test
	public void duplicateMailAddress() {
		/* ugly workaround to get in another transaction... */
		this.simpleJdbcTemplate.update("INSERT INTO USER (ID, MAIL_ADDRESS, "
				+ "CREATION_DATE, NAME, USER_GROUP, FAMILY_NAME, PASSWORD) VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?)", 999L, "default@example.tld", new Date(),
				"TEST", USER.toString(), "TEST", "password");
		User u = getUser(USER);
		u.setMail("default@example.tld");
		try {
			userDao.save(u);
		} catch (JpaSystemException e) {
			assertTrue(e.contains(MySQLIntegrityConstraintViolationException.class));
			return;
		}
		fail("no exception");
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteNotPersistedUser() {
		userDao.delete(getUser(WFS));
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateNotPersistedUser() {
		userDao.update(getUser(WFS));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteNotPersistedCategorie() {
		categorieDao.delete(getCategorie());
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateNotPersistedCategorie() {
		categorieDao.update(getCategorie());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteNotPersistedObservation() {
		observationDao.delete(getObservation(getCategorie(), getUser(WFS)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateNotPersistedObservation() {
		observationDao.update(getObservation(getCategorie(), getUser(WFS)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteNotPersistedUserWithId() {
		userDao.delete(getUser(42L, WFS));
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateNotPersistedUserWithId() {
		userDao.update(getUser(42L, WFS));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteNotPersistedCategorieWithId() {
		categorieDao.delete(getCategorie(42L));
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateNotPersistedCategorieWithId() {
		categorieDao.update(getCategorie(42L));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteNotPersistedObservationWithId() {
		observationDao.delete(getObservation(getCategorie(42L), getUser(WFS)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateNotPersistedObservationWithId() {
		observationDao.update(getObservation(getCategorie(42L), getUser(WFS)));
	}
}
