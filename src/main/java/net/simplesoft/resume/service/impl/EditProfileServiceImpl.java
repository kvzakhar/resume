package net.simplesoft.resume.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import net.simplesoft.resume.exception.CantCompleteClientRequestException;
import net.simplesoft.resume.form.SignUpForm;
import net.simplesoft.resume.repository.storage.ProfileRepository;
import net.simplesoft.resume.repository.storage.SkillCategoryRepository;
import net.simplesoft.resume.service.EditProfileService;
import net.simplesoft.resume.util.DataUtil;

@Service
public class EditProfileServiceImpl implements EditProfileService{
	private static final Logger LOGGER = LoggerFactory.getLogger(EditProfileServiceImpl.class);
	
	private final ProfileRepository profileRepository;
	private final SkillCategoryRepository skillCategoryRepository;
	
	@Value("${generate.uid.suffix.length}")
	private int generateUidSuffixLength;
	
	@Value("${generate.password.alphabet}")
	private String generateUidAlphabet;
	
	@Value("${generate.uid.max.try.count}")
	private int maxTryCountToGenerateUid;
	
	@Autowired
	public EditProfileServiceImpl(ProfileRepository profiles, SkillCategoryRepository skills) {
		this.profileRepository = profiles;
		this.skillCategoryRepository = skills;
	}

	@Override
	@Transactional
	public Profile createNewProfile(SignUpForm signUpForm) {
		Profile profile = new Profile();
		profile.setUid(generateProfileUid(signUpForm));
		profile.setFirstName(DataUtil.capitalizeName(signUpForm.getFirstName()));
		profile.setLastName(DataUtil.capitalizeName(signUpForm.getLastName()));
		profile.setPassword(signUpForm.getPassword());
		profile.setCompleted(false);
		profileRepository.save(profile);
		return profile;
	}
	
	private String generateProfileUid(SignUpForm signUpForm) throws CantCompleteClientRequestException{
		String baseUid = DataUtil.generateProfileUid(signUpForm);
		String uid = baseUid;
		for (int i = 0; profileRepository.countByUid(uid) > 0; i++) {
			uid = DataUtil.regenerateUidWithRandomSuffix(baseUid, generateUidAlphabet, generateUidSuffixLength);
			if (i >= maxTryCountToGenerateUid) {
				throw new CantCompleteClientRequestException(
						"Can't generate unique uid for profile: " + baseUid+": maxTryCountToGenerateUid detected");
			}
		}
		return uid;
	}

	@Override
	public List<Skill> listSkills(long idProfile) {
		return profileRepository.findOne(idProfile).getSkills();
	}

	@Override
	public List<SkillCategory> listSkillCategories() {
		return skillCategoryRepository.findAll(new Sort("id"));
	}

	@Override
	@Transactional
	public void updateSkills(long idProfile, List<Skill> newSkills) {
		Profile profile = profileRepository.findOne(idProfile);
		if(CollectionUtils.isEqualCollection(newSkills, profile.getSkills())) {
			LOGGER.debug("Profile skills: nothing to update");
			return;
		} 
		profile.setSkills(newSkills);
		profileRepository.save(profile);		
	}

	@Override
	public List<Hobby> listHobbies(long idProfile) {
		return profileRepository.findOne(idProfile).getHobbies();
	}

	@Override
	public List<Course> listCourses(long idProfile) {
		return profileRepository.findOne(idProfile).getCourses();
	}

	@Override
	public List<Practic> listPractics(long idProfile) {
		return profileRepository.findOne(idProfile).getPractics();
	}

	@Override
	public List<Education> listEducation(long idProfile) {
		return profileRepository.findOne(idProfile).getEducations();
	}

	@Override
	public String getInfo(long idProfile) {
		return profileRepository.findOne(idProfile).getInfo();
	}

	@Override
	public List<Language> listLanguages(long idProfile) {
		return profileRepository.findOne(idProfile).getLanguages();
	}

	@Override
	public List<Certificate> listCertificates(long idProfile) {
		return profileRepository.findOne(idProfile).getCertificates();
	}

	@Override
	public Profile findById(long idProfile) {
		return profileRepository.findOne(idProfile);
	}

	@Override
	public Contacts getContacts(long idProfile) {
		return profileRepository.findOne(idProfile).getContacts();
	}

}
