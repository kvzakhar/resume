package net.simplesoft.resume.controller;

import static net.simplesoft.resume.Constants.*;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.simplesoft.resume.entity.Profile;
import net.simplesoft.resume.service.FindProfileService;


@Controller
public class PublicDataController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PublicDataController.class);
	
	private final FindProfileService findProfileService;
	
	@Autowired
	public PublicDataController(FindProfileService findProfileService) {
		this.findProfileService = findProfileService;
	}

	@GetMapping(value="/{uid}")
	public String getProfile(@PathVariable("uid") String uid, Model model){
		Profile profile = findProfileService.findByUid(uid);
		if(profile == null) {
			return "profile-not-found";
		}
		model.addAttribute("profile", profile);
		return "profile";
	}
	
	@GetMapping(value="/error")
	public String getError(){
		return "error";
	}
	
	
	@GetMapping(value="/welcome")
	public String listAll(Model model) {		
		Page<Profile> profiles = findProfileService.findAllProfilesByPage(new PageRequest(0, MAX_PROFILES_PER_PAGE, new Sort("id")));
		model.addAttribute("profiles", profiles.getContent());
		model.addAttribute("page", profiles);		
		return "welcome";		
	}
	
	@GetMapping("/fragment/more")
	public String moreProfiles(Model model, @PageableDefault(size = MAX_PROFILES_PER_PAGE) @SortDefault(sort = "id") Pageable pageable) 
			throws UnsupportedEncodingException {
		
		Page<Profile> profiles = findProfileService.findAllProfilesByPage(pageable);
		model.addAttribute("profiles", profiles.getContent());		
		return "fragment/profile-items";
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
