package org.sloth.web;

import java.beans.PropertyEditorSupport;
import org.sloth.model.Categorie;
import org.sloth.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;

public class CategorieEditor extends PropertyEditorSupport {

 @Autowired
 ObservationService observationService;

 public void setObservationService(ObservationService os){
  this.observationService = os;
 }

 @Override
 public void setAsText(final String text) {
  int id = Integer.valueOf(text);
  Categorie c = observationService.getCategorie(id);
  setValue(c);
 }

 @Override
 public String getAsText() {
  Categorie c = (Categorie) getValue();
  return String.valueOf(c.getId());
 }
}