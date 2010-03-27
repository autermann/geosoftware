/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sloth.persistence;

import java.util.Collection;
import org.sloth.model.Observation;
import org.sloth.model.Report;
import org.sloth.model.User;

/**
 * TODO
 */
public interface ReportDao extends BaseEntityDao<Report> {

	public Collection<Report> getByUser(User u) throws NullPointerException,
			IllegalArgumentException;

	public Collection<Report> getByObservation(Observation o)
			throws NullPointerException, IllegalArgumentException;

	public Collection<Report> getUnprocessed();

	public Collection<Report> getProcessed();
}
