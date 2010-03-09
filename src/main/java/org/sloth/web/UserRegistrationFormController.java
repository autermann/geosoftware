package org.sloth.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class UserRegistrationFormController extends SimpleFormController {

	public UserRegistrationFormController() {
	//	this.set
	}
/*
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) {

	UserRegistrationCommand userRegistrationCommand = (UserRegistrationCommand) command;

	return new ModelAndView(getFormView(), errors.getModel());



	}

	public Object formBackingObject(HttpServletRequest request, Object command) {
	logger.info("Entering into Form Backing Object");
	UserRegistrationCommand userRegistrationCommand = (UserRegistrationCommand) command;
	logger.info("Exiting Form Backing Object");
	return userRegistrationCommand;

	 */
}
