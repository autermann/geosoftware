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
package org.sloth.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import org.sloth.model.BaseEntity;

public class InMemoryDao<T extends BaseEntity> {

	private Long count = 100L;
	protected Hashtable<Long, T> database = new Hashtable<Long, T>();

	public List<T> getAll() {
		return Collections.list(database.elements());
	}

	public T getById(Long id) {
		return database.get(id);
	}

	public void update(T t) throws NullPointerException,
			IllegalArgumentException {
		if (t == null) {
			throw new NullPointerException();
		}
		if (t.isNew() || !database.containsKey(t.getId())) {
			throw new IllegalArgumentException();
		}

		database.remove(t.getId());
		database.put(t.getId(), t);
	}

	public void delete(T t) throws NullPointerException,
			IllegalArgumentException {
		if (t == null) {
			throw new NullPointerException();
		}
		if (t.isNew() || !database.containsKey(t.getId())) {
			throw new IllegalArgumentException();
		}
		database.remove(t.getId());
	}

	public void save(T t) throws NullPointerException {
		if (t == null) {
			throw new NullPointerException();
		}
		if (!t.isNew()) {
			throw new IllegalArgumentException();
		}
		if (!t.isNew() && database.containsKey(t.getId())) {
			throw new IllegalArgumentException();
		}
		t.setId(count++);
		database.put(t.getId(), t);
	}

	protected void deleteAll(Collection<T> c) {
		for (T t : c) {
			this.delete(t);
		}
	}
}
