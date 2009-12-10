/*
 * Copyright (C) 2009  Stefan Arndt, Christian Autermann, Dustin Demuth,
 *                     Christoph Fendrich, Christian Paluschek
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
package org.sloth.core;
import java.util.Calendar;
import java.util.Collection;
import org.sloth.data.BoundingBox;
import org.sloth.data.Observation;
import org.sloth.data.ObservationCategorie;
import org.sloth.data.User;
import org.sloth.data.Coordinate;
import org.sloth.exceptions.NotAuthorizedException;
import org.sloth.frontend.Session;
import org.sloth.io.db.DBBinding;

/**
 *
 * @author Christian Autermann
 */
public class ObservationManagement {

	private Session session;

	/**
	 * 
	 * @param session
	 * @return
	 */
	public static ObservationManagement getInstance(Session session) {
		if (session == null) {
			throw new NullPointerException();
		}
		ObservationManagement om = new ObservationManagement();
		om.setSession(session);
		return om;
	}

	private ObservationManagement() {
	}

	private Session getSession() {
		return session;
	}

	private void setSession(Session session) {
		this.session = session;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Observation getObservationById(long id) {
		return DBBinding.getInstance().getObservationById(id);
	}

	/**
	 * 
	 * @param oc
	 * @return
	 */
	public Collection<Observation> getObservationsByCategorie(
			ObservationCategorie oc) {
		return DBBinding.getInstance().getObservationsByCategorie(oc);
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public Collection<Observation> getObservationsByKeyword(String s) {
		return DBBinding.getInstance().getObservationsByKeyword(s);
	}

	/**
	 * 
	 * @param u
	 * @return
	 * @throws NotAuthorizedException
	 */
	public Collection<Observation> getObservationsByUser(User u) throws
			NotAuthorizedException {
		if (!getSession().getUser().getRights().CAN_VIEW_OBSERVATIONS_BY_USER) {
			throw new NotAuthorizedException();
		}
		return DBBinding.getInstance().getObservationsByUser(u);
	}

	/**
	 * 
	 * @param after
	 * @param before
	 * @return
	 */
	public Collection<Observation> getObservationsByDate(Calendar after,
														 Calendar before) {
		return DBBinding.getInstance().getObservationsByDate(after, before);
	}

	/**
	 * 
	 * @param coverage
	 * @return
	 */
	public Collection<Observation> getObservationsByCoverage(
			BoundingBox coverage) {
		return DBBinding.getInstance().getObservationsByCoverage(coverage);
	}

	/**
	 * 
	 * @param oc
	 * @param title
	 * @param description
	 * @param location 
	 * @return
	 * @throws NotAuthorizedException
	 */
	public Observation addObservation(ObservationCategorie oc, String title,
									  String description, Coordinate location)
			throws NotAuthorizedException {
		if (!getSession().getUser().getRights().CAN_CREATE_OBSERVATIONS) {
			throw new NotAuthorizedException();
		}
		Observation o = new Observation(getSession().getUser(), title,
										description, Calendar.getInstance(),
										-1, location, oc);
		DBBinding.getInstance().addObservation(o);
		return o;
	}

	/**
	 * 
	 * @param o
	 * @param reason
	 * @throws NotAuthorizedException
	 */
	public void reportObservation(Observation o, String reason) throws
			NotAuthorizedException {
		if (!getSession().getUser().getRights().CAN_REPORT_OBSERVATIONS) {
			throw new NotAuthorizedException();
		}
		DBBinding.getInstance().reportObservation(o, reason);
	}

	/**
	 * 
	 * @param o
	 * @throws NotAuthorizedException
	 */
	public void deleteObservation(Observation o) throws NotAuthorizedException {
		if (o.getUser().equals(getSession().getUser())) {
			if (!getSession().getUser().getRights().CAN_DELETE_OWN_OBSERVATIONS) {
				throw new NotAuthorizedException();
			}
		} else if (!getSession().getUser().getRights().CAN_DELETE_OTHER_OBSERVATIONS) {
			throw new NotAuthorizedException();
		}
		DBBinding.getInstance().deleteObservation(o);
	}

	/**
	 * 
	 * @param o
	 * @throws NotAuthorizedException
	 */
	public void updateObservation(Observation o) throws NotAuthorizedException {
		if (o.getUser().equals(getSession().getUser())) {
			if (!getSession().getUser().getRights().CAN_MODIFY_OWN_OBSERVATIONS) {
				throw new NotAuthorizedException();
			}
		} else if (!getSession().getUser().getRights().CAN_MODIFY_OTHER_OBSERVATIONS) {
			throw new NotAuthorizedException();
		}
		DBBinding.getInstance().updateObservation(o);
	}

}
