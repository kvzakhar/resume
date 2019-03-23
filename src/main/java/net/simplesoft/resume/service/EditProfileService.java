package net.simplesoft.resume.service;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import net.simplesoft.resume.entity.Certificate;
import net.simplesoft.resume.entity.Contacts;
import net.simplesoft.resume.entity.Course;
import net.simplesoft.resume.entity.Education;
import net.simplesoft.resume.entity.Hobby;
import net.simplesoft.resume.entity.Language;
import net.simplesoft.resume.entity.Practic;
import net.simplesoft.resume.entity.Profile;
import net.simplesoft.resume.entity.Skill;
import net.simplesoft.resume.entity.SkillCategory;
import net.simplesoft.resume.form.SignUpForm;
import net.simplesoft.resume.model.CurrentProfile;

public interface EditProfileService {
	
	Profile createNewProfile(SignUpForm signUpForm);
	
	void updateProfileData(@NotNull CurrentProfile currentProfile, @NotNull Profile profile, @NotNull MultipartFile photo);
	
	Profile findById(long idProfile);
	
	Set<Skill> listSkills(long idProfile);
	
	Contacts getContacts(long idProfile);
	
	Set<Hobby> listHobbies(long idProfile);
	
	Set<Course> listCourses(long idProfile);
	
	Set<Practic> listPractics(long idProfile);
	
	Set<Education> listEducation(long idProfile);

	List<SkillCategory> listSkillCategories();
	
	Set<Language> listLanguages(long idProfile);
	
	Set<Certificate> listCertificates(long idProfile);
	
	String getInfo(long idProfile);
	
	void updateSkills(long idProfile, Set<Skill> newSkills);
	
	void updateCourses(long idProfile, Set<Course> newCourses);
}