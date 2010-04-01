package org.sloth.validation;

import org.sloth.service.PasswordService;
import org.sloth.service.UserService;
import org.sloth.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import static org.sloth.util.ValidationUtils.*;

public abstract class AbstractUserActionValidator implements Validator {

	protected static final String REGEX;
	protected PasswordService passwordService;
	protected UserService userService;

	static {
		String regex = Config.getProperty("mail.regex");
		if (notNullAndNotEmpty(regex)) {
			REGEX = regex;
		} else {
			REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
		}
	}

	@Autowired
	public void setPasswordManager(PasswordService passwordManager) {
		this.passwordService = passwordManager;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
