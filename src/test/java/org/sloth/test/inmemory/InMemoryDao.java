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
package org.sloth.test.inmemory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;
import org.sloth.model.BaseEntity;

public class InMemoryDao<T extends BaseEntity> {

	private Long count = 100L;
	private Hashtable<Long, T> database = new Hashtable<Long, T>();

	protected Set<Entry<Long,T>> getEntrySet(){
		return database.entrySet();
	}

	public Collection<T> getAll() {
		Collection<T> col = new ArrayList<T>();
		Enumeration<T> en = database.elements();
		while (en.hasMoreElements()) {
			col.add(en.nextElement());
		}
		return col;
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
		if (!t.isNew() && database.containsKey(t.getId())) {
			throw new IllegalArgumentException();
		}
		t.setId(count++);
		database.put(t.getId(), t);
	}

}