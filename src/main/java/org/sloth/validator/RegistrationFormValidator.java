package org.sloth.validator;

import org.sloth.web.action.RegistrationFormAction;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import static org.sloth.util.ValidatorUtils.*;

@Component
public class RegistrationFormValidator extends AbstractUserActionValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(RegistrationFormAction.class);
	}

	@Override
	public void validate(Object t, Errors e) {
		RegistrationFormAction a = (RegistrationFormAction) t;
		rejectIfEmptyOrWhitespace(e, "familyName", "field.registration.familyName.empty");
		rejectIfEmptyOrWhitespace(e, "name", "field.registration.name.empty");
		rejectIfEmptyOrWhitespace(e, "passwordRepeat", "field.registration.passwordRepeat.empty");
		rejectIfEmptyOrWhitespace(e, "mailRepeat", "field.registration.mailRepeat.empty");
		rejectIfEmptyOrWhitespace(e, "password", "field.registration.password.empty");
		rejectIfEmptyOrWhitespace(e, "mail", "field.registration.mail.empty");
		rejectIfTooLong(e, "name", "field.registration.name.tooLong", 255);
		rejectIfTooLong(e, "familyName", "field.registration.familyName.tooLong", 255);
		rejectIfTooLong(e, "mail", "field.registration.mail.tooLong", 255);

		if (a.getPassword().equals(a.getPasswordRepeat())) {
			if (!passwordService.meetsRecommendation(a.getPassword())) {
				e.rejectValue("password", "field.registration.password.lowSecurity");
			}
		} else {
			e.rejectValue("passwordRepeat", "field.registration.passwordRepeat.wrong");
		}
		if (a.getMail().equals(a.getMailRepeat())) {
			if (!a.getMail().matches(REGEX)) {
				e.rejectValue("mail", "field.registration.mail.invalid");
				if (userService.get(a.getMail()) != null) {
					e.rejectValue("mail", "field.registration.mail.notUnique");
				}
			}
		} else {
			e.rejectValue("mailRepeat", "field.registration.mailRepeat.wrong");
		}
	}
}
