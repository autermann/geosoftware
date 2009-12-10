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
package org.sloth.io.db;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sloth.data.*;
import java.util.*;
import static org.sloth.util.Log.*;
import static org.sloth.util.Configuration.get;

/**
 *
 * @author Christian Autermann
 */
public class SQLBinding extends DBBinding {

	/**
	 * 
	 */
	public SQLBinding() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			throwing(e);
		}
	}

	private ResultSet executeQuery(String sql) {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			String url = "jdbc:mysql://" + get("SQL_HOST") + ":" + get(
					"SQL_PORT") + "/" + get("SQL_DB_NAME");
			cn = DriverManager.getConnection(url, get("SQL_USERNAME"),
					get("SQL_PASSWORD"));
			st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery(sql);
		} catch(Exception e) {
			throwing(e);
		} finally {
			try {if (null != rs) {rs.close();}}
			catch(Exception e) {throwing(e);}
			try {if (null != st) {st.close();}}
			catch(Exception e) {throwing(e);}
			try {if (null != cn) {cn.close();}}
			catch(Exception e) {throwing(e);}
		}
		return rs;
	}

	@Override
	public Collection<Observation> getObservations() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Observation getObservationById(
			long id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Collection<Observation> getObservationsByCategorie(
			ObservationCategorie oc) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Collection<Observation> getObservationsByKeyword(String s) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Collection<Observation> getObservationsByUser(User u) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Collection<Observation> getObservationsByDate(Calendar after,
														 Calendar before) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Collection<Observation> getObservationsByCoverage(
			BoundingBox coverage) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void addObservation(Observation o) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void reportObservation(Observation o, String reason) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void deleteObservation(Observation o) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void updateObservation(Observation o) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void deleteUser(User u) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void updateUser(User u) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Collection<User> getUserList() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void addUser(User u) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public User getUserByEmail(
			String mail) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
