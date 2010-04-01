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
package org.sloth.web.categorie;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.Categorie;
import org.sloth.service.ObservationService;
import org.sloth.validation.CategorieValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import static org.sloth.util.ControllerUtils.*;

@Controller
@RequestMapping("/c/edit/{id}")
@SessionAttributes(types = Categorie.class)
public class EditCategorieController {

	private static final String VIEW = "categories/edit";
	private static final String CATEGORIE_ATTRIBUTE = "categorie";
	private static final Logger logger = LoggerFactory.getLogger(
			EditCategorieController.class);
	private ObservationService os;
	private CategorieValidator cv;

	@Autowired
	public void setCategorieValidator(CategorieValidator cv) {
		this.cv = cv;
	}

	@Autowired
	public void setObservationService(ObservationService os) {
		this.os = os;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder wdb) {
		wdb.setAllowedFields("title", "description", "iconFileName");
	}

	@RequestMapping(method = GET)
	public ModelAndView setupForm(@PathVariable Long id, HttpSession s,
								  HttpServletResponse r) throws IOException {
		if (isAdmin(s)) {
			Categorie c = os.getCategorie(id);
			if (c == null) {
				return notFoundMAV(r);
			} else {
				return new ModelAndView(VIEW, CATEGORIE_ATTRIBUTE, c);
			}
		} else {
			return forbiddenMAV(r);
		}

	}

	@RequestMapping(method = POST)
	public String processSubmit(
			@ModelAttribute(CATEGORIE_ATTRIBUTE) Categorie c,
			BindingResult result, SessionStatus status, HttpSession s,
			HttpServletResponse r) throws IOException {
		if (isAdmin(s)) {
			cv.validate(c, result);
			if (result.hasErrors()) {
				return VIEW;
			} else {
				try {
					os.updateCategorie(c);
				} catch(Exception e) {
					logger.warn("Unexpected Exception", e);
					return internalErrorView(r);
				} finally {
					status.setComplete();
				}
				return "redirect:/c";
			}
		} else {
			return forbiddenView(r);
		}
	}

}
