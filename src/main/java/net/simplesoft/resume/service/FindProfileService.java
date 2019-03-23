package net.simplesoft.resume.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.simplesoft.resume.entity.Profile;

public interface FindProfileService {

	Profile findByUid(String uid);	
	
	Page<Profile> findAllProfilesByPage(Pageable page);

	Iterable<Profile> findAllForIndexing();
}
