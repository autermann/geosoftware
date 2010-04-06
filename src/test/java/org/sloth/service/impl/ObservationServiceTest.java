package org.sloth.service.impl;

import java.util.Collection;
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
	}

	@Test
	public void selfTest() {
		assertNotNull(observationService);
	}

	@Test
	public void testGetObservationById() {
		Categorie c = getCategorie();
		User u = getUser();
		LinkedList<Observation> oList = new LinkedList<Observation>();
		for (int i = 0; i < 200; i++) {
			Observation o = getObservation(c, u);
			observationService.registrate(o);
			oList.add(o);
		}
		for (Observation o : oList) {
			assertNotNull(o.getId());
			assertEquals(o, observationService.getObservation(o.getId()));
		}
		assertNull(observationService.getObservation(Long.valueOf(-4)));
		assertNull(observationService.getObservation(Long.valueOf(12341234)));
	}

	@Test
	public void testGetObservations() {
		for (int i = 0; i < 100; i++) {
			observationService.registrate(getObservation(getCategorie(), getUser()));
			assertEquals(i + 1, observationService.getObservations().size());
		}
	}

	@Test
	public void testGetObservationsByKeyword() {
		Categorie c1 = getCategorie();
		Categorie c2 = getCategorie();
		Categorie c3 = getCategorie();
		Observation o1 = getObservation(c1, getUser());
		Observation o2 = getObservation(c2, getUser());
		Observation o3 = getObservation(c3, getUser());
		Observation o4 = getObservation(c3, getUser());
		String keyword = "ZdbnksaiI1";
		String keyword2 = "2342134fgsdadc" + keyword + "asgdh";
		c1.setTitle(keyword2);
		c2.setDescription(keyword2);
		o3.setTitle(keyword2);
		o4.setDescription(keyword2);
		Collection<Observation> list = observationService.getObservations(keyword);
		assertTrue(list.contains(o1));
		assertTrue(list.contains(o2));
		assertTrue(list.contains(o3));
		assertTrue(list.contains(o4));
	}

	@Test
	public void testDeleteObservation() {
	}

	@Test
	public void testUpdateObservation() throws Exception {
	}

	@Test
	public void testRegistrateObservation() throws Exception {
	}

	@Test
	public void testGetObservationsByCategorie() {
	}

	@Test
	public void testGetCategorieById() {
	}

	@Test
	public void testGetCategories() {
	}

	@Test
	public void testDeleteCategorie() {
	}

	@Test
	public void testDeleteCategorieById() {
	}

	@Test
	public void testUpdateCategorie() throws Exception {
	}

	@Test
	public void testRegistrateCategorie() throws Exception {
	}

	@Test
	public void testDeleteObservationById() {
	}

	@Test
	public void testGetCategorieByTitle() {
	}

	@Test
	public void testGetObservationsByUser() {
	}

	@Test
	public void testGetReports() {
	}

	@Test
	public void testGetReportsByUser() {
	}

	@Test
	public void testGetReportsByObservation() {
	}

	@Test
	public void testGetReportsByProcessedState() {
	}

	@Test
	public void testDeleteReport() {
	}

	@Test
	public void testRegistrateReport() throws Exception {
	}

	@Test
	public void testGetReport() {
	}

	@Test
	public void testUpdateReport() throws Exception {
	}

	@Test
	public void testGetNewestObservations() {
	}

	@Test
	public void testIsCategorieTitleAvailable() {
	}
}
