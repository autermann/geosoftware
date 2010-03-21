package org.sloth.web;

import java.util.Collection;
import java.util.Map;
import org.slf4j.Logger;
import javax.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.sloth.exceptions.ConstraintViolationException;
import org.sloth.model.Categorie;
import org.sloth.model.Observation;
import org.sloth.model.User;
import org.sloth.service.ObservationService;
import org.sloth.service.validator.ObservationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MapController {

	private ObservationService os;
	private ObservationValidator ov;
	private static final Logger logger = LoggerFactory.getLogger(MapController.class);

	@Autowired
	public void setObservationValidator(ObservationValidator ov) {
		this.ov = ov;
	}

	@Autowired
	public void setObservationService(ObservationService os) {
		this.os = os;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView fillMap(Model model) {
		ModelAndView mav = new ModelAndView("welcome");
		mav.addObject("observation", new Observation());
		mav.addObject("map", os.getObservationsByCategories());
		mav.addObject("categories", os.getCategories());
		return mav;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		CategorieEditor c = new CategorieEditor();
		c.setObservationService(os);
		dataBinder.registerCustomEditor(Categorie.class, c);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processSubmit(@ModelAttribute("observation") Observation observation,
			BindingResult result,
			SessionStatus status, HttpSession session) {
		User u = (User) session.getAttribute("loginUser");
		if (u != null) {
			ov.validate(observation, result);
			ModelAndView mav = new ModelAndView("welcome");
			mav.addObject("categories", os.getCategories());
			mav.addObject("map", os.getObservationsByCategories());
			if (result.hasErrors()) {
				mav.addObject("observation", observation);
				return mav;
			} else {
				observation.setUser(u);
				try {
					this.os.registrate(observation);
				} catch (NullPointerException e) {
					logger.warn("Binding fail. No Observation Model Attribut.", e);
				} catch (IllegalArgumentException e) {
					logger.warn("Model-Attribute observation is already known...", e);
				} catch (ConstraintViolationException e) {
					logger.warn("Die hier fliegt: ", e);
				}
				status.setComplete();
				mav.addObject("observation", new Observation());
				return mav;
			}
		} else {
			return new ModelAndView("login");
		}

	}
}
