/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sloth.web.observation;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.sloth.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import static org.sloth.util.ControllerUtils.*;

@Controller
@RequestMapping("/o/own")
public class ListOwnObservationsController {

	private final static String VIEW = "observations/list";
	private final static String OBSERVATIONS_ATTRIBUTE = "observations";
	private ObservationService os;

	@Autowired
	public void setobservationService(ObservationService os) {
		this.os = os;
	}

	@RequestMapping
	public ModelAndView setupList(HttpSession s, HttpServletResponse r)
			throws IOException {
		if (isAuth(s)) {
			return new ModelAndView(VIEW, OBSERVATIONS_ATTRIBUTE, os.
					getObservationsByUser(getUser(s)));
		} else {
			return forbiddenMAV(r);
		}
	}

}
