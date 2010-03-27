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
package org.sloth.web.observation;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.sloth.model.Observation;
import org.sloth.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import static org.sloth.web.util.ControllerUtils.*;

@Controller
@RequestMapping("/o/del/{id}")
@SessionAttributes(types = Observation.class)
public class DeleteObservationController {

	private static final String view = "observations/delete";
	private static final String modelAttribute = "observation";
	protected static final Logger logger = LoggerFactory.getLogger(DeleteObservationController.class);
	private ObservationService observationService;

	@Autowired
	public void setObservationService(ObservationService observationService) {
		this.observationService = observationService;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields();
	}

	@RequestMapping(method = GET)
	public ModelAndView setupForm(@PathVariable Long id,
								  HttpSession s,
								  HttpServletResponse r) throws IOException {
		if (isAuth(s)) {
			Observation o = observationService.getObservation(id);
			if (o == null)
				return notFoundMAV(r);
			if (isAdmin(s) || isOwnObservation(s, o))
				return new ModelAndView(view, modelAttribute, o);
		}
		return forbiddenMAV(r);
	}

	@RequestMapping(method = POST)
	public String processSubmit(@PathVariable Long id,
								HttpSession s,
								HttpServletResponse r) throws IOException {
		if (isAuth(s)) {
			Observation o = observationService.getObservation(id);
			if (o == null)
				return notFoundView(r);
			if (isAdmin(s) || isOwnObservation(s, o))
				try {
					observationService.deleteObservation(o);
					return "redirect:/o";
				} catch (Exception e) {
					logger.warn("Unexpected Exception.", e);
					return internalErrorView(r);
				}
		}
		return forbiddenView(r);
	}
}
