package org.sloth.validator;

import org.sloth.model.Report;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import static org.sloth.util.ValidatorUtils.*;

@Component
public class ReportValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Report.class);
	}

	@Override
	public void validate(Object t, Errors e) {
		rejectIfEmptyOrWhitespace(e, "title", "field.report.title.empty");
		rejectIfEmptyOrWhitespace(e, "title", "field.report.description.empty");
		rejectIfTooLong(e, "title", "field.report.title.tooLong", 255);
		rejectIfTooLong(e, "description", "field.report.description.tooLong",
						100);
		rejectIfNull(e, "author", "field.report.author.empty");
		rejectIfNull(e, "observation", "field.report.observation.empty");
	}

}
