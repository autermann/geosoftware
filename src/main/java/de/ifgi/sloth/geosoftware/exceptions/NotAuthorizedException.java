/*
 * Copyright (C) 2009  Stefan Arndt, Christian Autermann, Dustin Demuth,
 * 					 Christoph Fendrich, Christian Paluschek
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
package de.ifgi.sloth.geosoftware.exceptions;

/**
 *
 * @author Christian Autermann
 */
public class NotAuthorizedException extends Exception {

	/**
	 * 
	 */
	public static final long serialVersionUID = 0;

	/**
	 *
	 * @param message
	 */
	public NotAuthorizedException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	public NotAuthorizedException() {
		super();
	}
}
