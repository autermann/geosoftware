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
import org.sloth.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import static org.sloth.web.util.ControllerUtils.*;

@Controller
@RequestMapping("/c")
public class ListCategorieController {

	private static final String view = "categories/list";
	private static final String modelAttribute = "categories";
	private ObservationService observationManager;

	@Autowired
	public void setobservationManager(ObservationService observationManager) {
		this.observationManager = observationManager;
	}

	@RequestMapping
	public ModelAndView setupList(HttpSession s,
								  HttpServletResponse r) throws IOException {
		if (isAdmin(s))
			return new ModelAndView(view, modelAttribute, observationManager.getCategories());
		else
			return forbiddenMAV(r);
	}
}