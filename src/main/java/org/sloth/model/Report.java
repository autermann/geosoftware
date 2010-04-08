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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.sloth.exception.ConstraintViolationException;
import org.sloth.exception.FieldLengthConstraintViolationException;
import org.sloth.exception.NotNullConstraintViolationException;

/**
 * Model class for Reports created by {@code User}s for {@code Observation}s.
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
@Entity(name = "REPORTS")
public class Report extends BaseEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(nullable = false, name = "OBSERVATION_ID")
	private Observation observation;
	@ManyToOne
	@JoinColumn(nullable = false, name = "AUTHOR_ID")
	private User author;
	@Column(name = "DESCRIPTION", nullable = false, length = 1000)
	private String description;
	@Column(name = "PROCESSED", nullable = false)
	private boolean processed = false;
	@Column(name = "CREATION_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	@Column(name = "LAST_UPDATE_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdateDate;

	/**
	 * Creates a new {@code Report}. {@code creationDate} and {@code
	 * lastUpdateDate} are set too the actual time. All other properties are
	 * initialised with {@code null}.
	 */
	public Report() {
		this.creationDate = this.lastUpdateDate = new Date();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Report) {
			return obj.hashCode() == this.hashCode();
		} else {
			return false;
		}
	}

	/**
	 * @return the author
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the lastUpdateDate
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	/**
	 * @return the observation
	 */
	public Observation getObservation() {
		return observation;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 37 * hash + this.getVersion();
		hash = 37 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
		hash = 37 * hash
				+ (this.observation != null ? this.observation.hashCode() : 0);
		hash = 37 * hash + (this.author != null ? this.author.hashCode() : 0);
		hash = 37 * hash
				+ (this.description != null ? this.description.hashCode() : 0);
		hash = 37 * hash + (this.processed ? 1 : 0);
		hash = 37
				* hash
				+ (this.creationDate != null ? this.creationDate.hashCode() : 0);
		hash = 37
				* hash
				+ (this.lastUpdateDate != null ? this.lastUpdateDate.hashCode()
						: 0);
		return hash;
	}

	/**
	 * @return the processed
	 */
	public boolean isProcessed() {
		return processed;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(User author) {
		this.author = author;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param lastUpdateDate
	 *            the lastUpdateDate to set
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	/**
	 * @param observation
	 *            the observation to set
	 */
	public void setObservation(Observation observation) {
		this.observation = observation;
	}

	/**
	 * @param processed
	 *            the processed to set
	 */
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	@Override
	public void validate() throws ConstraintViolationException {
		if (this.author == null || this.observation == null
				|| this.description == null
				|| this.description.trim().isEmpty()) {
			throw new NotNullConstraintViolationException();
		}
		if (this.description.length() > 1000) {
			throw new FieldLengthConstraintViolationException();
		}
	}

	@Override
	public Object clone() {
		Report r = new Report();
		r.setCreationDate(creationDate);
		r.setLastUpdateDate(lastUpdateDate);
		r.setAuthor(author);
		r.setObservation(observation);
		r.setDescription(description);
		r.setId(new Long(getId()));
		r.setProcessed(processed);
		r.setVersion(getVersion());
		return r;
	}
}
