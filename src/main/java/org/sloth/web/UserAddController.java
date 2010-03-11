package org.sloth.web;

import org.sloth.model.User;
import org.sloth.service.UserManager;
import org.sloth.service.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/users/new")
@SessionAttributes(types = User.class)
public class UserAddController {

	@Autowired
	private UserManager userManager;
	@Autowired
	private UserValidator userValidator;

	public void setUserValidator(UserValidator userValidator) {
		this.userValidator = userValidator;
	}

	protected UserValidator getUserValidator() {
		return this.userValidator;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	protected UserManager getUserManager() {
		return this.userManager;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id", "creationDate", "userRight");
	}


	/*
	 * Beim Aufruf der Seite wird die GET-Methoder aufgerufen...
	 */
	@RequestMapping(method = GET)
	public String setupForm(Model model) {
		User user = new User();
		model.addAttribute(user);
		return "users/form";
	}

	/*
	 * Beim Absenden des Formulars die POST-Methode...
	 */
	@RequestMapping(method = POST)
	public String processSubmit(@ModelAttribute User user, BindingResult result,
								SessionStatus status) {
		getUserValidator().validate(user, result);
		if (result.hasErrors()) {
			return "users/form";
		} else {
			this.userManager.registrateUser(user);
			status.setComplete();
			return "redirect:/";
		}
	}

}
