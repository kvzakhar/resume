package net.simplesoft.resume.controller;

import java.awt.print.Pageable;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.simplesoft.resume.service.NameService;

@Controller
public class PublicDataController {

	@Autowired
	private NameService nameService;

	@RequestMapping(value="/{uid}", method=RequestMethod.GET)
	public String getProfile(@PathVariable("uid") String uid, Model model){
		String fullName = nameService.convertName(uid);
		model.addAttribute("fullName", fullName);
		return "profile";
	}
	
	@RequestMapping(value="/error", method=RequestMethod.GET)
	public String getError(){
		return "error";
	}
	
	
	@GetMapping(value="/welcome")
	public String welcome(Model model) {		
		return "welcome";		
	}

	@GetMapping(value="/search")
	public String searchProfiles() {
		
		return "search-results";	
	}
	
	@GetMapping(value = "/sign-in")
	public String signIn() {
		return "sign-in";
	}

	@GetMapping(value = "/sign-up")
	public String signUp(Model model) {
		return "sign-up";
	}

	@PostMapping(value = "/sign-up")
	public String signUp() {
		return "sign-up";
	}
	
	@GetMapping(value = "/sign-up/success")
	public String signUpSuccess() {
		return "sign-up-success";
	}
	
	@RequestMapping(value = "/sign-in-failed")
	public String signInFailed(HttpSession session) {
		if (session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") == null) {
			return "redirect:/sign-in";
		} else {
			return "sign-in";
		}
	}

	@GetMapping(value = "/restore")
	public String getRestoreAccess() {
		return "restore";
	}

	@GetMapping(value = "/restore/success")
	public String getRestoreSuccess() {
		return "restore-success";
	}

	@PostMapping(value = "/restore")
	public String processRestoreAccess(@RequestParam("uid") String anyUnigueId) {
		return "redirect:/restore/success";
	}

	@RequestMapping(value = "/restore/{token}", method = RequestMethod.GET)
	public String restoreAccess(@PathVariable("token") String token) {
		return "redirect:/edit/password";
	}
}
