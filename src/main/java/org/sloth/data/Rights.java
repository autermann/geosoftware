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
package org.sloth.data;

/**
 *
 * @author Christian Autermann
 */
public enum Rights {

	/**
	 *
	 */
	Administrator(
		true,//CAN_DELETE_OTHER_USERS,
		false,//CAN_DELETE_HIMSELF,
		true,//CAN_DELETE_OWN_OBSERVATIONS,
		true,//CAN_DELETE_OTHER_OBSERVATIONS,
		true,//CAN_DELETE_OBSERVATION_CATEGORIES,
		true,//CAN_CREATE_OBSERVATIONS,
		true,//CAN_CREATE_OBSERVATIONS_CATEGORIES,
		true,//CAN_MODIFY_OTHER_OBSERVATIONS,
		true,//CAN_MODIFY_OWN_OBSERVATIONS,
		false,//CAN_MODIFY_OTHER_USERS,
		true,//CAN_MODIFY_HIMSELF,
		true,//CAN_MODIFY_RIGHTS,
		true//CAN_REPORT_OBSERVATIONS
	),
	/**
	 *
	 */
	Guest(
		false,//CAN_DELETE_OTHER_USERS,
		false,//CAN_DELETE_HIMSELF,
		false,//CAN_DELETE_OWN_OBSERVATIONS,
		false,//CAN_DELETE_OTHER_OBSERVATIONS,
		false,//CAN_DELETE_OBSERVATION_CATEGORIES,
		false,//CAN_CREATE_OBSERVATIONS,
		false,//CAN_CREATE_OBSERVATIONS_CATEGORIES,
		false,//CAN_MODIFY_OTHER_OBSERVATIONS,
		false,//CAN_MODIFY_OWN_OBSERVATIONS,
		false,//CAN_MODIFY_OTHER_USERS,
		false,//CAN_MODIFY_HIMSELF,
		false,//CAN_MODIFY_RIGHTS,
		false//CAN_REPORT_OBSERVATIONS
	),
	/**
	 *
	 */
	WebUser(
		false,//CAN_DELETE_OTHER_USERS,
		true,//CAN_DELETE_HIMSELF,
		true,//CAN_DELETE_OWN_OBSERVATIONS,
		false,//CAN_DELETE_OTHER_OBSERVATIONS,
		false,//CAN_DELETE_OBSERVATION_CATEGORIES,
		true,//CAN_CREATE_OBSERVATIONS,
		false,//CAN_CREATE_OBSERVATIONS_CATEGORIES,
		false,//CAN_MODIFY_OTHER_OBSERVATIONS,
		false,//CAN_MODIFY_OWN_OBSERVATIONS,
		false,//CAN_MODIFY_OTHER_USERS,
		true,//CAN_MODIFY_HIMSELF,
		false,//CAN_MODIFY_RIGHTS,
		true//CAN_REPORT_OBSERVATIONS
	);
	/**
	 *
	 */
	public final boolean CAN_DELETE_OTHER_USERS;
	/**
	 *
	 */
	public final boolean CAN_DELETE_HIMSELF;
	/**
	 *
	 */
	public final boolean CAN_DELETE_OWN_OBSERVATIONS;
	/**
	 *
	 */
	public final boolean CAN_DELETE_OTHER_OBSERVATIONS;
	/**
	 *
	 */
	public final boolean CAN_DELETE_OBSERVATION_CATEGORIES;
	/**
	 *
	 */
	public final boolean CAN_CREATE_OBSERVATIONS;
	/**
	 *
	 */
	public final boolean CAN_CREATE_OBSERVATIONS_CATEGORIES;
	/**
	 *
	 */
	public final boolean CAN_MODIFY_OTHER_OBSERVATIONS;
	/**
	 *
	 */
	public final boolean CAN_MODIFY_OWN_OBSERVATIONS;
	/**
	 *
	 */
	public final boolean CAN_MODIFY_OTHER_USERS;
	/**
	 *
	 */
	public final boolean CAN_MODIFY_HIMSELF;
	/**
	 *
	 */
	public final boolean CAN_MODIFY_RIGHTS;
	/**
	 *
	 */
	public final boolean CAN_REPORT_OBSERVATIONS;

	private Rights(boolean CAN_DELETE_OTHER_USERS,
			boolean CAN_DELETE_HIMSELF,
			boolean CAN_DELETE_OWN_OBSERVATIONS,
			boolean CAN_DELETE_OTHER_OBSERVATIONS,
			boolean CAN_DELETE_OBSERVATION_CATEGORIES,
			boolean CAN_CREATE_OBSERVATIONS,
			boolean CAN_CREATE_OBSERVATIONS_CATEGORIES,
			boolean CAN_MODIFY_OTHER_OBSERVATIONS,
			boolean CAN_MODIFY_OWN_OBSERVATIONS,
			boolean CAN_MODIFY_OTHER_USERS,
			boolean CAN_MODIFY_HIMSELF,
			boolean CAN_MODIFY_RIGHTS,
			boolean CAN_REPORT_OBSERVATIONS) {
		this.CAN_DELETE_OTHER_USERS = CAN_DELETE_OTHER_USERS;
		this.CAN_DELETE_HIMSELF = CAN_DELETE_HIMSELF;
		this.CAN_DELETE_OWN_OBSERVATIONS = CAN_DELETE_OWN_OBSERVATIONS;
		this.CAN_DELETE_OTHER_OBSERVATIONS = CAN_DELETE_OTHER_OBSERVATIONS;
		this.CAN_DELETE_OBSERVATION_CATEGORIES = CAN_DELETE_OBSERVATION_CATEGORIES;
		this.CAN_CREATE_OBSERVATIONS = CAN_CREATE_OBSERVATIONS;
		this.CAN_CREATE_OBSERVATIONS_CATEGORIES = CAN_CREATE_OBSERVATIONS_CATEGORIES;
		this.CAN_MODIFY_OTHER_OBSERVATIONS = CAN_MODIFY_OTHER_OBSERVATIONS;
		this.CAN_MODIFY_OWN_OBSERVATIONS = CAN_MODIFY_OWN_OBSERVATIONS;
		this.CAN_MODIFY_OTHER_USERS = CAN_MODIFY_OTHER_USERS;
		this.CAN_MODIFY_HIMSELF = CAN_MODIFY_HIMSELF;
		this.CAN_MODIFY_RIGHTS = CAN_MODIFY_RIGHTS;
		this.CAN_REPORT_OBSERVATIONS = CAN_REPORT_OBSERVATIONS;
	}
}
