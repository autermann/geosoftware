package org.sloth.validation;

import org.sloth.web.action.RegistrationFormAction;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import static org.sloth.util.ValidationUtils.*;
import static org.sloth.validation.ErrorCodes.REGISTRATION.*;

@Component
public class RegistrationFormValidator extends AbstractUserActionValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(RegistrationFormAction.class);
	}

	@Override
	public void validate(Object t, Errors e) {
		rejectIfEmptyOrWhitespace(e, "familyName", EMPTY_FAMILY_NAME);
		rejectIfEmptyOrWhitespace(e, "name", EMPTY_NAME);
		rejectIfEmptyOrWhitespace(e, "passwordRepeat", EMPTY_PASSWORD_REPEAT);
		rejectIfEmptyOrWhitespace(e, "mailRepeat", EMPTY_MAIL_REPEAT);
		rejectIfEmptyOrWhitespace(e, "password", EMPTY_PASSWORD);
		rejectIfEmptyOrWhitespace(e, "mail", EMPTY_MAIL);
		rejectIfTooLong(e, "name", TOO_LONG_NAME, 255);
		rejectIfTooLong(e, "familyName", TOO_LONG_FAMILY_NAME, 255);
		rejectIfTooLong(e, "mail", TOO_LONG_MAIL, 255);
		RegistrationFormAction a = (RegistrationFormAction) t;
		String password = a.getPassword(), mail = a.getMail();
		if (!password.equals(a.getPasswordRepeat())) {
			e.rejectValue("passwordRepeat", WRONG_PASSWORD_REPEAT);
		} else if (!this.passwordService.meetsRecommendation(password)) {
			e.rejectValue("password", LOW_SECURED_PASSWORD);
		}
		if (!mail.equals(a.getMailRepeat())) {
			e.rejectValue("mailRepeat", WRONG_MAIL_REPEAT);
		} else if (!mail.matches(REGEX)) {
			e.rejectValue("mail", INVALID_MAIL);
		} else if (!this.userService.isMailAddressAvailable(mail)) {
			e.rejectValue("mail", NOT_UNIQUE_MAIL);
		}
	}
}
