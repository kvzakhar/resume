package net.simplesoft.resume.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.multipart.MultipartFile;

import net.simplesoft.resume.annotation.ProfileDataFieldGroup;
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
import net.simplesoft.resume.exception.FormValidationException;
import net.simplesoft.resume.form.SignUpForm;
import net.simplesoft.resume.model.CurrentProfile;
import net.simplesoft.resume.model.UploadResult;
import net.simplesoft.resume.repository.storage.ProfileRepository;
import net.simplesoft.resume.repository.storage.SkillCategoryRepository;
import net.simplesoft.resume.service.EditProfileService;
import net.simplesoft.resume.service.ImageProcessorService;
import net.simplesoft.resume.service.ImageStorageService;
import net.simplesoft.resume.util.DataUtil;

@Service
public class EditProfileServiceImpl implements EditProfileService{
	private static final Logger LOGGER = LoggerFactory.getLogger(EditProfileServiceImpl.class);
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private SkillCategoryRepository skillCategoryRepository;
	
	@Autowired
	private ImageProcessorService imageProcessorService;
	
	@Autowired
	private ImageStorageService imageStorageService;
	
	@Value("${generate.uid.suffix.length}")
	private int generateUidSuffixLength;
	
	@Value("${generate.password.alphabet}")
	private String generateUidAlphabet;
	
	@Value("${generate.uid.max.try.count}")
	private int maxTryCountToGenerateUid;
	
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
	public Set<Skill> listSkills(long idProfile) {
		return profileRepository.findOne(idProfile).getSkills();
	}

	@Override
	public List<SkillCategory> listSkillCategories() {
		return skillCategoryRepository.findAll(new Sort("id"));
	}

	@Override
	@Transactional
	public void updateSkills(long idProfile, Set<Skill> newSkills) {
		Profile profile = profileRepository.findOne(idProfile);
		if(CollectionUtils.isEqualCollection(newSkills, profile.getSkills())) {
			LOGGER.debug("Profile skills: nothing to update");
			return;
		} 
		profile.setSkills(newSkills);
		profileRepository.save(profile);		
	}

	@Override
	public Set<Hobby> listHobbies(long idProfile) {
		return profileRepository.findOne(idProfile).getHobbies();
	}

	@Override
	public Set<Course> listCourses(long idProfile) {
		return Collections.unmodifiableSet(profileRepository.findOne(idProfile).getCourses());
	}
	
	@Override
	@Transactional
	public void updateCourses(long idProfile, Set<Course> newCourses) {
		Profile profile = profileRepository.findOne(idProfile);
		
		if(CollectionUtils.isEqualCollection(newCourses, profile.getCourses())) {
			LOGGER.info("Profile courses: nothing to update");
			return;
		}
		profile.setCourses(newCourses);
		profileRepository.save(profile);
	}

	@Override
	public Set<Practic> listPractics(long idProfile) {
		return profileRepository.findOne(idProfile).getPractics();
	}

	@Override
	public Set<Education> listEducation(long idProfile) {
		return profileRepository.findOne(idProfile).getEducations();
	}

	@Override
	public String getInfo(long idProfile) {
		return profileRepository.findOne(idProfile).getInfo();
	}

	@Override
	public Set<Language> listLanguages(long idProfile) {
		return profileRepository.findOne(idProfile).getLanguages();
	}

	@Override
	public Set<Certificate> listCertificates(long idProfile) {
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

	@Override
	@Transactional
	public void updateProfileData(CurrentProfile currentProfile, Profile profileForm, MultipartFile uploadPhoto) {
		Profile loadedProfile = profileRepository.findOne(currentProfile.getId());
		List<String> oldProfilePhotos = null;
		
		if(!uploadPhoto.isEmpty()) {
			UploadResult uploadResult = imageProcessorService.processNewProfilePhoto(uploadPhoto);
			deleteUploadedPhotosIfTransactionFailed(uploadResult);
			oldProfilePhotos = Arrays.asList(new String[] { loadedProfile.getLargePhoto(), loadedProfile.getSmallPhoto() });
			loadedProfile.updateProfilePhotos(uploadResult.getLargeUrl(), uploadResult.getSmallUrl());
		}
		
		int copiedFieldsCount = DataUtil.copyFields(profileForm, loadedProfile, ProfileDataFieldGroup.class);
		boolean shouldProfileBeUpdated = !uploadPhoto.isEmpty() || copiedFieldsCount > 0;
		if (shouldProfileBeUpdated) {
			executeUpdateProfileData(currentProfile, loadedProfile);
		}		
	}
	
	protected void executeUpdateProfileData(CurrentProfile currentProfile, Profile loadedProfile) {
		loadedProfile.setCompleted(true);
		synchronized (this) {
		//	checkForDuplicatesEmailAndPhone(loadedProfile);
			//profileRepository.save(loadedProfile);
		}
	}
	
	protected void checkForDuplicatesEmailAndPhone(Profile profileForm) {
		Profile profileForEmail = profileRepository.findByEmail(profileForm.getEmail());
		if (profileForEmail != null && !profileForEmail.getId().equals(profileForm.getId())) {
			throw new FormValidationException("email", profileForm.getEmail());
		}
		Profile profileForPhone = profileRepository.findByPhone(profileForm.getPhone());
		if (profileForPhone != null && !profileForPhone.getId().equals(profileForm.getId())) {
			throw new FormValidationException("phone", profileForm.getPhone());
		}
	}
	
	protected void deleteUploadedPhotosIfTransactionFailed(final UploadResult uploadResult) {
		TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
			@Override
			public void afterCompletion(int status) {
				if (status == TransactionSynchronization.STATUS_ROLLED_BACK) {
					imageStorageService.remove(uploadResult.getLargeUrl(), uploadResult.getSmallUrl());
				}
			}
		});
	}

}
