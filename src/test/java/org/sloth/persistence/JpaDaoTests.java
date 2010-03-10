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
package org.sloth.persistence;

import org.junit.*;
import org.sloth.model.Coordinate;
import org.sloth.model.Observation;
import org.sloth.model.ObservationCategorie;
import org.sloth.model.User;
import org.sloth.model.UserRight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import static org.junit.Assert.*;

@ContextConfiguration(locations = "JpaDaoTests-context.xml")
public class JpaDaoTests extends AbstractTransactionalJUnit4SpringContextTests {

	@Test
	public void selfTesting() {
		assertNotNull("ObservationDao is null", getObservationDao());
		assertNotNull("ObservationCategorieDao is null", getObservationCategorieDao());
		assertNotNull("UserDao is null", getUserDao());
		assertNotNull("UserRightDao is null", getUserRightDao());
	}

	@Test
	public void persistTests() {
		ObservationCategorie oc = new ObservationCategorie();
		oc.setDescription("DESC1");
		oc.setTitle("TITLE!");
		User u = new User();
		u.setFamilyName("FamilyName");
		u.setHashedPassword("password1");
		u.setName("Name");
		u.seteMail("name@name.tld");
		UserRight ur = new UserRight("User", "N User halt...", UserRight.USER_VALUE);
		u.setUserRight(ur);
		Observation o = new Observation();
		o.setCoordinate(new Coordinate(1, 2));
		o.setDescription("ne Observation halt");
		o.setObservationCategorie(oc);
		o.setTitle("Title");
		o.setUser(u);
		getObservationCategorieDao().save(oc);
		getUserRightDao().save(ur);
		getUserDao().save(u);
		getObservationDao().save(o);
		assertFalse(oc.getId() == 0);
		assertFalse(o.getId() == 0);
		assertFalse(u.getId() == 0);
	}

	@Autowired
	private UserDao userDao;

	/**@return the userDao*/
	protected UserDao getUserDao() {
		return userDao;
	}

	/**@param userDao the userDao to set*/
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	private UserRightDao userRightDao;

	/**@return the userRightDao*/
	protected UserRightDao getUserRightDao() {
		return userRightDao;
	}

	/**@param userRightDao the userRightDao to set*/
	public void setUserRightDao(UserRightDao userRightDao) {
		this.userRightDao = userRightDao;
	}

	@Autowired
	private ObservationDao observationDao;

	/**@return the observationDao*/
	protected ObservationDao getObservationDao() {
		return observationDao;
	}

	/**@param observationDao the observationDao to set*/
	public void setObservationDao(ObservationDao observationDao) {
		this.observationDao = observationDao;
	}

	@Autowired
	private ObservationCategorieDao observationCategorieDao;

	/**@param observationCategorieDao the observationCategorieDao to set*/
	public void setObservationCategorieDao(ObservationCategorieDao observationCategorieDao) {
		this.observationCategorieDao = observationCategorieDao;
	}

	/**@return the observationCategorieDao*/
	protected ObservationCategorieDao getObservationCategorieDao() {
		return this.observationCategorieDao;
	}
}
