package net.simplesoft.resume.controller;

import static net.simplesoft.resume.Constants.*;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.simplesoft.resume.annotation.constraints.FieldMatch;
import net.simplesoft.resume.component.FormErrorConverter;
import net.simplesoft.resume.entity.Profile;
import net.simplesoft.resume.form.SignUpForm;
import net.simplesoft.resume.model.CurrentProfile;
import net.simplesoft.resume.service.EditProfileService;
import net.simplesoft.resume.service.FindProfileService;
import net.simplesoft.resume.util.SecurityUtil;

@Controller
public class PublicDataController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PublicDataController.class);
	
	private final FindProfileService findProfileService;	
	private final FormErrorConverter formErrorConverter;	
	private final EditProfileService editProfileService;
	
	@Autowired
	public PublicDataController(FindProfileService findService, FormErrorConverter formErrorConverter, EditProfileService editService) {
		this.findProfileService = findService;
		this.formErrorConverter = formErrorConverter;
		this.editProfileService = editService;
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
		CurrentProfile currentProfile = SecurityUtil.getCurrentProfile();
		if(currentProfile != null) {
			return "redirect:/" + currentProfile.getUsername();
		}
		return "sign-in";
	}

	@GetMapping(value = "/sign-up")
	public String signUp(Model model) {
		model.addAttribute("profileForm", new SignUpForm());
		return "sign-up";
	}

	@PostMapping(value = "/sign-up")
	public String signUp(@Valid @ModelAttribute("profileForm") SignUpForm  signUpForm, BindingResult  bindingResult) {		
		if (bindingResult.hasErrors()) {
			formErrorConverter.convertToFieldError(FieldMatch.class, signUpForm, bindingResult);
			return "sign-up";
		} else {
			Profile profile = editProfileService.createNewProfile(signUpForm);
			SecurityUtil.authentificate(profile);
			return "redirect:/sign-up/success";
		}
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
