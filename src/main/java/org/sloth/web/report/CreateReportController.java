/*
 * Copyright (C) 2009-2010  Stefan Arndt, Christian Autermann, Dustin Demuth,
 *                  Christoph Fendrich, Simon Ottenhues, Christian Paluschek
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sloth.web.report;

import static org.sloth.util.ControllerUtils.forbiddenMAV;
import static org.sloth.util.ControllerUtils.forbiddenView;
import static org.sloth.util.ControllerUtils.getUser;
import static org.sloth.util.ControllerUtils.internalErrorView;
import static org.sloth.util.ControllerUtils.isAuth;
import static org.sloth.util.ControllerUtils.notFoundMAV;
import static org.sloth.util.ControllerUtils.notFoundView;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.Observation;
import org.sloth.model.Report;
import org.sloth.service.ObservationService;
import org.sloth.validation.ReportValidator;
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

/**
 * Controller to create a new {@code Report}.
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 * 
 */
@Controller
@RequestMapping("/r/o/{id}/new")
public class CreateReportController {

	private static final String VIEW = "reports/form";
	private static final String REPORT_ATTRIBUTE = "report";
	private static final Logger logger = LoggerFactory
			.getLogger(CreateReportController.class);
	private ObservationService observationService;
	private ReportValidator reportValidator;

	/**
	 * @param observationService
	 *            the {@code ObservationService} to set
	 */
	@Autowired
	public void setObservationService(ObservationService observationService) {
		this.observationService = observationService;
	}

	/**
	 * @param reportValidator
	 *            the {@code ReportValidator} to set
	 */
	@Autowired
	public void setReportValidator(ReportValidator reportValidator) {
		this.reportValidator = reportValidator;
	}

	/**
	 * Sets custom parameters to the {@code WebDataBinder}.
	 * 
	 * @param webDataBinder
	 *            the {@code WebDataBinder} to initialize
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.setAllowedFields("description");
	}

	/**
	 * Handles the {@code GET} request and sets up the form.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleGet(@PathVariable Long id, HttpSession session,
			HttpServletResponse response) throws IOException {
		if (isAuth(session)) {
			Observation o = this.observationService.getObservation(id);
			if (o == null) {
				return notFoundMAV(response);
			}
			return new ModelAndView(VIEW, REPORT_ATTRIBUTE, new Report());
		}
		return forbiddenMAV(response);
	}

	/**
	 * Handles the {@code POST} request and saves the new {@code Report}.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String handlePost(@ModelAttribute Report report,
			BindingResult result, @PathVariable Long id, SessionStatus status,
			HttpSession session, HttpServletResponse response)
			throws IOException {
		if (isAuth(session)) {
			try {
				Observation o = this.observationService.getObservation(id);
				if (o == null) {
					return notFoundView(response);
				} else {
					report.setObservation(o);
					report.setAuthor(getUser(session));
					this.reportValidator.validate(report, result);
					if (result.hasErrors()) {
						return VIEW;
					} else {
						status.setComplete();
						this.observationService.registrate(report);
						return "redirect:/";
					}
				}
			} catch (Exception e) {
				logger.warn("Unexpected Exception", e);
				return internalErrorView(response);
			}
		}
		return forbiddenView(response);
	}
}
