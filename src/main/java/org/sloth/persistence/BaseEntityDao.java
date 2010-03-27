/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sloth.persistence;

import java.util.Collection;
import org.sloth.model.BaseEntity;

/**
 * @todo
 * @param <T>
 * @author auti
 */
public interface BaseEntityDao<T extends BaseEntity> {

	/**
	 * Query for all Entities.
	 *
	 * @return all Entities found
	 */
	public Collection<T> getAll();

	/**
	 * Query for a Entity with a known {@code id}.
	 *
	 * @param id the id
	 * @return the @code Entity with the specified id, if no
	 * matching Entity found {@code null} is returned.
	 */
	public T getById(Long id);

	/**
	 * Update a Entity. Invoking this method with an
	 * Entity not known by the database will cause an
	 * {@code IllegalArgumentException}.
	 *
	 * @param t the Entity to be updated
	 * @throws NullPointerException if {@code t} is {@code null}
	 * @throws IllegalArgumentException if {@code t} is not found
	 * in the database.
	 */
	public void update(T t) throws NullPointerException,
								   IllegalArgumentException;

	/**
	 * Delete a Entity from database. Invoking this method with an
	 * Entity not known by the database will cause an
	 * {@code IllegalArgumentException}.
	 *
	 * @param t the Entity to be deleted
	 * @throws NullPointerException if {@code t} is {@code null}
	 * @throws IllegalArgumentException if {@code t} is not found in the
	 * database.
	 */
	public void delete(T t) throws NullPointerException,
								   IllegalArgumentException;

	/**
	 * Delete Entities from database. Invoking this method with
	 * Entities not known by the database will cause an
	 * {@code IllegalArgumentException}.
	 *
	 * @param t the Entities to be deleted
	 * @throws NullPointerException if {@code t} or {@code t}'s content
	 * is {@code null}
	 * @throws IllegalArgumentException if Entities are not found in the
	 * database.
	 */
	public void delete(Collection<T> t) throws NullPointerException,
								   IllegalArgumentException;

	/**
	 * Save a categorie in the database. {@link Categorie#id} will be
	 * generated.
	 *
	 * @param t the {@code Categorie} to be saved
	 * @throws NullPointerException if {@code t} is {@code null}
	 */
	public void save(T t) throws NullPointerException;
}
