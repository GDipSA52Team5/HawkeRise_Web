package sg.edu.iss.hawkerise.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.hawkerise.model.Centre;
import sg.edu.iss.hawkerise.model.Hawker;
import sg.edu.iss.hawkerise.model.Tag;
import sg.edu.iss.hawkerise.service.CentreService;
import sg.edu.iss.hawkerise.service.HawkerService;
import sg.edu.iss.hawkerise.service.TagService;

@Controller
@RequestMapping("/hawker")
public class HawkerController {
	@Autowired
	private HawkerService hservice;

	@Autowired
	private CentreService cservice;

	@Autowired
	private TagService tservice;

	@RequestMapping(value = "/home")
	public String home(@ModelAttribute("hawker") Hawker hawker, Model model, HttpSession session) {
		// if session exists, bring him to the homepage directly
		if (session.getAttribute("hsession") == null) {
			return "forward:/hawker/login";
		} else {

			Hawker h = (Hawker) session.getAttribute("hsession");
			model.addAttribute("hawker", h);
			return "hawker/home";
		}
	}

	@RequestMapping(value = "/login")
	public String login(@ModelAttribute("hawker") Hawker hawker, Model model, HttpSession session) {
		// if session exists, bring him to the homepage directly
		if (session.getAttribute("hsession") != null) {
			return "forward:/hawker/home";
		} else {
			Hawker h = new Hawker();
			model.addAttribute("hawker", h);
			return "hawker/login";
		}
	}

	@RequestMapping(value = "/logout")
	public String logout(@ModelAttribute("hawker") Hawker hawker, Model model, HttpSession session) {

		if (session.getAttribute("hsession") != null) {
			session.removeAttribute("hsession");
			return "forward:/hawker/authenticate";
		} else {
			return "forward:/hawker/authenticate";
		}
	}

	@RequestMapping(value = "/authenticate")
	public String authenticate(@ModelAttribute("hawker") @Valid Hawker hawker, BindingResult bindingResult, Model model,
			HttpSession session) {

		if (bindingResult.hasErrors()) {
			return "hawker/login";
		}

		if (session.getAttribute("hsession") != null) {
			// if session exists, bring him to the homepage directly
			Hawker h = hservice.findByUserName(hawker.getUserName());
			model.addAttribute("hawker", h);
			return "forward:/hawker/home";
		} else if (hservice.authenticate(hawker)) {
			// if session not exists, check the name and password them bring him to homepage
			Hawker h = hservice.findByUserName(hawker.getUserName());
			session.setAttribute("hsession", h);
			model.addAttribute("hawker", h);
			return "forward:/hawker/home";
		} else
			// if wrong retry
			return "hawker/login";
	}

	@RequestMapping(value = "/register")
	public String register(Model model, HttpSession session) {
		// if session exists, return to the hawker's homepage
		if (session.getAttribute("hsession") != null) {
			session.removeAttribute("hsession");
			return "forward:/hawker/register";
		}
		// if session not exists, bring him to the registration to sign-up
		else {
			Hawker newHawker = new Hawker();
			model.addAttribute("newHawker", newHawker);
			List<Centre> centres = cservice.findAllCentres();
			model.addAttribute("centres", centres);
			List<Tag> tags = tservice.findAllTags();
			model.addAttribute("tags", tags);
			return "hawker/registration";
		}
	}

	@RequestMapping(value = "/completeRegistration")
	public String completeRegisteration(@ModelAttribute("newHawker") @Valid Hawker hawker, Model model,
			BindingResult bindingResult, HttpSession session) {
		// if session exists, return to the hawker's homepage
		if (session.getAttribute("hsession") != null) {
			return "forward:/hawker/home";
		}
		// if session does not exist, create the new hawker information
		else {
			List<Centre> centres = cservice.findAllCentres();
			model.addAttribute("centres", centres);
			List<Tag> tags = tservice.findAllTags();
			model.addAttribute("tags", tags);
			
			if (hservice.checkExists(hawker)) {
				if (hservice.checkCentreAndUnitNumber(hawker)) {
					bindingResult
							.addError(new FieldError("hawker", "unitNumber", "Unit No. already in use! Please check."));
				}
				if (hservice.checkUserName(hawker)) {
					bindingResult
							.addError(new FieldError("hawker", "userName", "Username already in use! Please check."));
				}
				if (hservice.checkValidTime(hawker) == false) {
					bindingResult.addError(new FieldError("hawker", "operatingHours", "Closing Hours should be after Opening Hours! Please check."));
				}
				return "hawker/registration";
			}
			else if (hservice.checkValidTime(hawker) == false) {
				bindingResult.addError(new FieldError("hawker", "operatingHours",
						"Closing Hours should be after Opening Hours! Please check."));
				return "hawker/registration";
			} 
			else {
				Centre belongCentre = cservice.findCentreByName(hawker.getCentre().getName());
				hawker.setCentre(belongCentre);
				hservice.createHawker(hawker);
				return "forward:/hawker/login";
			}

		}
	}

	@RequestMapping(value = "/update")
	public String update(Model model, HttpSession session) {
		if (session.getAttribute("hsession") == null) {
			return "forward:/hawker/login";
		} else {

			Hawker hawker = (Hawker) session.getAttribute("hsession");
			model.addAttribute("hawkerToUpdate", hawker);
			List<Tag> tags = tservice.findAllTags();
			model.addAttribute("tags", tags);
			return "hawker/updateDetails";
		}
	}

	@RequestMapping(value = "/saveUpdate")
	public String saveUpdate(@ModelAttribute("hawkerToUpdate") Hawker hawker, BindingResult bindingResult,
			HttpSession session, Model model) {
		if (session.getAttribute("hsession") == null) {
			return "forward:/hawker/login";
		} else {
			if (hservice.checkValidTime(hawker) == false) {
				bindingResult.addError(new FieldError("hawker", "operatingHours",
						"Closing Hours should be after Opening Hours! Please check."));
				List<Tag> tags = tservice.findAllTags();
				model.addAttribute("tags", tags);
				return "hawker/updateDetails";
			}
			hservice.update(hawker);
			Hawker h = hservice.findByUserName(hawker.getUserName());
			model.addAttribute("hawker", h);
			session.removeAttribute("hesssion");
			session.setAttribute("hsession", h);
			return "hawker/home";
		}
	}

}
