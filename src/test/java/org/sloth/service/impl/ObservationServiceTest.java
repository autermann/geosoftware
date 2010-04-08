package org.sloth.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.sloth.EntityFactory.*;
import org.sloth.model.User;
import org.sloth.model.Categorie;
import org.sloth.model.Observation;
import org.sloth.model.Report;
import org.sloth.persistence.CategorieDao;
import org.sloth.persistence.ObservationDao;
import org.sloth.persistence.ReportDao;
import org.sloth.persistence.UserDao;

public class ObservationServiceTest {

	private ObservationDao observationDao;
	private CategorieDao categorieDao;
	private ReportDao reportDao;
	private UserDao userDao;
	private ObservationServiceImpl observationService;
	
	@Before
	public void setUp() {
		observationService = new ObservationServiceImpl();
		userDao = new InMemoryUserDao();
		observationDao = new InMemoryObservationDao();
		categorieDao = new InMemoryCategorieDao();
		reportDao = new InMemoryReportDao();
		observationService.setCategorieDao(categorieDao);
		observationService.setObservationDao(observationDao);
		observationService.setReportDao(reportDao);
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
		User u = getUser();
		Observation o1 = getObservation(c1, u);
		Observation o2 = getObservation(c2, u);
		Observation o3 = getObservation(c3, u);
		Observation o4 = getObservation(c3, u);
		String keyword = "ZdbnksaiI1";
		String keyword2 = "2342134fgsdadc" + keyword + "asgdh";
		c1.setTitle(keyword2);
		c2.setDescription(keyword2);
		o3.setTitle(keyword2);
		o4.setDescription(keyword2);
		observationService.registrate(c1);
		observationService.registrate(c2);
		observationService.registrate(c3);
		observationService.registrate(o1);
		observationService.registrate(o2);
		observationService.registrate(o3);
		observationService.registrate(o4);
		Collection<Observation> list = observationService.getObservations(keyword);
		assertTrue(list.contains(o1));
		assertTrue(list.contains(o2));
		assertTrue(list.contains(o3));
		assertTrue(list.contains(o4));
	}

	@Test
	public void testDeleteObservation() {
//            Categorie c = getCategorie();
//            User u = getUser();
//            Observation o = getObservation(c, u);
//            String key = "deleteObservation";
//            c.setTitle(key);
//            c.setDescription(key+ " Test");
//            observationService.registrate(o);
//            Collection<Observation> list = observationService.getObservations(key);
//            if (list.contains(o)){
//                observationService.deleteObservation(o);
//            }
//              assertFalse(list.contains(o));
//ja ich weiss die auti methode ist besser :), aber meine funktioniert auch !!! :D

		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		observationService.registrate(o);
		assertNotNull(observationService.getObservation(o.getId()));
		observationService.deleteObservation(o);
		assertNull(observationService.getObservation(o.getId()));
	}

	@Test
	public void testUpdateObservation() throws Exception {

		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		observationService.registrate(o);
		String title = "new Title";
		assertNotNull(observationService.getObservation(o.getId()));
		o.setTitle(title);
		observationService.updateObservation(o);
		assertEquals(title, observationService.getObservation(o.getId()).getTitle());
	}

	@Test
	public void testRegistrateObservation() throws Exception {

		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		observationService.registrate(o);
		assertNotNull(observationService.getObservation(o.getId()));
		assertNotNull(o.getId());

	}

	@Test
	public void testGetObservationsByCategorie() {
		Categorie c1 = getCategorie();
		Categorie c2 = getCategorie();
		Categorie c3 = getCategorie();
		User u = getUser();
		Observation o1 = getObservation(c1, u);
		Observation o2 = getObservation(c2, u);
		observationService.registrate(o1);
		observationService.registrate(o2);
		assertEquals(1, observationService.getObservations(c1).size());
		assertEquals(1, observationService.getObservations(c2).size());
		assertEquals(0, observationService.getObservations(c3).size());
	}

	@Test
	public void testGetCategorieById() {
		Categorie c = getCategorie();
		observationService.registrate(c);
		assertNotNull(observationService.getCategorie(c.getId()));
	}

	@Test
	public void testGetCategories() {
		for (int i = 0; i < 213; i++) {
			observationService.registrate(getCategorie());
		}
		assertEquals(213, observationService.getCategories().size());
	}

	@Test
	public void testDeleteCategorie() {
		Categorie c = getCategorie();
		observationService.registrate(c);
		assertNotNull(observationService.getCategorie(c.getId()));
		observationService.deleteCategorie(c);
		assertNull(observationService.getCategorie(c.getId()));

	}

	@Test
	public void testDeleteCategorieById() {
		Categorie c = getCategorie();
		observationService.registrate(c);
		assertNotNull(observationService.getCategorie(c.getId()));
		observationService.deleteCategorie(c.getId());
		assertNull(observationService.getCategorie(c.getId()));

	}

