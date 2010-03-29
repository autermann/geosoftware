package org.sloth.web.report;

import java.io.IOException;
import javax.naming.Binding;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.Observation;
import org.sloth.model.Report;
import org.sloth.service.ObservationService;
import org.sloth.validator.ReportValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import static org.sloth.util.ControllerUtils.*;

@Controller
@RequestMapping("/r/o/{id}/new")
public class CreateReportController {

	private static final String VIEW = "reports/form";
	private static final String REPORT_ATTRIBUTE = "report";
	private static final Logger logger = LoggerFactory.getLogger(
			CreateReportController.class);
	private ObservationService observationService;
	private ReportValidator reportValidator;

	@Autowired
	public void setObservationService(ObservationService observationService) {
		this.observationService = observationService;
	}

	@Autowired
	public void setReportValidator(ReportValidator reportValidator) {
		this.reportValidator = reportValidator;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("description");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleGet(@PathVariable Long id, HttpSession s,
								  HttpServletResponse r) throws IOException {
		if (isAuth(s)) {
			Observation o = observationService.getObservation(id);
			if (o == null) {
				return notFoundMAV(r);
			}
			return new ModelAndView(VIEW, REPORT_ATTRIBUTE, new Report());
		}
		return forbiddenMAV(r);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String handlePost(@ModelAttribute Report report,
							 BindingResult result, @PathVariable Long id,
							 SessionStatus status,
							 HttpSession s, HttpServletResponse r) throws
			IOException {
		if (isAuth(s)) {
			try {

				Observation o = observationService.getObservation(id);
				if (o == null) {
					return notFoundView(r);
				}
				report.setObservation(o);
				report.setAuthor(getUser(s));
				reportValidator.validate(report, result);
				if (result.hasErrors()) {
					return VIEW;
				}
				status.setComplete();
				observationService.registrate(report);
				return "redirect:/";
			} catch(Exception e) {
				logger.warn("Unexpected Exception", e);
				return internalErrorView(r);
			}
		}
		return forbiddenView(r);
	}

}
