/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sloth.persistence.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class EntityManagerDao {

	protected static final Log logger = LogFactory.getLog(EntityManagerDao.class);
	private EntityManager entityManager;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}
}
