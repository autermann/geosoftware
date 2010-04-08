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
import static org.sloth.util.ControllerUtils.internalErrorView;
import static org.sloth.util.ControllerUtils.isAdmin;
import static org.sloth.util.ControllerUtils.isAuth;
import static org.sloth.util.ControllerUtils.isOwnReport;
import static org.sloth.util.ControllerUtils.notFoundMAV;
import static org.sloth.util.ControllerUtils.notFoundView;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to delete a {@code Report}.
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
@Controller
@RequestMapping("/r/del/{id}")
public class DeleteReportController {

	private static final String VIEW = "reports/details";
	private static final String REPORT_ATTRIBUTE = "report";
	private static final Logger logger = LoggerFactory
			.getLogger(DeleteReportController.class);
	private ObservationService observationService;

	/**
	 * @param observationService
	 *            the {@code ObservationService} to set
	 */
	@Autowired
	public void setObservationService(ObservationService observationService) {
		this.observationService = observationService;
	}

	/**
	 * Sets custom parameters to the {@code WebDataBinder}.
	 * 
	 * @param webDataBinder
	 *            the {@code WebDataBinder} to initialize
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.setAllowedFields();
	}

	/**
	 * Handles the {@code GET} request and sets up the form.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleGet(@PathVariable Long id, HttpSession session,
			HttpServletResponse response) throws IOException {
		if (isAuth(session)) {
			Report report = this.observationService.getReport(id);
			if (report == null) {
				return notFoundMAV(response);
			}
			if (isAdmin(session) || isOwnReport(session, report)) {
				return new ModelAndView(VIEW, REPORT_ATTRIBUTE, report);
			}
		}
		return forbiddenMAV(response);
	}

	/**
	 * Handles the {@code POST} request and deletes the observation.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String handlePost(@PathVariable Long id, SessionStatus status,
			HttpSession session, HttpServletResponse response) throws IOException {
		status.setComplete();
		Report report = this.observationService.getReport(id);
		if (report == null) {
			return notFoundView(response);
		} else {
			if (isAdmin(session) || isOwnReport(session, report)) {
				try {
					this.observationService.deleteReport(report);
				} catch (Exception e) {
					logger.warn("Unexpected Exception", e);
					return internalErrorView(response);
				} finally {
					status.setComplete();
				}
				return "redirect:/r";
			} else {
				return forbiddenView(response);
			}
		}
	}
}
