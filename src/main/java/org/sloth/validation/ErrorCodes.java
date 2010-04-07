package org.sloth.validation;

/**
 * Encapsulates error codes for validation.
 *
 * @author Christian Autermann
 */
public class ErrorCodes {

	/**
	 * {@code Categorie} specific error codes.
	 */
	public static final class CATEGORIE {

		/**
		 * Indicating that the entered title is {@code null} or empty.
		 */
		public static final String EMPTY_TITLE = "field.categorie.title.empty";
		/**
		 * Indicating that the entered title is too long.
		 */
		public static final String TOO_LONG_TITLE = "field.categorie.title.tooLong";
		/**
		 * Indicating that the entered title is not unique.
		 */
		public static final String NOT_UNIQUE_TITLE = "field.categorie.title.notUnique";
		/**
		 * Indicating that the entered description is {@code null} or empty.
		 */
		public static final String EMPTY_DESCRIPTION = "field.categorie.description.empty";
		/**
		 * Indicating that the entered description is too long.
		 */
		public static final String TOO_LONG_DESCRIPTION = "field.categorie.description.tooLong";
		/**
		 * Indicating that the entered icon filename is {@code null} or empty.
		 */
		public static final String EMPTY_ICON_FILE_NAME = "field.categorie.iconFileName.empty";
		/**
		 * Indicating that the entered icon filename is too long.
		 */
		public static final String TOO_LONG_ICON_FILE_NAME = "field.categorie.iconFileName.tooLong";
	}

	/**
	 * {@code LoginAction} specific error codes.
	 */
	public static final class LOGIN {

		/**
		 * Indicating that the credentials are invalid.
		 */
		public static final String INVALID = "field.login.invalid";
		/**
		 * Indicating that the entered mail address is {@code null} or empty.
		 */
		public static final String EMPTY_MAIL = "field.login.mail.empty";
		/**
		 * Indicating that the entered password is {@code null} or empty.
		 */
		public static final String EMPTY_PASSWORD = "field.login.password.empty";
	}

	/**
	 * {@code Observation} specific error codes.
	 */
	public static final class OBSERVATION {

		/**
		 * Indicating that the user is {@code null}.
		 */
		public static final String EMPTY_USER = "field.observation.user.empty";
		/**
		 * Indicating that the categorie is {@code null}.
		 */
		public static final String EMPTY_CATEGORIE = "field.observation.categorie.empty";
		/**
		 * Indicating that the entered title {@code null} or empty.
		 */
		public static final String EMPTY_TITLE = "field.observation.title.empty";
		/**
		 * Indicating that the entered title is too long.
		 */
		public static final String TOO_LONG_TITLE = "field.observation.title.tooLong";
		/**
		 * Indicating that the entered description {@code null} or empty.
		 */
		public static final String EMPTY_DESCRIPTION = "field.observation.description.empty";
		/**
		 * Indicating that the entered description  is too long.
		 */
		public static final String TOO_LONG_DESCRIPTION = "field.observation.description.tooLong";
	}

	/**
	 * {@code Coordinate} specific error codes.
	 */
	public static final class COORDINATE {

		/**
		 * Indicating that the entered longitude is {@code null} or empty.
		 */
		public static final String EMPTY_LONGITUDE = "field.coordinate.longitude.empty";
		/**
		 * Indicating that the entered longitude is invalid.
		 */
		public static final String INVALID_LONGITUDE = "field.coordinate.longitude.invalid";
		/**
		 * Indicating that the entered latitude {@code null} or empty.
		 */
		public static final String EMPTY_LATITUDE = "field.coordinate.latitude.empty";
		/**
		 * Indicating that the entered  latitude is invalid.
		 */
		public static final String INVALID_LATITUDE = "field.coordinate.latitude.invalid";
	}

	/**
	 * {@code Report} specific error codes.
	 */
	public static final class REPORT {

		/**
		 * Indicating that the observation is {@code null}.
		 */
		public static final String EMPTY_OBSERVATION = "field.report.observation.empty";
		/**
		 * Indicating that the author is {@code null}.
		 */
		public static final String EMPTY_AUTHOR = "field.report.author.empty";
		/**
		 * Indicating that the entered description is {@code null} or empty.
		 */
		public static final String EMPTY_DESCRIPTION = "field.report.description.empty";
		/**
		 * Indicating that the entered description is too long.
		 */
		public static final String TOO_LONG_DESCRIPTION = "field.report.description.tooLong";
	}

	/**
	 * {@code UserEditFormAction} specific error codes.
	 */
	public static final class USER_EDIT {

