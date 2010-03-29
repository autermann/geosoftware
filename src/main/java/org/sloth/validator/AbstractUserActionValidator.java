package org.sloth.validator;

import org.sloth.service.PasswordService;
import org.sloth.service.UserService;
import org.sloth.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import static org.sloth.util.ValidatorUtils.*;
import static org.springframework.util.StringUtils.*;

public abstract class AbstractUserActionValidator implements Validator {

	protected PasswordService passwordService;
	protected UserService userService;
	protected static final String REGEX;

	static {
		String regex = Config.getProperty("mail.regex");
		if (notNull(regex) && hasText(regex)) {
			REGEX = regex;
		} else {
			REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
		}
	}

	@Autowired
	public void setPasswordService(PasswordService passwordService) {
		this.passwordService = passwordService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
