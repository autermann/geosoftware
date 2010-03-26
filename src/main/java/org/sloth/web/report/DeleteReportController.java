package org.sloth.web.report;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.Report;
import org.sloth.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import static org.sloth.web.util.ControllerUtils.*;

@Controller
@RequestMapping("/r/del/{id}")
public class DeleteReportController {

	private ObservationService observationService;
	private static final String view = "reports/details";
	private static final String attibute = "report";
	private static final Logger logger = LoggerFactory.getLogger(DeleteReportController.class);

	/**
	 * @param observationService the observationService to set
	 */
	@Autowired
	public void setObservationService(ObservationService observationService) {
		this.observationService = observationService;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleGet(@PathVariable Long id,
								  HttpSession s,
								  HttpServletResponse r) throws IOException {
		if (isAuth(s)) {
			Report report = observationService.getReport(id);
			if (report == null)
				return notFoundMAV(r);
			if (isAdmin(s) || isOwnReport(s, report))
				return new ModelAndView(view, attibute, report);
		}
		return forbiddenMAV(r);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String handlePost(@ModelAttribute Report report,
							 SessionStatus status,
							 HttpSession s,
							 HttpServletResponse r) throws IOException {
		status.setComplete();
		if (isAdmin(s) || isOwnReport(s, report))
			try {
				observationService.deleteReport(report);
				return "redirect:/r";
			} catch (Exception e) {
				logger.warn("Unexpected Exception", e);
				return internalErrorView(r);
			}
		return forbiddenView(r);
	}
}
