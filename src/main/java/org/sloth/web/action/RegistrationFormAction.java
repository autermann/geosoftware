package org.sloth.web.action;

import org.sloth.model.Group;
import org.sloth.model.User;

public class RegistrationFormAction {

	private String password;
	private String passwordRepeat;
	private String familyName;
	private String name;
	private String mail;
	private String mailRepeat;

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the passwordRepeat
	 */
	public String getPasswordRepeat() {
		return passwordRepeat;
	}

	/**
	 * @param passwordRepeat
	 *            the passwordRepeat to set
	 */
	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}

	/**
	 * @return the familyName
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * @param familyName
	 *            the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail
	 *            the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the mailRepeat
	 */
	public String getMailRepeat() {
		return mailRepeat;
	}

	/**
	 * @param mailRepeat
	 *            the mailRepeat to set
	 */
	public void setMailRepeat(String mailRepeat) {
		this.mailRepeat = mailRepeat;
	}

	public User createUser() {
		User u = new User();
		u.setFamilyName(getFamilyName());
		u.setMail(getMail());
		u.setPassword(getPassword());
		u.setName(getName());
		return u;
	}
}