	@Test
	public void testUpdateCategorie() throws Exception {

		Categorie c = getCategorie();
		observationService.registrate(c);
		assertNotNull(observationService.getCategorie(c.getId()));
		c.setDescription("asdf");
		observationService.updateCategorie(c);
		assertEquals("asdf", observationService.getCategorie(c.getId()).getDescription());


	}

	@Test
	public void testRegistrateCategorie() throws Exception {
		Categorie c = getCategorie();
		observationService.registrate(c);
		assertNotNull(observationService.getCategorie(c.getId()));
	}

	@Test
	public void testDeleteObservationById() {
		Observation o = getObservation(getCategorie(), getUser());
		observationService.registrate(o);
		assertNotNull(observationService.getObservation(o.getId()));
		observationService.deleteObservation(o.getId());
		assertNull(observationService.getObservation(o.getId()));
	}

	@Test
	public void testGetCategorieByTitle() {
		Categorie c = getCategorie();
		observationService.registrate(c);
		assertNotNull(observationService.getCategorieByTitle(c.getTitle()));

	}

	@Test
	public void testGetObservationsByUser() {

		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		observationService.registrate(o);
		assertNotNull(observationService.getObservationsByUser(u));
	}

	@Test
	public void testGetReports() {
		for (int i = 0; i < 100; i++) {
			observationService.registrate(getReport(getUser(), getObservation(getCategorie(), getUser())));
			assertEquals(i + 1, observationService.getReports().size());
		}
	}

	@Test
	public void testGetReportsByUser() {
		Categorie c = getCategorie();
		User u1 = getUser();
		User u2 = getUser();
		Observation o = getObservation(c, getUser());
		observationService.registrate(getReport(u1, o));
		observationService.registrate(getReport(u2, o));
		assertEquals(1, observationService.getReportsByUser(u1).size());
		assertEquals(1, observationService.getReportsByUser(u2).size());
		assertEquals(0, observationService.getReportsByUser(getUser()).size());
	}

	@Test
	public void testGetReportsByObservation() {
		Categorie c = getCategorie();
		User u = getUser();
		Observation o1 = getObservation(c, u);
		Observation o2 = getObservation(c, u);
		Observation o3 = getObservation(c, u);
		observationService.registrate(getReport(u, o1));
		observationService.registrate(getReport(u, o2));
		assertEquals(1,observationService.getReportsByObservation(o1).size());
		assertEquals(1,observationService.getReportsByObservation(o2).size());
		assertEquals(0,observationService.getReportsByObservation(o3).size());
	}

	@Test
	public void testGetReportsByProcessedState() {

		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		observationService.registrate(o);
		assertNotNull(observationService.getReportsByProcessedState(true));
	}

	@Test
	public void testDeleteReport() {
		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		Report r = getReport(u, o);
		observationService.registrate(r);
		assertNotNull(observationService.getReport(r.getId()));
		observationService.deleteReport(r);
		assertNull(observationService.getReport(r.getId()));
	}

	@Test
	public void testRegistrateReport() throws Exception {

		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		Report r = getReport(u, o);
		observationService.registrate(r);
		assertNotNull(observationService.getReport(r.getId()));


	}

	@Test
	public void testGetReport() {

		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		Report r = getReport(u, o);
		observationService.registrate(r);
		assertNotNull(observationService.getReport(r.getId()));

	}

	@Test
	public void testUpdateReport() throws Exception {

		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		Report r = getReport(u, o);
		observationService.registrate(r);
		assertNotNull(observationService.getReport(r.getId()));
		observationService.updateReport(r);


	}

	@Test
	public void testGetNewestObservations() {
		reset();
		Categorie c = getCategorie();
		User u = getUser();
		LinkedList<Observation> list = new LinkedList<Observation>();

		long time = System.currentTimeMillis();
		int observations = 100;
		for (int i = 0; i < observations; i++) {
			Observation o = getObservation(c, u);
			// only milisecond accuracy... needs some stretching factor
			o.setCreationDate(new Date(time+(5*i)));
			observationService.registrate(o);
			list.add(o);
		}
		//counter for the Observations
		for (Observation o : list) {
			assertNotNull(observationService.getObservation(o.getId()));
		}
		assertEquals(observations, observationService.getObservations().size());
		assertEquals(list.getLast(), observationService.getNewestObservations(1).get(0));

	}

	@Test
	public void testIsCategorieTitleAvailable() {

		Categorie c = getCategorie();
		User u = getUser();
		Observation o = getObservation(c, u);
		observationService.registrate(o);
		assertNotNull(observationService.isCategorieTitleAvailable(c.getTitle()));
	}
}
