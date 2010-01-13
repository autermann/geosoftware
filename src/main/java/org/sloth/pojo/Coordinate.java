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
package org.sloth.pojo;

/**
 * Represents a simple two valued coordinate.
 * 
 * @author Christian Autermann
 * @version 1.0
 * @since 1.0
 */
public class Coordinate {

	private double latitude = -1;
	private double longitude = -1;

	/**
	 * Creates a Coordinate with <code>-1</code> as value for
	 * <code>latitude</code> and <code>longitude</code>.
	 */
	public Coordinate() {
		/* nothing to do here */
	}

	/**
	 * Creates a new Coordinate.
	 * @param lon the longitude
	 * @param lat the latitude
	 */
	public Coordinate(double lon, double lat) {
		this.setLatitude(lat);
		this.setLongitude(lon);
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	@SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
	public boolean equals(Object obj) {
		if (obj != null) {
			return this.hashCode() == obj.hashCode();
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 47 * hash + (int) (Double.doubleToLongBits(this.getLatitude())
								  ^ (Double.doubleToLongBits(this.getLatitude())
									 >>> 32));
		hash = 47 * hash + (int) (Double.doubleToLongBits(this.getLongitude())
								  ^ (Double.doubleToLongBits(this.getLongitude())
									 >>> 32));
		return hash;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("(");
		buf.append(this.getLongitude());
		buf.append(",");
		buf.append(this.getLatitude());
		buf.append(")");
		return buf.toString();
	}

}
