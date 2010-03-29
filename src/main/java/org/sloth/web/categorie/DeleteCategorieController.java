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
import static org.sloth.util.ControllerUtils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/c/del/{id}")
public class DeleteCategorieController {

	private static final String CATEGORIE_ATTRIBUTE = "categorie";
	private static final String VIEW = "categories/delete";
	private static final Logger logger = LoggerFactory.getLogger(
			DeleteCategorieController.class);
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
	public String setupForm(@PathVariable("id") Long id, Model model,
							HttpSession s, HttpServletResponse r) throws
			IOException {
		if (isAdmin(s)) {
			Categorie categorie = observationService.getCategorie(id);
			if (categorie == null) {
				return notFoundView(r);
			}
			model.addAttribute(CATEGORIE_ATTRIBUTE, categorie);
			return VIEW;
		} else {
			return forbiddenView(r);
		}
	}

	@RequestMapping(method = POST)
	public String processSubmit(@PathVariable("id") Long id, HttpSession s,
								HttpServletResponse r) throws IOException {
		if (isAdmin(s)) {
			try {
				this.observationService.deleteCategorie(id);
			} catch(Exception e) {
				logger.warn("Unexpected Exception", e);
				return internalErrorView(r);
			}
			return "redirect:/c";
		} else {
			return forbiddenView(r);
		}
	}

}
