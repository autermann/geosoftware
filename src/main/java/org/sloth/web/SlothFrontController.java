package org.sloth.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SlothFrontController {

	@RequestMapping
	public String doWelcome(){
		return "welcome";
	}
}
