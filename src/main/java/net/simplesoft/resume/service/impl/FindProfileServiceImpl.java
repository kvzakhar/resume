package net.simplesoft.resume.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.simplesoft.resume.entity.Profile;
import net.simplesoft.resume.repository.storage.ProfileRepository;
import net.simplesoft.resume.service.FindProfileService;

@Service
public class FindProfileServiceImpl implements FindProfileService{
	
	private final ProfileRepository profileRepository;
	
	@Autowired
	public FindProfileServiceImpl(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}

	@Override
	public Profile findByUid(String uid) {
		return profileRepository.findByUid(uid);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Profile> findAllProfilesByPage(Pageable page) {
		return profileRepository.findAll(page);
	}	
	
	@Override
	@Transactional
	public Iterable<Profile> findAllForIndexing() {
		Iterable<Profile> all = profileRepository.findAll();
		for(Profile p : all) {
/*			p.getSkills().size();
			p.getCertificates().size();
			p.getLanguages().size();
			p.getPractics().size();*/
			p.getCourses().size();
		}
		return all;
	}
	

}
