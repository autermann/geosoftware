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
package org.sloth.web.action;

import org.sloth.model.Group;
import org.sloth.model.User;
/**
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 *
 */
public class UserEditFormAction {

	private String actualPassword;
	private User editingUser;
	private String newFamilyName;
	private Group newGroup;
	private String newMail;
	private String newName;
	private String newPassword;
	private String newPasswordHash;
	private String newPasswordRepeat;
	private User oldUser;

	public UserEditFormAction(User oldUser, User editingUser) {
		this.oldUser = oldUser;
		setEditingUser(editingUser);
		setNewFamilyName(oldUser.getFamilyName());
		setNewGroup(oldUser.getUserGroup());
		setNewName(oldUser.getName());
		setNewMail(oldUser.getMail());
	}

	/**
	 * @return the actualPassword
	 */
	public String getActualPassword() {
		return actualPassword;
	}

	/**
	 * @return the editingUser
	 */
	public User getEditingUser() {
		return editingUser;
	}

	public Long getId() {
		return getOldUser().getId();
	}

	public User getMergedUser() {
		User u = getOldUser();
		if (this.newPasswordHash != null) {
			u.setPassword(getNewPasswordHash());
		}
		if (this.newMail != null) {
			u.setMail(getNewMail());
		}
		if (this.newGroup != null) {
			u.setUserGroup(getNewGroup());
		}
		u.setFamilyName(this.newFamilyName);
		u.setName(this.newName);
		return u;
	}

	/**
	 * @return the newFamilyName
	 */
	public String getNewFamilyName() {
		return newFamilyName;
	}

	/**
	 * @return the newGroup
	 */
	public Group getNewGroup() {
		return newGroup;
	}

	/**
	 * @return the newMail
	 */
	public String getNewMail() {
		return newMail;
	}

	/**
	 * @return the newName
	 */
	public String getNewName() {
		return newName;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @return the newPasswordHash
	 */
	public String getNewPasswordHash() {
		return newPasswordHash;
	}

	/**
	 * @return the newPasswordRepeat
	 */
	public String getNewPasswordRepeat() {
		return newPasswordRepeat;
	}

	/**
	 * @return the oldUser
	 */
	public User getOldUser() {
		return oldUser;
	}

	/**
	 * @param actualPassword
	 *            the actualPassword to set
	 */
	public void setActualPassword(String actualPassword) {
		this.actualPassword = actualPassword;
	}

	/**
	 * @param editingUser
	 *            the editingUser to set
	 */
	public void setEditingUser(User editingUser) {
		this.editingUser = editingUser;
	}

	/**
	 * @param newFamilyName
	 *            the newFamilyName to set
	 */
	public void setNewFamilyName(String newFamilyName) {
		this.newFamilyName = newFamilyName;
	}

	/**
	 * @param newGroup
	 *            the newGroup to set
	 */
	public void setNewGroup(Group newGroup) {
		this.newGroup = newGroup;
	}

	/**
	 * @param newMail
	 *            the newMail to set
	 */
	public void setNewMail(String newMail) {
		this.newMail = newMail;
	}

	/**
	 * @param newName
	 *            the newName to set
	 */
	public void setNewName(String newName) {
		this.newName = newName;
	}

	/**
	 * @param newPassword
	 *            the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @param newPasswordHash
	 *            the newPasswordHash to set
	 */
	public void setNewPasswordHash(String newPasswordHash) {
		this.newPasswordHash = newPasswordHash;
	}

	/**
	 * @param newPasswordRepeat
	 *            the newPasswordRepeat to set
	 */
	public void setNewPasswordRepeat(String newPasswordRepeat) {
		this.newPasswordRepeat = newPasswordRepeat;
	}
}
