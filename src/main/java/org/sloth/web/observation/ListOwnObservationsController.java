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
import static org.sloth.web.util.ControllerUtils.*;

@Controller
@RequestMapping("/o/own")
public class ListOwnObservationsController {

	private final static String view = "observations/list";
	private final static String modelAttribute = "observations";
	private ObservationService observationService;

	@Autowired
	public void setobservationService(ObservationService observationService) {
		this.observationService = observationService;
	}

	@RequestMapping
	public ModelAndView setupList(HttpSession s, HttpServletResponse r)
			throws IOException {
		if (isAuth(s))
			return new ModelAndView(view, modelAttribute, observationService
					.getObservationsByUser(getUser(s)));
		else
			return forbiddenMAV(r);
	}
}
