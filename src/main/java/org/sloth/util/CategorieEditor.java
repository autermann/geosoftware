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
package org.sloth.util;

import java.beans.PropertyEditorSupport;

import org.sloth.model.Categorie;
import org.sloth.service.ObservationService;

/**
 * Property editor used to map between {@link Categorie}s and their {@code
 * String}-represantation.
 * 
 * @author Christian Autermann
 * @author Stefan Arndt
 * @author Dustin Demuth
 * @author Christoph Fendrich
 * @author Simon Ottenhues
 * @author Christian Paluschek
 */
public class CategorieEditor extends PropertyEditorSupport {

	private ObservationService observationService;

	@Override
	public String getAsText() {
		Object o = getValue();
		if (o == null) {
			return null;
		} else {
			return String.valueOf(((Categorie) getValue()).getId());
		}
	}

	@Override
	public void setAsText(final String text) {
		if (text == null) {
			return;
		}
		Categorie c = observationService.getCategorie(Long.valueOf(text));
		if (c == null) {
			throw new IllegalArgumentException();
		}
		setValue(c);
	}

	/**
	 * @param observationService
	 *            the observationService to set
	 */
	public void setObservationService(ObservationService observationService) {
		this.observationService = observationService;
	}
}
