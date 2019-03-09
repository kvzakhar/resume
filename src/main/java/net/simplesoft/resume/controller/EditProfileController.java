package net.simplesoft.resume.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.simplesoft.resume.entity.Contacts;

import net.simplesoft.resume.entity.Profile;
import net.simplesoft.resume.form.CertificateForm;
import net.simplesoft.resume.form.CourseForm;
import net.simplesoft.resume.form.EducationForm;
import net.simplesoft.resume.form.InfoForm;
import net.simplesoft.resume.form.LanguageForm;
import net.simplesoft.resume.form.PracticForm;
import net.simplesoft.resume.form.SkillForm;
import net.simplesoft.resume.service.EditProfileService;
import net.simplesoft.resume.service.StaticDataService;
import net.simplesoft.resume.util.SecurityUtil;

@Controller
public class EditProfileController {	
	private static final Logger LOGGER = LoggerFactory.getLogger(EditProfileController.class);

	private EditProfileService editProfileService;
	
	@Autowired
	private StaticDataService staticDataService;
	
	@Autowired
	public EditProfileController(EditProfileService editProfileService) {
		this.editProfileService = editProfileService;
	}

	@GetMapping(value = "/edit")
	public String getEditProfile(Model model) {
		model.addAttribute("profileForm", editProfileService.findById(SecurityUtil.getCurrentIdProfile()));
		return "edit/profile";
	}

	@PostMapping(value = "/edit")
	public String saveEditProfile(@Valid @ModelAttribute("profileForm") Profile profileForm, BindingResult bindingResult, 
			@RequestParam("profilePhoto") MultipartFile uploadPhoto) {
		if(bindingResult.hasErrors()) {
			return "edit/profile";
		}
		
		return "edit/profile";
	}

	@GetMapping(value = "/edit/contacts")
	public String getEditContactsProfile(Model model) {
		model.addAttribute("contactsForm", editProfileService.getContacts(SecurityUtil.getCurrentIdProfile()));
		return "edit/contacts";
	}

	@PostMapping(value = "/edit/contacts")
	public String saveEditContactsProfile(@Valid @ModelAttribute("contactsForm") Contacts contactsForm, BindingResult bindingResult) {
		if(!bindingResult.hasErrors()) {
			return "edit/contacts";
		}
		return "edit/contacts";
	}

	@GetMapping(value = "/edit/skills")
	public String getEditSkills(Model model) {
		LOGGER.info("GET SKILLS");
		model.addAttribute("skillForm", new SkillForm(editProfileService.listSkills(SecurityUtil.getCurrentIdProfile())));
		return gotoSkillsJSP(model);
	}

	@PostMapping(value = "/edit/skills")
	public String saveEditSkills(@Valid @ModelAttribute("skillForm") SkillForm form, BindingResult bindingResult, Model model) {
		LOGGER.info("POST SKILLS");
		if(!bindingResult.hasErrors()) {			
			editProfileService.updateSkills(SecurityUtil.getCurrentIdProfile(), form.getItems());
		}		
		return gotoSkillsJSP(model);
	}

	private String gotoSkillsJSP(Model model) {
		model.addAttribute("skillCategories", editProfileService.listSkillCategories());
		return "edit/skills";
	}

	@GetMapping(value = "/edit/practics")
	public String getEditPractics(Model model) {
		model.addAttribute("practicForm", new PracticForm(editProfileService.listPractics(SecurityUtil.getCurrentIdProfile())));
		return gotoPracticsJSP(model);
	}

	@PostMapping(value = "/edit/practics")
	public String saveEditPractics(@Valid @ModelAttribute("practicForm") PracticForm form, 
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return gotoPracticsJSP(model);
		}
		return gotoPracticsJSP(model);
	}

	private String gotoPracticsJSP(Model model) {
		model.addAttribute("years",  staticDataService.findPracticsYears());
		model.addAttribute("months", staticDataService.findMonthMap());
		return "edit/practics";
	}

	@RequestMapping(value = "/edit/certificates", method = RequestMethod.GET)
	public String getEditCertificates(Model model) {
		model.addAttribute("certificateForm", 
				new CertificateForm(editProfileService.listCertificates(SecurityUtil.getCurrentIdProfile())));
		return "edit/certificates";
	}

	@RequestMapping(value = "/edit/certificates", method = RequestMethod.POST)
	public String saveEditCertificates(@Valid @ModelAttribute("certificateForm") CertificateForm form, 
			BindingResult bindingResult, Model model) {	
		if (bindingResult.hasErrors()) {
			return "edit/certificates";
		} else {
			
			return "redirect:/edit/courses";
		}
	}

	@GetMapping(value = "/edit/courses")
	public String getEditCourses(Model model) {
		model.addAttribute("courseForm", new CourseForm(editProfileService.listCourses(SecurityUtil.getCurrentIdProfile())));
		return gotoCoursesJSP(model);
	}

	@PostMapping(value = "/edit/courses")
	public String saveEditCourses(Model model) {
		return gotoCoursesJSP(model);
	}

	private String gotoCoursesJSP(Model model) {
		model.addAttribute("years",  staticDataService.findCourcesYears());
		model.addAttribute("months", staticDataService.findMonthMap());
		return "edit/courses";
	}

	@GetMapping(value = "/edit/education")
	public String getEditEducation(Model model) {
		model.addAttribute("educationForm", new EducationForm(editProfileService.listEducation(SecurityUtil.getCurrentIdProfile())));
		return gotoEducationJSP(model);
	}

	@PostMapping(value = "/edit/education")
	public String saveEditEducation(BindingResult bindingResult, Model model) {
		return gotoEducationJSP(model);
	}

	private String gotoEducationJSP(Model model) {
		return "edit/education";
	}

	@GetMapping(value = "/edit/languages")
	public String getEditLanguages(Model model) {
		model.addAttribute("languageForm", new LanguageForm(editProfileService.listLanguages(SecurityUtil.getCurrentIdProfile())));
		return gotoLanguagesJSP(model);
	}

	@PostMapping(value = "/edit/languages")
	public String saveEditLanguages(BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return gotoLanguagesJSP(model);
		} else {
			
			return "redirect:/edit/hobbies";
		}
	}

	private String gotoLanguagesJSP(Model model) {
		return "edit/languages";
	}

	@GetMapping(value = "/edit/hobbies")
	public String getEditHobbies(Model model) {
		model.addAttribute("hobbies", editProfileService.listHobbies(SecurityUtil.getCurrentIdProfile()));
		return "edit/hobbies";
	}

	@PostMapping(value = "/edit/hobbies")
	public String saveEditHobbies(@RequestParam("hobbies") List<String> hobbies) {
		return "redirect:/edit/info";
	}

	@RequestMapping(value = "/edit/info", method = RequestMethod.GET)
	public String getEditProfileInfo(Model model) {
		model.addAttribute("infoForm", new InfoForm(editProfileService.getInfo(SecurityUtil.getCurrentIdProfile())));
		return "edit/info";
	}

	@PostMapping(value = "/edit/info")
	public String saveEditProfileInfo(BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()){
			return "edit/info";
		} else {

			return "redirect:/my-profile";
		}
	}

	@GetMapping(value = "/edit/password")
	public String getEditPasswords(Model model) {
		return "password";
	}

	@PostMapping(value = "/edit/password")
	public String saveEditPasswords(BindingResult bindingResult, Model model) {
		return "password";
	}

	@RequestMapping(value = "/my-profile")
	public String getMyProfile(Model model) {
		return "redirect:/";
	}
}
