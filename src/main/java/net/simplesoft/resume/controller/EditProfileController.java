package net.simplesoft.resume.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.simplesoft.resume.form.SkillForm;
import net.simplesoft.resume.repository.storage.ProfileRepository;
import net.simplesoft.resume.repository.storage.SkillCategoryRepository;

@Controller
public class EditProfileController {	

	private SkillCategoryRepository skillCategoryRepository;
	private ProfileRepository profileRepository;
	
	@Autowired
	public EditProfileController(SkillCategoryRepository skillCategoryRepository, ProfileRepository profileRepository) {
		this.skillCategoryRepository = skillCategoryRepository;
		this.profileRepository = profileRepository;
	}

	@GetMapping(value = "/edit")
	public String getEditProfile(Model model) {
		return "edit/profile";
	}

	@PostMapping(value = "/edit")
	public String saveEditProfile() {
		return "edit/profile";
	}

	@GetMapping(value = "/edit/contacts")
	public String getEditContactsProfile(Model model) {
		return "edit/contacts";
	}

	@PostMapping(value = "/edit/contacts")
	public String saveEditContactsProfile() {
		return "edit/contacts";
	}

	@GetMapping(value = "/edit/skills")
	public String getEditTechSkills(Model model) {
		model.addAttribute("skillForm", new SkillForm(profileRepository.findOne(1L).getSkills()));
		return gotoSkillsJSP(model);
	}

	@PostMapping(value = "/edit/skills")
	public String saveEditTechSkills(@ModelAttribute("skillForm") SkillForm form, BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			return gotoSkillsJSP(model);
		}
		return "redirect:/mike-ross";
	}

	private String gotoSkillsJSP(Model model) {
		model.addAttribute("skillCategories", skillCategoryRepository.findAll(new Sort("id")));
		return "edit/skills";
	}

	@GetMapping(value = "/edit/practics")
	public String getEditPractics(Model model) {
		return gotoPracticsJSP(model);
	}

	@PostMapping(value = "/edit/practics")
	public String saveEditPractics(Model model) {
		return gotoPracticsJSP(model);
	}

	private String gotoPracticsJSP(Model model) {
		return "edit/practics";
	}

	@RequestMapping(value = "/edit/certificates", method = RequestMethod.GET)
	public String getEditCertificates(Model model) {
		return "edit/certificates";
	}

	@RequestMapping(value = "/edit/certificates", method = RequestMethod.POST)
	public String saveEditCertificates(Model model) {
		return "edit/certificates";

	}

	@GetMapping(value = "/edit/courses")
	public String getEditCourses(Model model) {
		return gotoCoursesJSP(model);
	}

	@PostMapping(value = "/edit/courses")
	public String saveEditCourses(Model model) {
		return gotoCoursesJSP(model);
	}

	private String gotoCoursesJSP(Model model) {
		return "edit/courses";
	}

	@GetMapping(value = "/edit/education")
	public String getEditEducation(Model model) {
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
		return gotoLanguagesJSP(model);
	}

	@PostMapping(value = "/edit/languages")
	public String saveEditLanguages(BindingResult bindingResult, Model model) {
		return gotoLanguagesJSP(model);
	}

	private String gotoLanguagesJSP(Model model) {
		return "edit/languages";
	}

	@GetMapping(value = "/edit/hobbies")
	public String getEditHobbies(Model model) {
		return "edit/hobbies";
	}

	@PostMapping(value = "/edit/hobbies")
	public String saveEditHobbies(@RequestParam("hobbies") List<String> hobbies) {
		return "redirect:/edit/info";
	}

	@RequestMapping(value = "/edit/info", method = RequestMethod.GET)
	public String getEditProfileInfo(Model model) {
		return "edit/info";
	}

	@PostMapping(value = "/edit/info")
	public String saveEditProfileInfo(BindingResult bindingResult, Model model) {
		return "edit/info";
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
