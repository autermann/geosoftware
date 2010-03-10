/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sloth.persistence.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class EntityManagerDao {

	protected static final Logger logger = LoggerFactory.getLogger(EntityManagerDao.class);
	private EntityManager entityManager;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}
}
