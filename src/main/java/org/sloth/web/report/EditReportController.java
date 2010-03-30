package org.sloth.web.report;

import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import static org.sloth.util.ControllerUtils.*;

@Controller
@RequestMapping("/r/edit/{id}")
@SessionAttributes(types = {Report.class, Boolean.class})
public class EditReportController {

	private ObservationService os;
	private ReportValidator rv;
	private static final String VIEW = "reports/form";
	private static final String REPORT_ATTRIBUTE = "report";
	private static final String PROCESSED_ATTRIBUTE = "processed";
	private static final Logger logger = LoggerFactory.getLogger(
			EditReportController.class);

	/**
	 * @param os
	 *            the observationService to set
	 */
	@Autowired
	public void setObservationService(ObservationService os) {
		this.os = os;
	}

	/**
	 * @param rv
	 *            the reportValidator to set
	 */
	@Autowired
	public void setReportValidator(ReportValidator rv) {
		this.rv = rv;
	}

	@InitBinder
	public void initBinder(WebDataBinder wdb) {
		wdb.setAllowedFields("description", "processed");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleGet(@PathVariable Long id, HttpSession s,
								  HttpServletResponse r) throws IOException {
		if (isAuth(s)) {
			Report report = os.getReport(id);
			if (report == null) {
				return notFoundMAV(r);
			}

			if (isAdmin(s) || isOwnReport(s, report)) {
				ModelAndView mav = new ModelAndView(VIEW);
				mav.addObject(PROCESSED_ATTRIBUTE, new Boolean(report.
						isProcessed()));
				mav.addObject(REPORT_ATTRIBUTE, report);
				return mav;
			}

		}
		return forbiddenMAV(r);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String handlePost(@PathVariable Long id,
							 @ModelAttribute(PROCESSED_ATTRIBUTE) Boolean p,
							 @ModelAttribute(REPORT_ATTRIBUTE) Report report,
							 BindingResult result,
							 SessionStatus status, HttpSession s,
							 HttpServletResponse resp)
			throws IOException {
		if (isAuth(s)) {
			if (report == null) {
				return notFoundView(resp);
			} else if (!isAdmin(s) && !p.equals(Boolean.valueOf(report.
					isProcessed()))) {
				return forbiddenView(resp);
			} else if (isAdmin(s) || isOwnReport(s, report)) {
				rv.validate(report, result);
				if (result.hasErrors()) {
					return VIEW;
				}
				status.setComplete();
				try {
					report.setLastUpdateDate(new Date());
					os.updateReport(report);
				} catch(Exception e) {
					logger.warn("Unexpected Exception", e);
					return internalErrorView(resp);
				} finally {
					status.setComplete();
				}
				return "redirect:/r";
			}
		}
		return forbiddenView(resp);
	}

}
