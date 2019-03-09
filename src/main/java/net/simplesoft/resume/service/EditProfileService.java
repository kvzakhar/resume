package net.simplesoft.resume.service;

import java.util.List;

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

public interface EditProfileService {
	
	Profile createNewProfile(SignUpForm signUpForm);
	
	Profile findById(long idProfile);
	
	List<Skill> listSkills(long idProfile);
	
	Contacts getContacts(long idProfile);
	
	List<Hobby> listHobbies(long idProfile);
	
	List<Course> listCourses(long idProfile);
	
	List<Practic> listPractics(long idProfile);
	
	List<Education> listEducation(long idProfile);

	List<SkillCategory> listSkillCategories();
	
	List<Language> listLanguages(long idProfile);
	
	List<Certificate> listCertificates(long idProfile);
	
	String getInfo(long idProfile);
	
	void updateSkills(long idProfile, List<Skill> newSkills);
}