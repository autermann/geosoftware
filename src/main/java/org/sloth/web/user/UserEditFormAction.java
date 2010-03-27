package org.sloth.web.user;

import org.sloth.model.Group;
import org.sloth.model.User;

public class UserEditFormAction {

	private User oldUser;
	private String actualPassword;
	private String newPassword;
	private String newPasswordHash;
	private String newPasswordRepeat;
	private String newMail;
	private String newName;
	private String newFamilyName;
	private Group newGroup;

	public Long getId() {
		return getOldUser().getId();
	}

	public UserEditFormAction(User oldUser) {
		this.oldUser = oldUser;
		setNewFamilyName(oldUser.getFamilyName());
		setNewGroup(oldUser.getUserGroup());
		setNewName(oldUser.getName());
		setNewMail(oldUser.getMail());
	}

	/**
	 * @return the oldUser
	 */
	public User getOldUser() {
		return oldUser;
	}

	/**
	 * @return the actualPassword
	 */
	public String getActualPassword() {
		return actualPassword;
	}

	/**
	 * @param actualPassword
	 *            the actualPassword to set
	 */
	public void setActualPassword(String actualPassword) {
		this.actualPassword = actualPassword;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword
	 *            the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return the newPasswordRepeat
	 */
	public String getNewPasswordRepeat() {
		return newPasswordRepeat;
	}

	/**
	 * @param newPasswordRepeat
	 *            the newPasswordRepeat to set
	 */
	public void setNewPasswordRepeat(String newPasswordRepeat) {
		this.newPasswordRepeat = newPasswordRepeat;
	}

	/**
	 * @return the newMail
	 */
	public String getNewMail() {
		return newMail;
	}

	/**
	 * @param newMail
	 *            the newMail to set
	 */
	public void setNewMail(String newMail) {
		this.newMail = newMail;
	}

	/**
	 * @return the newName
	 */
	public String getNewName() {
		return newName;
	}

	/**
	 * @param newName
	 *            the newName to set
	 */
	public void setNewName(String newName) {
		this.newName = newName;
	}

	/**
	 * @return the newFamilyName
	 */
	public String getNewFamilyName() {
		return newFamilyName;
	}

	/**
	 * @param newFamilyName
	 *            the newFamilyName to set
	 */
	public void setNewFamilyName(String newFamilyName) {
		this.newFamilyName = newFamilyName;
	}

	/**
	 * @return the newGroup
	 */
	public Group getNewGroup() {
		return newGroup;
	}

	/**
	 * @param newGroup
	 *            the newGroup to set
	 */
	public void setNewGroup(Group newGroup) {
		this.newGroup = newGroup;
	}

	/**
	 * @return the newPasswordHash
	 */
	public String getNewPasswordHash() {
		return newPasswordHash;
	}

	/**
	 * @param newPasswordHash
	 *            the newPasswordHash to set
	 */
	public void setNewPasswordHash(String newPasswordHash) {
		this.newPasswordHash = newPasswordHash;
	}
}
