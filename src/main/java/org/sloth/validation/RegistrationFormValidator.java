package org.sloth.validation;

import org.sloth.web.action.RegistrationFormAction;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import static org.sloth.util.ValidationUtils.*;

@Component
public class RegistrationFormValidator extends AbstractUserActionValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(RegistrationFormAction.class);
	}

	@Override
	public void validate(Object t, Errors e) {
		rejectIfEmptyOrWhitespace(e, "familyName", "field.registration.familyName.empty");
		rejectIfEmptyOrWhitespace(e, "name", "field.registration.name.empty");
		rejectIfEmptyOrWhitespace(e, "passwordRepeat", "field.registration.passwordRepeat.empty");
		rejectIfEmptyOrWhitespace(e, "mailRepeat", "field.registration.mailRepeat.empty");
		rejectIfEmptyOrWhitespace(e, "password", "field.registration.password.empty");
		rejectIfEmptyOrWhitespace(e, "mail", "field.registration.mail.empty");
		rejectIfTooLong(e, "name", "field.registration.name.tooLong", 255);
		rejectIfTooLong(e, "familyName", "field.registration.familyName.tooLong", 255);
		rejectIfTooLong(e, "mail", "field.registration.mail.tooLong", 255);
		RegistrationFormAction a = (RegistrationFormAction) t;
		String password = a.getPassword(), mail = a.getMail();
		if (!password.equals(a.getPasswordRepeat())) {
			e.rejectValue("passwordRepeat", "field.registration.passwordRepeat.wrong");
		} else if (!this.passwordService.meetsRecommendation(password)) {
			e.rejectValue("password", "field.registration.password.lowSecurity");
		}
		if (!mail.equals(a.getMailRepeat())) {
			e.rejectValue("mailRepeat", "field.registration.mailRepeat.wrong");
		} else if (!mail.matches(REGEX)) {
			e.rejectValue("mail", "field.registration.mail.invalid");
		} else if (!this.userService.isMailAddressAvailable(mail)) {
			e.rejectValue("mail", "field.registration.mail.notUnique");
		}
	}
}
