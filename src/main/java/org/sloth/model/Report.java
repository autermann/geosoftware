/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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

import org.sloth.exceptions.ConstraintViolationException;
import org.sloth.exceptions.FieldLengthConstraintViolationException;
import org.sloth.exceptions.NotNullConstraintViolationException;

/**
 * Model class for Reports created by {@code User}s for {@code Observation}s.
 *
 * @author Christian Autermann
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
	 * Creates a new {@code Report}. {@code creationDate} and {@code lastUpdateDate}
	 * are set too the actual time. All other properties are initialised with {@code null}.
	 */
	public Report() {
		this.creationDate = this.lastUpdateDate = new Date();
	}

	@Override
	public void validate() throws ConstraintViolationException {
		if (this.author == null
				|| this.observation == null
				|| this.description == null
				|| this.description.trim().isEmpty()) {
			throw new NotNullConstraintViolationException();
		}
		if (this.description.length() > 1000) {
			throw new FieldLengthConstraintViolationException();
		}
	}

	/**
	 * @return the observation
	 */
	public Observation getObservation() {
		return observation;
	}

	/**
	 * @param observation
	 *            the observation to set
	 */
	public void setObservation(Observation observation) {
		this.observation = observation;
	}

	/**
	 * @return the author
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(User author) {
		this.author = author;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the processed
	 */
	public boolean isProcessed() {
		return processed;
	}

	/**
	 * @param processed
	 *            the processed to set
	 */
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the lastUpdateDate
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	/**
	 * @param lastUpdateDate
	 *            the lastUpdateDate to set
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Report) {
			return obj.hashCode() == this.hashCode();
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 37 * hash + this.getVersion();
		hash = 37 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
		hash = 37 * hash + (this.observation != null ? this.observation.hashCode() : 0);
		hash = 37 * hash + (this.author != null ? this.author.hashCode() : 0);
		hash = 37 * hash + (this.description != null ? this.description.hashCode() : 0);
		hash = 37 * hash + (this.processed ? 1 : 0);
		hash = 37 * hash + (this.creationDate != null ? this.creationDate.hashCode() : 0);
		hash = 37 * hash + (this.lastUpdateDate != null ? this.lastUpdateDate.hashCode() : 0);
		return hash;
	}
}
