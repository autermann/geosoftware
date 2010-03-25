package org.sloth.web.report;

import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.Report;
import org.sloth.service.ObservationService;
import org.sloth.service.validator.ReportValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import static org.sloth.web.util.ControllerUtils.*;

@Controller
@RequestMapping("/r/{id}")
@SessionAttributes(types = {Report.class, Boolean.class})
public class EditReportController {

	private ObservationService observationService;
	private ReportValidator reportValidator;
	private static final String view = "reports/form";
	private static final String attibuteR = "report";
	private static final String attibuteP = "processed";
	private static final Logger logger = LoggerFactory.getLogger(EditReportController.class);

	/**
	 * @param observationService the observationService to set
	 */
	@Autowired
	public void setObservationService(ObservationService observationService) {
		this.observationService = observationService;
	}

	/**
	 * @param reportValidator the reportValidator to set
	 */
	public void setReportValidator(ReportValidator reportValidator) {
		this.reportValidator = reportValidator;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("description", "processed");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleGet(@PathVariable Long id,
								  HttpSession s,
								  HttpServletResponse r) throws IOException {
		if (isAuth(s)) {
			Report report = observationService.getReport(id);
			if (report == null)
				return notFountMAV(r);



			if (isAdmin(s) || isOwnReport(s, report)) {
				ModelAndView mav = new ModelAndView(view);
				mav.addObject(attibuteP, new Boolean(report.isProcessed()));
				mav.addObject(attibuteR, report);
				return mav;
			}


		}
		return forbiddenMAV(r);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String handlePost(@PathVariable Long id,
							 @ModelAttribute Boolean processed,
							 @ModelAttribute Report report,
							 BindingResult result,
							 SessionStatus status,
							 HttpSession s,
							 HttpServletResponse r) throws IOException {
		if (isAuth(s)) {
			if (report == null)
				return notFoundView(r);
			if (!isAdmin(s)
				&& !processed.equals(Boolean.valueOf(report.isProcessed())))
				return forbiddenView(r);
			if (isAdmin(s) || isOwnReport(s, report)) {
				reportValidator.validate(report, result);
				if (result.hasErrors())
					return view;
				status.setComplete();
				try {
					report.setLastUpdateDate(new Date());
					observationService.updateReport(report);
					return "redirect:/r";
				} catch (Exception e) {
					logger.warn("Unexpected Exception", e);
					return internalErrorView(r);
				}
			}
		}
		return forbiddenView(r);
	}
}
