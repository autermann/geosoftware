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
package org.sloth.web;

import static org.sloth.util.ControllerUtils.forbiddenView;
import static org.sloth.util.ControllerUtils.getUser;
import static org.sloth.util.ControllerUtils.internalErrorView;
import static org.sloth.util.ControllerUtils.isAuth;
import static org.sloth.util.ValidationUtils.isNotEmptyOrWhitespace;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.Categorie;
import org.sloth.model.Observation;
import org.sloth.service.ObservationService;
import org.sloth.util.CategorieEditor;
import org.sloth.util.Config;
import org.sloth.validation.ObservationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/")
@SessionAttributes( { "observations", "observation", "categories" })
public class FrontPageController {

	private static final String VIEW = "index";
	private static final String MAP_CONTENT_ATTRIBUTE = "observations";
	private static final String NEW_OBSERVATION_ATTRIBUTE = "observation";
	private static final String CATEGORIE_ATTRIBUTE = "categories";
	private static final String SEARCH_PARAM = "q";
	private static final int VISIBLE_OBSERVATIONS_DEFAULT = 10;
	private static final int VISIBLE_OBSERVATIONS;
	private static final Logger logger = LoggerFactory
			.getLogger(FrontPageController.class);
	private ObservationService os;
	private ObservationValidator ov;

	static {
		Integer i = null;
		try {
			i = Integer.valueOf(Config.getProperty("lastObservationsCount"));
		} catch (NumberFormatException e) {
			logger.warn("Invalid or null value for property 'lastObservationsCount'.",e);
		}
		if (i == null) {
			VISIBLE_OBSERVATIONS = VISIBLE_OBSERVATIONS_DEFAULT;
		} else {
			VISIBLE_OBSERVATIONS = i;
		}
	}

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
		CategorieEditor c = new CategorieEditor();
		c.setObservationService(os);
		wdb.registerCustomEditor(Categorie.class, c);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView fillMap(HttpSession s,
			@RequestParam(value = SEARCH_PARAM, required = false) String q) {
		ModelAndView mav = new ModelAndView(VIEW);
		if (isAuth(s)) {
			mav.addObject(NEW_OBSERVATION_ATTRIBUTE, new Observation());
			mav.addObject(CATEGORIE_ATTRIBUTE, os.getCategories());
		}
		return mav.addObject(MAP_CONTENT_ATTRIBUTE,
				isNotEmptyOrWhitespace(q) ? this.os.getObservations(q) : this.os
						.getNewestObservations(VISIBLE_OBSERVATIONS));
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveObservation(HttpSession s, HttpServletResponse r,
			@ModelAttribute(NEW_OBSERVATION_ATTRIBUTE) Observation o,
			BindingResult result, SessionStatus status) throws IOException {
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
				return "redirect:/";
			}
		} else {
			return forbiddenView(r);
		}
	}
}
