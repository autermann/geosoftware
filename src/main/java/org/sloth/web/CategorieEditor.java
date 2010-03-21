package org.sloth.web;

import java.beans.PropertyEditorSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sloth.model.Categorie;
import org.sloth.service.ObservationService;

public class CategorieEditor extends PropertyEditorSupport {

	private ObservationService observationService;
	private Logger logger = LoggerFactory.getLogger(CategorieEditor.class);

	public void setObservationService(ObservationService os) {
		this.observationService = os;
	}

	@Override
	public void setAsText(final String text) {
		if (text == null)
			return;
		logger.info("Text: {}", text);
		Categorie c = observationService.getCategorie(Long.valueOf(text));
		logger.info("Categorie: {}", c);
		if (c == null)
			throw new IllegalArgumentException();
		setValue(c);
	}

	@Override
	public String getAsText() {
		if (getValue() == null) {
			return null;
		}
		Categorie c = (Categorie) getValue();
		logger.info("Categorie {}", c);
		return String.valueOf(c.getId());
	}
}
