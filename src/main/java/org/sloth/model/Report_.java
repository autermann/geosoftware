package org.sloth.model;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Metamodel class for the {@link Entity} {@link Report}
 * 
 * @see Report
 * @author Christian Autermann
 */
@StaticMetamodel(Report.class)
public class Report_ {

	/**
	 * Metamodel-Attribute for {@link Report#user}
	 * 
	 * @see Report#getAuthor()
	 * @see Report#setAuthor(org.sloth.model.User)
	 */
	public static volatile SingularAttribute<Report, User> author;
	/**
	 * Metamodel-Attribute for {@link Report#description}
	 * 
	 * @see Report#getDescription()
	 * @see Report#setDescription(java.lang.String)
	 */
	public static volatile SingularAttribute<Report, String> description;
	/**
	 * Metamodel-Attribute for {@link Report#id}
	 * 
	 * @see Report#getId()
	 * @see Report#setId(long)
	 */
	public static volatile SingularAttribute<Report, Long> id;
	/**
	 * Metamodel-Attribute for {@link Report#observation}
	 * 
	 * @see Report#getObservation()
	 * @see Report#setObservation(org.sloth.model.Observation)
	 */
	public static volatile SingularAttribute<Report, Observation> observation;
	/**
	 * Metamodel-Attribute for {@link Report#processed}
	 * 
	 * @see Report#isProcessed()
	 * @see Report#setProcessed(boolean)
	 */
	public static volatile SingularAttribute<Report, Boolean> processed;
	/**
	 * Metamodel-Attribute for {@link Report#creationDate}
	 * 
	 * @see Report#getCreationDate()
	 * @see Report#setCreationDate(java.util.Date)
	 */
	public static volatile SingularAttribute<Report, Date> creationDate;
	/**
	 * Metamodel-Attribute for {@link Report#lastUpdateDate}
	 * 
	 * @see Report#getLastUpdateDate()
	 * @see Report#setLastUpdateDate(java.util.Date)
	 */
	public static volatile SingularAttribute<Report, Date> lastUpdateDate;
}
