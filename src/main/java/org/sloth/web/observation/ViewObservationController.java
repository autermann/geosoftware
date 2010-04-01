package org.sloth.web.observation;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.sloth.model.Observation;
import org.sloth.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.SessionAttributes;
import static org.sloth.util.ControllerUtils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/o/{id}")
@SessionAttributes(types = Observation.class)
public class ViewObservationController {

	private static final String VIEW = "observations/details";
	private static final String OBSERVATIONS_ATTRIBUTE = "observation";
	protected static final Logger logger = LoggerFactory.getLogger(
			EditObservationController.class);
	private ObservationService os;

	@Autowired
	public void setObservationService(ObservationService os) {
		this.os = os;
	}

	@RequestMapping(method = GET)
	public ModelAndView setupForm(@PathVariable Long id, HttpSession s,
			HttpServletResponse r) throws IOException {
		if (isAuth(s)) {
			Observation o = this.os.getObservation(id);
			if (o == null) {
				return notFoundMAV(r);
			} else if (isAdmin(s) || isOwnObservation(s, o)) {
				ModelAndView mav = new ModelAndView(VIEW);
				mav.addObject(OBSERVATIONS_ATTRIBUTE, o);
				return mav;
			}
		}
		return forbiddenMAV(r);
	}
}
