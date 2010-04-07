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

import static org.sloth.util.ControllerUtils.forbiddenMAV;
import static org.sloth.util.ControllerUtils.forbiddenView;
import static org.sloth.util.ControllerUtils.internalErrorView;
import static org.sloth.util.ControllerUtils.isAdmin;
import static org.sloth.util.ControllerUtils.notFoundMAV;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.Categorie;
import org.sloth.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/c/del/{id}")
public class DeleteCategorieController {

	private static final String CATEGORIE_ATTRIBUTE = "categorie";
	private static final String VIEW = "categories/delete";
	private static final Logger logger = LoggerFactory
			.getLogger(DeleteCategorieController.class);
	private ObservationService os;

	@Autowired
	public void setObservationService(ObservationService os) {
		this.os = os;
	}

	@InitBinder
	public void initBinder(WebDataBinder wdb) {
		wdb.setAllowedFields();
	}

	@RequestMapping(method = GET)
	public ModelAndView setupForm(@PathVariable("id") Long id, HttpSession s,
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
	public String processSubmit(@PathVariable("id") Long id, HttpSession s,
			HttpServletResponse r) throws IOException {
		if (isAdmin(s)) {
			try {
				this.os.deleteCategorie(id);
				return "redirect:/c";
			} catch (Exception e) {
				logger.warn("Unexpected Exception", e);
				return internalErrorView(r);
			}
		} else {
			return forbiddenView(r);
		}
	}

}
