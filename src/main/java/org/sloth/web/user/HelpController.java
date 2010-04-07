package org.sloth.web.user;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelpController {

    @RequestMapping("/help")
    public String setupForm()
            throws IOException {
        return "/support/support";
    }
}




