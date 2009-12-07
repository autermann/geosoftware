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
package de.ifgi.sloth.geosoftware.data;

/**
 *
 * @author Christian Autermann
 */
public class BoundingBox {

	private double left, right, bottom, top;

	/**
	 *
	 * @param top
	 * @param bottom
	 * @param right
	 * @param left
	 */
	public BoundingBox(double top, double bottom, double right, double left) {
		this.top = top;
		this.bottom = bottom;
		this.right = right;
		this.left = left;

	}

	/**
	 * 
	 * @param one
	 * @param two
	 */
	public BoundingBox(Coordinate one, Coordinate two) {
		if (one.getX() < two.getX()) {
			this.left = one.getX();
			this.right = two.getX();
		} else {
			this.left = two.getX();
			this.right = one.getX();
		}
		if (one.getY() < two.getY()) {
			this.top = two.getY();
			this.bottom = one.getY();
		} else {
			this.top = one.getY();
			this.bottom = two.getY();
		}
	}

	/**
	 *
	 * @return
	 */
	public double getHeight() {
		return getTop() - getBottom();
	}

	/**
	 *
	 * @return
	 */
	public double getWidth() {
		return getRight() - getLeft();
	}

	/**
	 *
	 * @return
	 */
	public Coordinate getNO() {
		return new Coordinate(getRight(), getTop());
	}

	/**
	 *
	 * @return
	 */
	public Coordinate getSO() {
		return new Coordinate(getRight(), getBottom());
	}

	/**
	 *
	 * @return
	 */
	public Coordinate getNW() {
		return new Coordinate(getLeft(), getTop());
	}

	/**
	 * 
	 * @return
	 */
	public Coordinate getSW() {
		return new Coordinate(getLeft(), getBottom());
	}

	/**
	 * @return the left
	 */
	public double getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(double left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public double getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(double right) {
		this.right = right;
	}

	/**
	 * @return the bottom
	 */
	public double getBottom() {
		return bottom;
	}

	/**
	 * @param bottom the bottom to set
	 */
	public void setBottom(double bottom) {
		this.bottom = bottom;
	}

	/**
	 * @return the top
	 */
	public double getTop() {
		return top;
	}

	/**
	 * @param top the top to set
	 */
	public void setTop(double top) {
		this.top = top;
	}
}
