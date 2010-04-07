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
package org.sloth.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

/**
 * Represents a simple two valued coordinate.
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
@Embeddable
public class Coordinate implements Serializable, Cloneable {

	@Transient
	private static final long serialVersionUID = 6550470689926763724L;
	@Column(nullable = false)
	private double latitude;
	@Column(nullable = false)
	private double longitude;

	/**
	 * Creates a new Coordinate with 0 as default value for logitude and
	 * latitude.
	 */
	public Coordinate() {
	}

	/**
	 * Creates a new Coordinate.
	 * 
	 * @param lon
	 *            the longitude
	 * @param lat
	 *            the latitude
	 */
	public Coordinate(double lon, double lat) {
		this.latitude = lat;
		this.longitude = lon;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Coordinate) {
			return this.hashCode() == obj.hashCode();
		} else {
			return false;
		}
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 47
				* hash
				+ (int) (Double.doubleToLongBits(this.latitude) ^ (Double
						.doubleToLongBits(this.latitude) >>> 32));
		hash = 47
				* hash
				+ (int) (Double.doubleToLongBits(this.longitude) ^ (Double
						.doubleToLongBits(this.longitude) >>> 32));
		return hash;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("(");
		buf.append(this.longitude);
		buf.append(",");
		buf.append(this.latitude);
		buf.append(")");
		return buf.toString();
	}

	@Override
	public Object clone(){
		Coordinate c = new Coordinate();
		c.setLatitude(latitude);
		c.setLongitude(longitude);
		return c;
	}
}
