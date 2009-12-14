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
import org.sloth.data.*;
import java.util.*;
import static org.sloth.util.Log.*;
import static org.sloth.util.Configuration.get;

/**
 *
 * @author Christian Autermann
 */
public class SQLBinding extends DBBinding {
	private final String passwd = get("SQL_USERNAME");
	private final String usr = get("SQL_PASSWORD");
	private final String url = "jdbc:mysql://"
							+ get("SQL_HOST") + ":"
							+ get("SQL_PORT") + "/"
							+ get("SQL_DB_NAME");

	private final String tablePrefix = "sloth";
	private final String userTableName = "users";
	private final String observationTableName = "observations";
	private final String[] observationTableDefinition = {"email", "name", "bla",
														 "bla2"};

	
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
			cn = DriverManager.getConnection(url, usr, passwd);
			st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
									ResultSet.CONCUR_UPDATABLE);
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

	private Collection<Observation> getObservations(ResultSet rs) {
		Collection<Observation> result = new ArrayList<Observation>();
		try {
			while (rs.next()) {
				result.add(new Observation(null, null, null, null, 0, null, null));
			}
		} catch(SQLException e) {
			throwing(e);
		}
		return result;
	}
	
	private Collection<User> getUser(ResultSet rs) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Collection<Observation> getObservations() {
		return getObservations(executeQuery("select * from " + tablePrefix
											+ observationTableName));
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
