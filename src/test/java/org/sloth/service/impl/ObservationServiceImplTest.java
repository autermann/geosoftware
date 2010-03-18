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
package org.sloth.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.sloth.model.Categorie;
import org.sloth.model.Group;
import org.sloth.model.User;
import org.sloth.persistence.CategorieDao;
import org.sloth.persistence.ObservationDao;
import org.sloth.persistence.inmemory.InMemoryCategorieDao;
import org.sloth.persistence.inmemory.InMemoryObservationDao;

public class ObservationServiceImplTest {

	ObservationServiceImpl os;
	CategorieDao cDao;
	ObservationDao oDao;

	@Before
	public void setUp() {
		os = new ObservationServiceImpl();
		cDao = new InMemoryCategorieDao();
		oDao = new InMemoryObservationDao();
		os.setCategorieDao(cDao);
		os.setObservationDao(oDao);
		populateWithTestData();
	}

	private void populateWithTestData() {
		Categorie c1 = EntityFactory.getCategorie();
		Categorie c2 = EntityFactory.getCategorie();
		Categorie c3 = EntityFactory.getCategorie();
		User u1 = EntityFactory.getUser(Group.USER);
		User u2 = EntityFactory.getUser(Group.USER);
		User u3 = EntityFactory.getUser(Group.USER);
		User u4 = EntityFactory.getUser(Group.USER);
		cDao.save(c1);
		cDao.save(c2);
		cDao.save(c3);
		oDao.save(EntityFactory.getObservation(c1, u1));
		oDao.save(EntityFactory.getObservation(c2, u2));
		oDao.save(EntityFactory.getObservation(c3, u3));
		oDao.save(EntityFactory.getObservation(c1, u4));
		oDao.save(EntityFactory.getObservation(c2, u1));
		oDao.save(EntityFactory.getObservation(c3, u2));
		oDao.save(EntityFactory.getObservation(c1, u3));
	}

	/**
	 * Test stub to prevent "no test found"-errors...
	 */
	@Test
	public void stub() {}

}
