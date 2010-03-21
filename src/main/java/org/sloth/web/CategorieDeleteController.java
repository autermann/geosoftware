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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.exceptions.ConstraintViolationException;
import org.sloth.model.Categorie;
import org.sloth.model.Group;
import org.sloth.model.Observation;
import org.sloth.model.User;
import org.sloth.service.ObservationService;
import org.sloth.service.UserService;
import org.sloth.service.validator.CategorieValidator;
import org.sloth.service.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * @todo
 * @author auti
 */
@Controller
@RequestMapping("/categories/delete")
@SessionAttributes(types = Categorie.class)
public class CategorieDeleteController {

	private Logger logger = LoggerFactory.getLogger(CategorieDeleteController.class);
	@Autowired
	private ObservationService observationManager;
	@Autowired
	private CategorieValidator categorieValidator;

	/**
	 * @todo
	 * @param userValidator
	 */
	public void setCategorieValidator(CategorieValidator categorieValidator) {
		this.categorieValidator = categorieValidator;
	}

	/**
	 * @todo
	 * @return
	 */
	protected CategorieValidator getCategorieValidator() {
		return this.categorieValidator;
	}

	/**
	 * @todo
	 * @param userManager
	 */
	public void setObservationManager(ObservationService observationManager) {
		this.observationManager = observationManager;
	}

	/**
	 * @todo
	 * @return
	 */
	protected ObservationService getObservationManager() {
		return this.observationManager;
	}

	/**
	 * @todo
	 * @param dataBinder
	 */
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setAllowedFields("title", "description", "iconFileName");
	}

	/**
	 * @param model
	 * @return
	 * @todo
	 * Beim Aufruf der Seite wird die GET-Methoder aufgerufen...
	 */
	@RequestMapping(method = GET)
	public String setupForm(Model model) {
		Categorie categorie = new Categorie();
		model.addAttribute(categorie);
		return "categories/edit";
	}

	/**
	 * @param user
	 * @param result
	 * @param status
	 * @return
	 * @todo
	 * Beim Absenden des Formulars die POST-Methode...
	 */
	@RequestMapping(method = POST)
            public String processSubmit(@ModelAttribute Categorie categorie, BindingResult result,
								SessionStatus status) {
		getCategorieValidator().validate(categorie, result);
		if (result.hasErrors()) {
			return "redirect:/";
		} else {
			try {
				this.observationManager.updateCategorie(categorie);
			} catch(NullPointerException e) {
				logger.warn("Binding fail. no user model attribute.", e);
			} catch(IllegalArgumentException e) {
				logger.warn("Model-Attribute user is already known.", e);
			} catch(ConstraintViolationException e) {
				//TODO
			}
			status.setComplete();
			return "redirect:/categories";
		}
	}

}
