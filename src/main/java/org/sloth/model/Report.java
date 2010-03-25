/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sloth.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.sloth.exceptions.ConstraintViolationException;
import org.sloth.exceptions.FieldLengthConstraintViolationException;
import org.sloth.exceptions.NotNullConstraintViolationException;

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

	@Override
	public void validate() throws ConstraintViolationException {
		if (getAuthor() == null || getObservation() == null
			|| getDescription() == null || getDescription().trim().isEmpty())
			throw new NotNullConstraintViolationException();
		if (getDescription().length() > 1000)
			throw new FieldLengthConstraintViolationException();
	}

	/**
	 * @return the observation
	 */
	public Observation getObservation() {
		return observation;
	}

	/**
	 * @param observation the observation to set
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
	 * @param author the author to set
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
	 * @param description the description to set
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
	 * @param processed the processed to set
	 */
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
}