		/**
		 * Indicating that the entered actual password is {@code null} or empty.
		 */
		public static final String EMPTY_ACTUAL_PASSWORD = "field.useredit.actualpassword.empty";
		/**
		 * Indicating that the entered actual password is wrong.
		 */
		public static final String WRONG_ACTUAL_PASSWORD = "field.useredit.actualpassword.wrong";
		/**
		 * Indicating that the entered new password is {@code null} or empty.
		 */
		public static final String EMPTY_NEW_PASSWORD = "field.useredit.newPassword.empty";
		/**
		 * Indicating that the entered  new password repeat is {@code null} or empty.
		 */
		public static final String EMPTY_NEW_PASSWORD_REPEAT = "field.useredit.newPasswordRepeat.empty";
		/**
		 * Indicating that the entered new name is {@code null} or empty.
		 */
		public static final String EMPTY_NEW_NAME = "field.useredit.newName.empty";
		/**
		 * Indicating that the entered new name is too long.
		 */
		public static final String TOO_LONG_NEW_NAME = "field_useredit.newName.tooLong";
		/**
		 * Indicating that the entered new family name is {@code null} or empty.
		 */
		public static final String EMPTY_NEW_FAMILY_NAME = "field.useredit.newFamilyName.empty";
		/**
		 * Indicating that the entered new family name is too long.
		 */
		public static final String TOO_LONG_NEW_FAMILY_NAME = "field.useredit.newFamilyName.tooLong";
		/**
		 * Indicating that the entered new mail address is invalid.
		 */
		public static final String INVALID_NEW_MAIL = "field.useredit.newMail.invalid";
		/**
		 * Indicating that the entered new mail address is {@code null} or empty.
		 */
		public static final String EMPTY_NEW_MAIL = "field.useredit.newMail.empty";
		/**
		 * Indicating that the entered new mail address is not unique.
		 */
		public static final String NOT_UNIQUE_NEW_MAIL = "field.useredit.newMail.notUnique";
		/**
		 * Indicating that the entered new password repeat is wrong.
		 */
		public static final String WRONG_NEW_PASSWORD_REPEAT = "field.useredit.newPasswordRepeat.wrong";
		/**
		 * Indicating that the entered new password is not adequately secured.
		 */
		public static final String BAD_SECURED_NEW_PASSWORD = "field.useredit.newPassword.lowSecurity";
		/**
		 * Indicating that the editing user has not the appropiate rights to change the group.
		 */
		public static final String CAN_NOT_CHANGE_OWN_GROUP = "field.useredit.newGroup.canNotChangeOwnGroup";
	}

	/**
	 * {@code RegistrationFormAction} specific error codes.
	 */
	public static final class REGISTRATION {

		/**
		 * Indicating that the entered family name is too long.
		 */
		public static final String TOO_LONG_FAMILY_NAME = "field.registration.familyName.tooLong";
		/**
		 * Indicating that the entered name is too long.
		 */
		public static final String TOO_LONG_NAME = "field.registration.name.tooLong";
		/**
		 * Indicating that the entered name is {@code null} or empty.
		 */
		public static final String EMPTY_NAME = "field.registration.name.empty";
		/**
		 * Indicating that the entered family name is {@code null} or empty.
		 */
		public static final String EMPTY_FAMILY_NAME = "field.registration.familyName.empty";
		/**
		 * Indicating that the entered mail address is {@code null} or empty.
		 */
		public static final String EMPTY_MAIL = "field.registration.mail.empty";
		/**
		 * Indicating that the entered mail address is invalid.
		 */
		public static final String INVALID_MAIL = "field.registration.mail.invalid";
		/**
		 * Indicating that the entered mail address is not unique.
		 */
		public static final String NOT_UNIQUE_MAIL = "field.registration.mail.notUnique";
		/**
		 * Indicating that the entered mail address is too long.
		 */
		public static final String TOO_LONG_MAIL = "field.registration.mail.tooLong";
		/**
		 * Indicating that the entered mail address repetition is {@code null} or empty.
		 */
		public static final String EMPTY_MAIL_REPEAT = "field.registration.mailRepeat.empty";
		/**
		 * Indicating that the entered mail address repetition is wrong.
		 */
		public static final String WRONG_MAIL_REPEAT = "field.registration.mailRepeat.wrong";
		/**
		 * Indicating that the entered password is {@code null} or empty.
		 */
		public static final String EMPTY_PASSWORD = "field.registration.password.empty";
		/**
		 * Indicating that the entered password is not adequately secured.
		 */
		public static final String LOW_SECURED_PASSWORD = "field.registration.password.lowSecurity";
		/**
		 * Indicating that the entered passwordrepetition is {@code null} or empty.
		 */
		public static final String EMPTY_PASSWORD_REPEAT = "field.registration.passwordRepeat.empty";
		/**
		 * Indicating that the entered password repetition is wrong.
		 */
		public static final String WRONG_PASSWORD_REPEAT = "field.registration.passwordRepeat.wrong";
	}
}
