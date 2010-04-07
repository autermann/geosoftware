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

import static org.sloth.util.ControllerUtils.forbiddenMAV;
import static org.sloth.util.ControllerUtils.forbiddenView;
import static org.sloth.util.ControllerUtils.getUser;
import static org.sloth.util.ControllerUtils.internalErrorView;
import static org.sloth.util.ControllerUtils.isAuth;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.Categorie;
import org.sloth.model.Observation;
import org.sloth.service.ObservationService;
import org.sloth.util.CategorieEditor;
import org.sloth.validation.ObservationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
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
@RequestMapping("/o/new")
@SessionAttributes(types = Observation.class)
@Deprecated
public class ObservationAddController {

	private static final String VIEW = "observations/form";
	private static final String OBSERVATION_ATTRIBUTE = "observation";
	private static final String CATEGORIES_ATTRIBUTE = "categories";
	private Logger logger = LoggerFactory
			.getLogger(ObservationAddController.class);
	private ObservationService os;
	private ObservationValidator ov;

	@Autowired
	public void setObservationValidator(ObservationValidator ov) {
		this.ov = ov;
	}

	@Autowired
	public void setObservationService(ObservationService os) {
		this.os = os;
	}

	@InitBinder
	public void initBinder(WebDataBinder wdb) {
		wdb.setDisallowedFields("id", "user", "creationDate");
		CategorieEditor c = new CategorieEditor();
		c.setObservationService(this.os);
		wdb.registerCustomEditor(Categorie.class, c);
	}

	@RequestMapping(method = GET)
	public ModelAndView setupForm(HttpSession s, HttpServletResponse r)
			throws IOException {
		if (isAuth(s)) {
			ModelAndView mav = new ModelAndView(VIEW);
			mav.addObject(OBSERVATION_ATTRIBUTE, new Observation());
			mav.addObject(CATEGORIES_ATTRIBUTE, os.getCategories());
			return mav;
		} else {
			return forbiddenMAV(r);
		}
	}

	@RequestMapping(method = POST)
	public String processSubmit(
			@ModelAttribute(OBSERVATION_ATTRIBUTE) Observation o,
			BindingResult result, SessionStatus status, HttpSession s,
			HttpServletResponse r) throws IOException {
		if (isAuth(s)) {
			o.setUser(getUser(s));
			this.ov.validate(o, result);
			if (result.hasErrors()) {
				return VIEW;
			} else {
				try {
					this.os.registrate(o);
				} catch (Exception e) {
					logger.warn("Unexpected Exception", e);
					return internalErrorView(r);
				} finally {
					status.setComplete();
				}
				return "redirect:/admin/observations";
			}
		} else {
			return forbiddenView(r);
		}
	}

}
