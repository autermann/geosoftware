package org.sloth.validation;

import org.sloth.model.Report;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import static org.sloth.util.ValidationUtils.*;
import static org.sloth.validation.ErrorCodes.REPORT.*;

@Component
public class ReportValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Report.class);
	}

	@Override
	public void validate(Object t, Errors e) {
		rejectIfEmptyOrWhitespace(e, "title", EMPTY_TITLE);
		rejectIfEmptyOrWhitespace(e, "title", EMPTY_DESCRIPTION);
		rejectIfTooLong(e, "title", TOO_LONG_TITLE, 255);
		rejectIfTooLong(e, "description", TOO_LONG_DESCRIPTION, 100);
		rejectIfNull(e, "author", EMPTY_AUTHOR);
		rejectIfNull(e, "observation", EMPTY_OBSERVATION);
	}
}
