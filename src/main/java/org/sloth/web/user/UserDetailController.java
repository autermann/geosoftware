/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sloth.web.user;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.sloth.model.User;
import org.sloth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import static org.sloth.util.ControllerUtils.*;

@Controller
@RequestMapping("/u/{id}")
public class UserDetailController {

	private static final String VIEW = "users/details";
	private static final String USERS_ATTRIBUTE = "users";
	private UserService us;

	@Autowired
	public void setUserService(UserService us) {
		this.us = us;
	}

	@RequestMapping
	public ModelAndView handleRequest(@PathVariable Long id, HttpSession s,
			HttpServletResponse r) throws IOException {

		if (isAdmin(s)) {
			User u = this.us.get(id);
			if (u == null) {
				return notFoundMAV(r);
			} else {
				return new ModelAndView(VIEW, USERS_ATTRIBUTE, u);
			}
		} else {
			return forbiddenMAV(r);
		}
	}
}
