package org.sloth.service.impl;

import java.util.Collection;
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

        Categorie c = getCategorie();
        User u = getUser();
        Observation o = getObservation(c, u);
        observationService.registrate(o);
        assertNotNull(observationService.getObservations(c));




    }

    @Test
    public void testGetCategorieById() {

        Categorie c = getCategorie();
        User u = getUser();
        Observation o = getObservation(c, u);
        observationService.registrate(o);
        assertNotNull(observationService.getCategorie(c.getId()));



    }

    @Test
    public void testGetCategories() {

        for (int i = 0; i < 100; i++) {
            observationService.registrate(getObservation(getCategorie(), getUser()));
            assertEquals(i + 1, observationService.getCategories().size());
        }
    }

    @Test
    public void testDeleteCategorie() {

        Categorie c = getCategorie();
        User u = getUser();
        Observation o = getObservation(c, u);
        observationService.registrate(o);
        assertNotNull(observationService.getCategorie(c.getId()));
        observationService.deleteCategorie(c);
        assertNull(observationService.getCategorie(c.getId()));

    }

    @Test
    public void testDeleteCategorieById() {

        Categorie c = getCategorie();
        User u = getUser();
        Observation o = getObservation(c, u);
        observationService.registrate(o);
        assertNotNull(observationService.getCategorie(c.getId()));
        observationService.deleteCategorie(c.getId());
        assertNull(observationService.getCategorie(c.getId()));

    }

    @Test
    public void testUpdateCategorie() throws Exception {

        Categorie c = getCategorie();
        User u = getUser();
        Observation o = getObservation(c, u);
        observationService.registrate(o);
        assertNotNull(observationService.getCategorie(c.getId()));
        observationService.updateCategorie(c);



    }

    @Test
    public void testRegistrateCategorie() throws Exception {

        Categorie c = getCategorie();
        observationService.registrate(c);
        assertNotNull(observationService.getCategorie(c.getId()));

    }

    @Test
    public void testDeleteObservationById() {

        Categorie c = getCategorie();
        User u = getUser();
        Observation o = getObservation(c, u);
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
            observationService.getReports();
            assertEquals(i + 1, observationService.getReports().size());
        }

    }

    @Test
    public void testGetReportsByUser() {

        Categorie c = getCategorie();
        User u = getUser();
        Observation o = getObservation(c, u);
        observationService.registrate(o);
        assertNotNull(observationService.getReportsByUser(u));


    }

    @Test
    public void testGetReportsByObservation() {


        Categorie c = getCategorie();
        User u = getUser();
        Observation o = getObservation(c, u);
        observationService.registrate(o);
        assertNotNull(observationService.getReportsByObservation(o));
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
        observationService.registrate(o);
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

        Categorie c = getCategorie();
        User u = getUser();
        

        int observations = 100;
        for (int i = 0; i < observations; i++) {
            
            Observation o = getObservation(c, u);
            observationService.registrate(o);
            observationList.add(o);
        }
        //counter for the Observations
        int i=0;
        for (Observation o : observationList){

            assertNotNull(observationService.getObservation(o.getId()));
            i++;
        }

        //camparision between the last index i and the newest observation
        assertEquals(i, observationService.getNewestObservations(observations));

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