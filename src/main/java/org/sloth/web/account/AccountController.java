package org.sloth.web.account;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.sloth.web.util.ControllerUtils.*;

@Controller
public class AccountController {

	private final static String userView = "account/user";
	private final static String adminView = "account/admin";

	@RequestMapping("/acc")
	public String handle(HttpSession s,
						 HttpServletResponse r) throws IOException {
		if (!isAuth(s))
			return forbiddenView(r);
		if (isAdmin(s))
			return adminView;
		else
			return userView;

	}
}
