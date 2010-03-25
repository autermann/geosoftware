package org.sloth.service.impl;

import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.sloth.EntityFactory.*;
import org.sloth.model.User;
import org.sloth.model.Categorie;
import org.sloth.model.Observation;
import org.sloth.persistence.CategorieDao;
import org.sloth.persistence.ObservationDao;
import org.sloth.persistence.UserDao;

public class ObservationServiceTest {

	private ObservationDao observationDao;
	private CategorieDao categorieDao;
	private UserDao userDao;
	private ObservationServiceImpl observationService;
	private List<User> userList = new LinkedList<User>();
	private List<Observation> observationList = new LinkedList<Observation>();
	private List<Categorie> categorieList = new LinkedList<Categorie>();

	@Before
	public void setUp() {
		observationService = new ObservationServiceImpl();
		userDao = new InMemoryUserDao();
		observationDao = new InMemoryObservationDao();
		categorieDao = new InMemoryCategorieDao();
		observationService.setCategorieDao(categorieDao);
		observationService.setObservationDao(observationDao);
		reset();
		fillDaos();
	}

	private void fillDaos() {
		for (int i = 0; i < 10; i++) {
			User u = getUser();
			userList.add(u);
			userDao.save(u);
		}
		for (int i = 0; i < 5; i++) {
			Categorie c = getCategorie();
			categorieList.add(c);
			categorieDao.save(c);
		}
		for (int o = 0, u = 0, c = 0; o < 50; o++) {
			Observation ob = getObservation(categorieList.get(c++
															  % categorieList.size()),
					userList.get(u++ % userList.size()));
			observationList.add(ob);
			observationDao.save(ob);
		}
		for (Observation o : observationList)
			System.out.println(o);
	}

	@Test
	public void selfTest() {
		assertNotNull(observationService);
	}

	@Test
	public void testGetObservations_0args() {
		assertEquals(observationService.getObservations().size(), observationList.size());
	}

	@Test
	public void testGetObservations_String() {
	}

	@Test
	public void testDeleteObservation_Observation() {
	}

	@Test
	public void testUpdateObservation() throws Exception {
	}

	@Test
	public void testRegistrate_Observation() throws Exception {
	}

	@Test
	public void testGetObservations_Categorie() {
	}

	@Test
	public void testGetCategorie() {
	}

	@Test
	public void testGetCategories() {
		assertEquals(observationService.getCategories().size(), categorieList.size());
	}

	@Test
	public void testDeleteCategorie_Categorie() {
	}

	@Test
	public void testDeleteCategorie_Long() {
	}

	@Test
	public void testUpdateCategorie() throws Exception {
	}

	@Test
	public void testRegistrate_Categorie() throws Exception {
	}

	@Test
	public void testDeleteObservation_Long() {
	}

	@Test
	public void testGetObservationsByCategories() {
	}

	@Test
	public void testGetCategorieByTitle() {
	}
}
