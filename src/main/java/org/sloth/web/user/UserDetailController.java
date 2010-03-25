/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sloth.web.user;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.sloth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import static org.sloth.web.util.ControllerUtils.*;

@Controller
@RequestMapping("/u/{id}")
public class UserDetailController {

	private UserService userService;
	private static final String view = "users/details";
	private static final String modelAttribute = "users";

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping
	public ModelAndView handle(HttpSession s,
							   HttpServletResponse r) throws IOException {
		if (isAdmin(s))
			return new ModelAndView(view, modelAttribute, userService.getUsers());
		else
			return forbiddenMAV(r);
	}
}
