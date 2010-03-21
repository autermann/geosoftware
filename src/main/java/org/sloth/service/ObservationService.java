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
package org.sloth.service;

import java.util.Collection;
import java.util.Map;
import org.sloth.exceptions.ConstraintViolationException;
import org.sloth.model.Observation;
import org.sloth.model.Categorie;

/**
 * Service interface to handle {@code Observations} and {@code Categorie}s.
 * 
 * @see Categorie
 * @see Observation
 * @author Christian Autermann
 */
public interface ObservationService {

	/**
	 * Returns the {@code Observation} with the specified {@code id}. If the
	 * system does not an {@code Observation} with that id, {@code null} is
	 * returned.
	 *
	 * @param id the {@code id} of the {@code Observation}
	 * @return the {@code Observation} or {@code null}
	 * @throws NullPointerException if {@code id} is {@code null}
	 */
	public Observation getObservation(Long id) throws NullPointerException;

	/**
	 * Returns the {@code Categorie} with the specified {@code id}. If the
	 * system does not an {@code Categorie} with that id, {@code null} is
	 * returned.
	 *
	 * @param id the {@code id} of the {@code Categorie}
	 * @return the {@code Categorie} or {@code null}
	 * @throws NullPointerException if {@code id} is {@code null}
	 */
	public Categorie getCategorie(Long id) throws NullPointerException;

	/**
	 * Returns all {@code Observation}s known by the system. If no
	 * {@code Observation} is known an empty {@code Collection} will be
	 * returned.
	 * 
	 * @return all {@code Observation}s
	 */
	public Collection<Observation> getObservations();

	/**
	 * Returns all {@code Observation}s in the specified {@code Categorie}. The
	 * {@code Categorie} has to be known by the system and not {@code null}.
	 * @param oc
	 * @return
	 * @throws IllegalArgumentException if {@code oc} is not in the system
	 * @throws NullPointerException if {@code oc} is {@code null}
	 */
	public Collection<Observation> getObservations(Categorie oc) throws
			IllegalArgumentException, NullPointerException;

	/**
	 * @todo
	 * @param keyword
	 * @return
	 * @throws NullPointerException if {@code keyword} is {@code null}
	 */
	public Collection<Observation> getObservations(String keyword) throws
			NullPointerException;

	/**
	 * Returns all {@code Categorie}s known by the system. If no
	 * {@code Categorie} is known an empty {@code Collection} will be
	 * returned.
	 *
	 * @return all {@code Categorie}s
	 */
	public Collection<Categorie> getCategories();

	/**
	 * Deletes an {@code Observation}.
	 * 
	 * @param id the id
	 * @throws NullPointerException if {@code id} is {@code null}
	 * @throws IllegalArgumentException if no {@code Observation} with
	 * {@code id} as Id is known by the system
	 */
	public void deleteObservation(Long id) throws NullPointerException,
												  IllegalArgumentException;

	/**
	 * Deletes an {@code Observation}.
	 *
	 * @param observation the {@code Observation}
	 * @throws NullPointerException if {@code observation} is {@code null}
	 * @throws IllegalArgumentException if {@code observation} is not know by
	 * the system
	 */
	public void deleteObservation(Observation observation) throws
			NullPointerException, IllegalArgumentException;

	/**
	 * Deletes a {@code Categorie}.
	 *
	 * @param categorie the {@code Categorie}
	 * @throws NullPointerException if {@code categorie} is {@code null}
	 * @throws IllegalArgumentException if {@code categorie} is not know by
	 * the system or {@code categorie} is the default {@code Categorie}
	 */
	public void deleteCategorie(Categorie categorie) throws NullPointerException,
															IllegalArgumentException;

	/**
	 * Deletes a {@code Categorie}.
	 *
	 * @param id the id
	 * @throws NullPointerException if {@code id} is {@code null}
	 * @throws IllegalArgumentException if no {@code Categorie} with {@code id}
	 * as Id is known by the system or the matching {@code categorie} is the
	 * default {@code Categorie}
	 */
	public void deleteCategorie(Long id);

	/**
	 * Merges changes made to {@code categorie} into the system.
	 * 
	 * @param categorie the {@code Categorie}
	 * @throws NullPointerException if {@code categorie} is {@code null}
	 * @throws IllegalArgumentException if {@code categorie} is not known by the
	 * system
	 * @throws ConstraintViolationException if {@code categorie} violates a
	 * system constraint
	 */
	public void updateCategorie(Categorie categorie) throws NullPointerException,
															IllegalArgumentException,
															ConstraintViolationException;

	/**
	 * Merges changes made to {@code observation} into the system.
	 *
	 * @param observation the {@code Observation}
	 * @throws NullPointerException if {@code observation} is {@code null}
	 * @throws IllegalArgumentException if {@code observation} is not known by
	 * the system
	 * @throws ConstraintViolationException if {@code observation} violates a
	 * system constraint
	 */
	public void updateObservation(Observation observation) throws
			NullPointerException, IllegalArgumentException,
			ConstraintViolationException;

	/**
	 * Adds a {@code Categorie} to the system.
	 * 
	 * @param categorie the {@code Categorie}
	 * @throws NullPointerException if {@code categorie} is {@code null}
	 * @throws IllegalArgumentException if {@code categorie} is already known by
	 * the system.
	 * @throws ConstraintViolationException if {@code categorie} violates a
	 * system constraint
	 */
	public void registrate(Categorie categorie) throws NullPointerException,
													   IllegalArgumentException,
													   ConstraintViolationException;

	/**
	 * Adds an {@code Observation} to the system.
	 *
	 * @param observation the {@code Observation}
	 * @throws NullPointerException if {@code observation} is {@code null}
	 * @throws IllegalArgumentException if {@code observation} is already known
	 * by the system.
	 * @throws ConstraintViolationException if {@code observation} violates a
	 * system constraint
	 */
	public void registrate(Observation observation) throws NullPointerException,
														   IllegalArgumentException,
														   ConstraintViolationException;

	/**
	 * @todo
	 * @return
	 */
	public Map<Categorie, Collection<Observation>> getObservationsByCategories();


	public Categorie getCategorieByTitle(String title);
}
