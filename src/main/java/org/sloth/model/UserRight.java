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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * Representation of rigths of an user. As convention a UserRight with an higher
 * value means more rights. Additionaly rightA has in the scenario
 * <pre>
 * rightA.valueOf() >= rightB.valueOf()
 * </pre>
 * more or the same rights as rightB.
 * 
 * @see User
 * @since 1.0
 * @version 1.0
 * @author Christian Autermann
 */
@Entity(name="USER_RIGHT")
public class UserRight implements Serializable {

	@Id
	@Column(unique = true)
	private int value;
	@Column(nullable = false)
	private String name;
	@Column(length = 1000, nullable = false)
	private String description;
	/**
	 * @todo
	 */
	@Transient
	public static final int AMDINISTRATOR_VALUE = 20;
	/**
	 * @todo
	 */
	@Transient
	public static final int USER_VALUE = 10;
	/**
	 * @todo
	 */
	@Transient
	public static final int GUEST_VALUE = 0;

	/**
	 * Creates a new UserRight.
	 * @param title the title
	 * @param description the description
	 * @param value the value
	 */
	public UserRight(String title, String description, int value) {
		setValue(value);
		setDescription(description);
		setName(title);
	}

	/**
	 * Creates a new UserRight with <code>-1</code> as value and
	 * <code>null</code> as <code>title</code> and <code>decription</code>.
	 */
	public UserRight() {
		/* nothing to do here */
	}

	/**
	 * @return the id
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the title
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the title to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	@SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else {
			return this.hashCode() == obj.hashCode();
		}
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 11 * hash + this.getValue();
		hash = 11 * hash + (this.getName() != null
				? this.getName().hashCode() : 0);
		hash = 11 * hash + (this.getDescription() != null
				? this.getDescription().hashCode() : 0);
		return hash;
	}
}
