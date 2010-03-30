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
package org.sloth.persistence;

import java.util.Collection;
import org.sloth.model.Categorie;
import org.sloth.model.Observation;
import org.sloth.model.Report;
import org.sloth.model.User;

/**
 * Data Access Object for {@link Categorie}
 */
public interface ReportDao {

	/**
	 * Query for all Entities.
	 *
	 * @return all Entities found
	 */
	public Collection<Report> getAll();

	/**
	 * Query for a Entity with a known {@code id}.
	 *
	 * @param id
	 *            the id
	 * @return the @code Entity with the specified id, if no matching Entity
	 *         found {@code null} is returned.
	 */
	public Report getById(Long id);

	/**
	 * Update a Entity. Invoking this method with an Entity not known by the
	 * database will cause an {@code IllegalArgumentException}.
	 *
	 * @param t
	 *            the Entity to be updated
	 * @throws NullPointerException
	 *             if {@code t} is {@code null}
	 * @throws IllegalArgumentException
	 *             if {@code t} is not found in the database.
	 */
	public void update(Report t) throws NullPointerException,
										IllegalArgumentException;

	/**
	 * Delete a Entity from database. Invoking this method with an Entity not
	 * known by the database will cause an {@code IllegalArgumentException}.
	 *
	 * @param t
	 *            the Entity to be deleted
	 * @throws NullPointerException
	 *             if {@code t} is {@code null}
	 * @throws IllegalArgumentException
	 *             if {@code t} is not found in the database.
	 */
	public void delete(Report t) throws NullPointerException,
										IllegalArgumentException;

	/**
	 * Delete Entities from database. Invoking this method with Entities not
	 * known by the database will cause an {@code IllegalArgumentException}.
	 *
	 * @param t
	 *            the Entities to be deleted
	 * @throws NullPointerException
	 *             if {@code t} or {@code t}'s content is {@code null}
	 * @throws IllegalArgumentException
	 *             if Entities are not found in the database.
	 */
	public void delete(Collection<Report> t) throws NullPointerException,
													IllegalArgumentException;

	/**
	 * Save a Report in the database. {@link Categorie#id} will be generated.
	 *
	 * @param t
	 *            the {@code Report} to be saved
	 * @throws NullPointerException
	 *             if {@code t} is {@code null}
	 */
	public void save(Report t) throws NullPointerException;

	/**
	 * Return all {@code Report}s created by the specified {@code User}.
	 * 
	 * @param u the {@code User}
	 * @return all {@code Report}s of the {@code User}
	 * @throws NullPointerException if {@code u} is {@code null}
	 * @throws IllegalArgumentException if {@code u} is not known
	 */
	public Collection<Report> getByUser(User u) throws NullPointerException,
													   IllegalArgumentException;

	/**
	 * Return all {@code Report}s for the specified {@code Observation}.
	 *
	 * @param o the {@code Observation}
	 * @return all {@code Report}s for the {@code Observation}
	 * @throws NullPointerException if {@code o} is {@code null}
	 * @throws IllegalArgumentException if {@code o} is not known
	 */
	public Collection<Report> getByObservation(Observation o)
			throws NullPointerException, IllegalArgumentException;

	/**
	 * Get all {@code Report}s which are unprocessed.
	 *
	 * @see Report#isProcessed()
	 * @see Report#setProcessed(boolean)
	 * @return all processed {@code Report}s
	 */
	public Collection<Report> getUnprocessed();

	/**
	 * Get all {@code Report}s which are processed.
	 * 
	 * @see Report#isProcessed()
	 * @see Report#setProcessed(boolean)
	 * @return all processed {@code Report}s
	 */
	public Collection<Report> getProcessed();

}
