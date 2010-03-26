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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import static org.sloth.web.util.ControllerUtils.*;

@Controller
@RequestMapping("/")
@SessionAttributes({"observations", "observation", "categories"})
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
		ModelAndView mav = new ModelAndView(view);
		if (isAuth(s)) {
			mav.addObject(newObservationAttribute, new Observation());
			mav.addObject(categoriesAttribute, os.getCategories());
		}
		return mav.addObject(mapContent, (q == null || q.trim().isEmpty()) ?
			os.getObservations() : os.getObservations(q));


	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveObservation(HttpSession s,
								  HttpServletResponse r,
								  @ModelAttribute(newObservationAttribute) Observation observation,
								  BindingResult result,
								  SessionStatus status) throws IOException {
		if (isAuth(s)) {
			observation.setUser(getUser(s));
			validator.validate(observation, result);
			if (result.hasErrors())
				return view;
			else
				try {
					status.setComplete();
					this.os.registrate(observation);
					return "redirect:/";
				} catch (Exception e) {
					logger.warn("Unexpected Exception", e);
					return internalErrorView(r);
				}
		} else
			return forbiddenView(r);
	}
}
