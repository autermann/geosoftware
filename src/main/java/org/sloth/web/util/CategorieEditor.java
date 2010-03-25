package org.sloth.web.util;

import java.beans.PropertyEditorSupport;
import org.sloth.model.Categorie;
import org.sloth.service.ObservationService;

public class CategorieEditor extends PropertyEditorSupport {

	private ObservationService observationService;

	public void setObservationService(ObservationService os) {
		this.observationService = os;
	}

	@Override
	public void setAsText(final String text) {
		if (text == null)
			return;
		Categorie c = observationService.getCategorie(Long.valueOf(text));
		if (c == null)
			throw new IllegalArgumentException();
		setValue(c);
	}

	@Override
	public String getAsText() {
		if (getValue() == null)
			return null;
		Categorie c = (Categorie) getValue();
		return String.valueOf(c.getId());
	}
}