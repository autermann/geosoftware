package org.sloth.web;

import org.sloth.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MapController {

	private ObservationService os;

	@Autowired
	public void setObservationService(ObservationService observationService){
		this.os = observationService;
	}

	@RequestMapping("/")
	public ModelAndView fillMap() {
		return new ModelAndView("welcome", "map", os.getObservationsByCategories());
	}
}
