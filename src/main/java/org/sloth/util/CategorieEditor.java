package org.sloth.util;

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
		if (text == null) {
			return;
		}
		Categorie c = observationService.getCategorie(Long.valueOf(text));
		if (c == null) {
			throw new IllegalArgumentException();
		}
		setValue(c);
	}

	@Override
	public String getAsText() {
		Object o = getValue();
		if (o == null) {
			return null;
		} else {
			return String.valueOf(((Categorie) getValue()).getId());
		}
	}
}
