package org.sloth.web.user;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.sloth.util.ControllerUtils.*;

@Controller
public class SelfRedirectController {

	@RequestMapping("/u/edit")
	public String handleEditRequest(HttpSession s, HttpServletResponse r)
			throws IOException {
		if (isAuth(s))
			return "redirect:/u/edit/" + getUser(s).getId();
		else
			return forbiddenView(r);
	}

	@RequestMapping("/u/del")
	public String handleDeleteRequest(HttpSession s, HttpServletResponse r)
			throws IOException {
		if (isAuth(s))
			return "redirect:/u/del/" + getUser(s).getId();
		else
			return forbiddenView(r);
	}
}
