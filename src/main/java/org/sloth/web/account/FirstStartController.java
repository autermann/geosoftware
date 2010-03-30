/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sloth.web.account;

import org.sloth.web.action.RegistrationFormAction;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.User;
import org.sloth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import org.sloth.model.Group;
import org.sloth.validator.RegistrationFormValidator;
import static org.sloth.util.ControllerUtils.*;

@Controller
@RequestMapping("/firstStart")
@SessionAttributes(types = RegistrationFormAction.class)
public class FirstStartController {

	private static final String VIEW = "users/registration";
	private static final String USER_ATTRIBUTE = "user";
	private static final Logger logger = LoggerFactory.getLogger(
			RegistrationController.class);
	private UserService userService;
	private RegistrationFormValidator rfv;

	@Autowired
	public void setUserValidator(RegistrationFormValidator rfv) {
		this.rfv = rfv;
	}

	@Autowired
	public void setUserService(UserService us) {
		this.userService = us;
	}

	@InitBinder
	public void initBinder(WebDataBinder wdb) {
		wdb.setDisallowedFields("id", "creationDate", "userGroup");
	}

	@RequestMapping(method = GET)
	public ModelAndView prepare(HttpServletResponse r) throws IOException {
		if (isFirstStart()) {
			return new ModelAndView(VIEW, USER_ATTRIBUTE,
									new RegistrationFormAction());
		} else {
			return forbiddenMAV(r);
		}
	}

	@RequestMapping(method = POST)
	public String submit(
			@ModelAttribute(USER_ATTRIBUTE) RegistrationFormAction a,
			BindingResult result, SessionStatus status,
			HttpSession s, HttpServletResponse r) throws IOException {
		if (isFirstStart()) {
			rfv.validate(a, result);
			if (result.hasErrors()) {
				return VIEW;
			} else {
				try {
					User u = a.createUser();
					u.setUserGroup(Group.ADMIN);
					userService.registrate(u);
					auth(s, u);
				} catch(Exception e) {
					logger.warn("Unexpected Exception", e);
					return internalErrorView(r);
				} finally {
					status.setComplete();
				}
				return "redirect:/";
			}
		} else {
			return forbiddenView(r);
		}

	}

	private boolean isFirstStart() {
		return userService.getUsers().size() == 0;
	}

}
