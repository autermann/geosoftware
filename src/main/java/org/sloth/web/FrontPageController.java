package org.sloth.web;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.sloth.web.util.CategorieEditor;
import org.slf4j.Logger;
import javax.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.sloth.model.Categorie;
import org.sloth.model.Observation;
import org.sloth.service.ObservationService;
import org.sloth.service.validator.ObservationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import static org.sloth.web.util.ControllerUtils.*;

@Controller
@RequestMapping("/")
public class FrontPageController {

	private ObservationService os;
	private ObservationValidator validator;
	private static final Logger logger = LoggerFactory.getLogger(FrontPageController.class);
	private static final String view = "welcome";
	private static final String mapContent = "observations";
	private static final String newObservationAttribute = "observation";
	private static final String categoriesAttribute = "categories";
	private static final String searchParam = "q";

	@Autowired
	public void setObservationValidator(ObservationValidator validator) {
		this.validator = validator;
	}

	@Autowired
	public void setObservationService(ObservationService observationService) {
		this.os = observationService;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		CategorieEditor c = new CategorieEditor();
		c.setObservationService(os);
		dataBinder.registerCustomEditor(Categorie.class, c);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView fillMap(HttpSession s,
								@RequestParam(value = searchParam, required = false) String q) {
		return fillModel(q, s, new ModelAndView(view));

	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView saveObservation(
			@ModelAttribute(newObservationAttribute) Observation observation,
			BindingResult result,
			SessionStatus status,
			HttpSession s,
			HttpServletResponse r,
			@RequestParam(value = searchParam, required = false) String q) throws
			IOException {
		if (isAuth(s)) {
			observation.setUser(getUser(s));
			validator.validate(observation, result);
			ModelAndView mav = new ModelAndView(view);
			fillModel(q, s, mav);
			if (result.hasErrors()) {
				mav.addObject(newObservationAttribute, observation);
				return mav;
			} else {
				try {
					this.os.registrate(observation);
				} catch (Exception e) {
					logger.warn("Unexpected Exception", e);
					return internalErrorMAV(r);
				}
				status.setComplete();
				return mav.addObject("observation", new Observation());
			}
		} else
			return forbiddenMAV(r);
	}

	private ModelAndView fillModel(String q,
								   HttpSession s,
								   ModelAndView mav) {

		logger.warn("Search Key: {}; Session: {}; Service: {}", new Object[]{q,
																			 s,
																			 os});
		if (isAuth(s)) {
			/* Only needed for creating a new Observation */
			mav.addObject(newObservationAttribute, new Observation());
			mav.addObject(categoriesAttribute, os.getCategories());
		}
		return mav.addObject(mapContent,
				(q == null) ? os.getObservations() : os.getObservations(q));

	}
}
