package org.sloth.service.impl;

import java.util.Collection;
import java.util.LinkedList;
import org.sloth.model.Observation;
import org.sloth.model.Report;
import org.sloth.model.User;
import org.sloth.persistence.ReportDao;

public class InMemoryReportDao extends InMemoryDao<Report> implements ReportDao {

	@Override
	public void delete(Collection<Report> t) throws NullPointerException, IllegalArgumentException {
		this.deleteAll(t);
	}

	@Override
	public Collection<Report> getByObservation(Observation o) throws NullPointerException, IllegalArgumentException {
		Collection<Report> reports = new LinkedList<Report>();
		for (Report r : getAll()) {
			if (r.getObservation().equals(o)) {
				reports.add(r);
			}
		}
		return reports;
	}

	@Override
	public Collection<Report> getByUser(User u) throws NullPointerException, IllegalArgumentException {
		Collection<Report> reports = new LinkedList<Report>();
		for (Report r : getAll()) {
			if (r.getAuthor().equals(u)) {
				reports.add(r);
			}
		}
		return reports;
	}

	@Override
	public Collection<Report> getProcessed() {
		Collection<Report> reports = new LinkedList<Report>();
		for (Report r : getAll()) {
			if (r.isProcessed()) {
				reports.add(r);
			}
		}
		return reports;
	}

	@Override
	public Collection<Report> getUnprocessed() {
		Collection<Report> reports = new LinkedList<Report>();
		for (Report r : getAll()) {
			if (!r.isProcessed()) {
				reports.add(r);
			}
		}
		return reports;
	}
}
