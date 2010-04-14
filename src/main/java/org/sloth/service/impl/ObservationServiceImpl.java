/*
 * Copyright (C) 2009-2010  Stefan Arndt, Christian Autermann, Dustin Demuth,
 *                  Christoph Fendrich, Simon Ottenhues, Christian Paluschek
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

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.sloth.exception.ConstraintViolationException;
import org.sloth.model.Categorie;
import org.sloth.model.Observation;
import org.sloth.model.Report;
import org.sloth.model.User;
import org.sloth.persistence.CategorieDao;
import org.sloth.persistence.ObservationDao;
import org.sloth.persistence.ReportDao;
import org.sloth.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implemtation of {@link ObservationService}.
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
@Service
public class ObservationServiceImpl implements ObservationService {

	private Logger logger = LoggerFactory.getLogger(ObservationServiceImpl.class);
	private ObservationDao observationDao;
	private CategorieDao categorieDao;
	private ReportDao reportDao;

	@Override
	public void deleteCategorie(Categorie categorie)
			throws NullPointerException, IllegalArgumentException {
		if (categorie == null) {
			throw new NullPointerException();
		} else {
			logger.info("Deleting Categorie: {}", categorie);
			this.categorieDao.delete(categorie);
		}
	}

	@Override
	public void deleteCategorie(Long id) throws NullPointerException,
			IllegalArgumentException {
		if (id == null) {
			throw new NullPointerException();
		} else {
			this.deleteCategorie(this.getCategorie(id));
		}
	}

	@Override
	public void deleteObservation(Long id) throws NullPointerException,
			IllegalArgumentException {
		if (id == null) {
			throw new NullPointerException();
		} else {
			this.deleteObservation(this.getObservation(id));
		}
	}

	@Override
	public void deleteObservation(Observation observation)
			throws NullPointerException, IllegalArgumentException {
		if (observation == null) {
			throw new NullPointerException();
		} else {
			logger.info("Deleting Observation {}", observation);
			this.observationDao.delete(observation);
		}
	}

	@Override
	public void deleteReport(Report r) throws NullPointerException,
			IllegalArgumentException {
		if (r == null) {
			throw new NullPointerException();
		} else {
			logger.info("Deleting Report {}", r);
			this.reportDao.delete(r);
		}
	}

	@Override
	public Categorie getCategorie(Long id) throws NullPointerException {
		if (id == null) {
			throw new NullPointerException();
		} else {
			logger.info("Getting Categorie with Id {}", id);
			return this.categorieDao.getById(id);
		}
	}

	@Override
	public Categorie getCategorieByTitle(String title) {
		if (title == null) {
			throw new NullPointerException();
		} else {
			logger.info("Getting Categorie with title {}", title);
			return this.categorieDao.getByTitle(title);
		}
	}

	@Override
	public Collection<Categorie> getCategories() {
		logger.info("Getting all Categories");
		return this.categorieDao.getAll();
	}

	@Override
	public List<Observation> getNewestObservations(int u) {
		logger.info("Getting {} newest Observations", u);
		return this.observationDao.getNewestObservations(u);
	}

	@Override
	public Observation getObservation(Long id) throws NullPointerException {
		if (id == null) {
			throw new NullPointerException();
		} else {
			logger.info("Getting Observation with Id {}", id);
			return this.observationDao.getById(id);
		}
	}

	@Override
	public Collection<Observation> getObservations() {
		logger.info("Getting all Observations");
		return this.observationDao.getAll();
	}

	@Override
	public Collection<Observation> getObservations(Categorie oc)
			throws NullPointerException, IllegalArgumentException {
		if (oc == null) {
			throw new NullPointerException();
		} else {
			logger.info("Getting all Observations in {}", oc);
			return this.observationDao.getByCategorie(oc);
		}
	}

	@Override
	public Collection<Observation> getObservations(String keyword)
			throws NullPointerException {
		if (keyword == null) {
			throw new NullPointerException();
		} else {
			logger.info("Getting all Observations matching '{}'", keyword);
			return this.observationDao.getByKeyWord(keyword);
		}
	}

	@Override
	public Collection<Observation> getObservationsByUser(User u) {
		if (u == null) {
			throw new NullPointerException();
		} else {
			logger.info("Getting all Observations by {}", u);
			return this.observationDao.getByUser(u);
		}
	}

	@Override
	public Report getReport(Long id) throws NullPointerException {
		if (id == null) {
			throw new NullPointerException();
		} else {
			logger.info("Getting Report with Id {}", id);
			return this.reportDao.getById(id);
		}
	}

	@Override
	public Collection<Report> getReports() {
		logger.info("Getting all Reports");
		return this.reportDao.getAll();
	}

	@Override
	public Collection<Report> getReportsByObservation(Observation o)
			throws NullPointerException, IllegalArgumentException {
		if (o == null) {
			throw new NullPointerException();
		} else {
			logger.info("Getting all Reports for {}", o);
			return this.reportDao.getByObservation(o);
		}
	}

	@Override
	public Collection<Report> getReportsByProcessedState(boolean processed) {
		logger.info("Getting all {} Reports", (processed) ? "processed":"unprocessed");
		return (processed) ? this.reportDao.getProcessed() : this.reportDao
				.getUnprocessed();
	}

	@Override
	public Collection<Report> getReportsByUser(User u)
			throws NullPointerException, IllegalArgumentException {
		if (u == null) {
			throw new NullPointerException();
		} else {
			logger.info("Getting Reports by {}", u);
			return this.reportDao.getByUser(u);
		}
	}

	@Override
	public boolean isCategorieTitleAvailable(String title) {
		logger.info("Testing if Categorie with title '{}' is available", title);
		return this.getCategorieByTitle(title) == null;
	}

	@Override
	public void registrate(Categorie categorie)
			throws ConstraintViolationException, NullPointerException {
		if (categorie == null) {
			throw new NullPointerException();
		} else {
			logger.info("Registering new Categorie {}", categorie);
			this.categorieDao.save(categorie);
		}
	}

	@Override
	public void registrate(Observation observation)
			throws NullPointerException, ConstraintViolationException,
			IllegalArgumentException {
		if (observation == null) {
			throw new NullPointerException();
		} else {
			logger.info("Registering new Observation {}", observation);
			this.observationDao.save(observation);
		}
	}

	@Override
	public void registrate(Report r) throws NullPointerException,
			IllegalArgumentException, ConstraintViolationException {
		if (r == null) {
			throw new NullPointerException();
		} else {
			logger.info("Registering new Report {}", r);
			this.reportDao.save(r);
		}
	}

	/**
	 * @param ocDao
	 *            the categorieDao to set
	 */
	@Autowired
	public void setCategorieDao(CategorieDao ocDao) {
		if (ocDao == null) {
			throw new NullPointerException();
		} else {
			this.categorieDao = ocDao;
		}
	}

	/**
	 * @param oDao
	 *            the observationDao to set
	 */
	@Autowired
	public void setObservationDao(ObservationDao oDao) {
		if (oDao == null) {
			throw new NullPointerException();
		} else {
			this.observationDao = oDao;
		}
	}

	/**
	 * @param rDao
	 *            the reportDao to set
	 */
	@Autowired
	public void setReportDao(ReportDao rDao) {
		if (rDao == null) {
			throw new NullPointerException();
		} else {
			this.reportDao = rDao;
		}
	}

	@Override
	public void updateCategorie(Categorie categorie)
			throws NullPointerException, ConstraintViolationException,
			IllegalArgumentException {
		if (categorie == null) {
			throw new NullPointerException();
		} else {
			logger.info("Updating Categorie {}", categorie);
			this.categorieDao.update(categorie);
		}
	}

	@Override
	public void updateObservation(Observation observation)
			throws NullPointerException, ConstraintViolationException,
			IllegalArgumentException {
		if (observation == null) {
			throw new NullPointerException();
		} else {
			logger.info("Updating Observation {}", observation);
			this.observationDao.update(observation);
		}
	}

	@Override
	public void updateReport(Report r) throws NullPointerException,
			IllegalArgumentException, ConstraintViolationException {
		if (r == null) {
			throw new NullPointerException();
		} else {
			logger.info("Updating Report {}", r);
			r.setLastUpdateDate(new Date());
			this.reportDao.update(r);
		}
	}
}
