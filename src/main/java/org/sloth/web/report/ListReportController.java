package org.sloth.web.report;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.sloth.model.Observation;
import org.sloth.model.User;
import org.sloth.service.ObservationService;
import org.sloth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import static org.sloth.util.ControllerUtils.*;

@Controller
public class ListReportController {

	private static final String VIEW = "reports/list";
	private static final String REPORTS_ATTRIBUTE = "reports";
	private ObservationService observationService;
	private UserService userService;

	/**
	 * @param observationService
	 *            the observationService to set
	 */
	@Autowired
	public void setObservationService(ObservationService observationService) {
		this.observationService = observationService;
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/r")
	public ModelAndView handleAllAndOwn(HttpSession s, HttpServletResponse r)
			throws IOException {
		if (isAuth(s))
			if (isAdmin(s))
				return new ModelAndView(VIEW, REPORTS_ATTRIBUTE, observationService
						.getReports());
			else
				return new ModelAndView("redirect:/r/u/" + getUser(s).getId());
		return forbiddenMAV(r);
	}

	@RequestMapping("/r/o/{id}")
	public ModelAndView handleObservationFilter(@PathVariable Long id,
			HttpSession s, HttpServletResponse r) throws IOException {
		if (isAdmin(s)) {
			Observation o = observationService.getObservation(id);
			if (o == null)
				return notFoundMAV(r);
			return new ModelAndView(VIEW, REPORTS_ATTRIBUTE, observationService
					.getReportsByObservation(o));
		}
		return forbiddenMAV(r);
	}

	@RequestMapping("/r/u/{id}")
	public ModelAndView handleUserFilter(@PathVariable Long id, HttpSession s,
			HttpServletResponse r) throws IOException {
		if (isSameId(s, id))
			return new ModelAndView(VIEW, REPORTS_ATTRIBUTE, observationService
					.getObservationsByUser(getUser(s)));
		if (isAdmin(s)) {
			User u = userService.get(id);
			if (u == null)
				return notFoundMAV(r);
			return new ModelAndView(VIEW, REPORTS_ATTRIBUTE, observationService
					.getReportsByUser(u));
		}
		return forbiddenMAV(r);
	}
}
