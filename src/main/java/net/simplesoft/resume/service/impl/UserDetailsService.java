package net.simplesoft.resume.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.simplesoft.resume.entity.Profile;
import net.simplesoft.resume.model.CurrentProfile;
import net.simplesoft.resume.repository.storage.ProfileRepository;

@Service("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsService.class);
	
	@Autowired
	private ProfileRepository profileRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Profile profile = findProfile(username);
		if(profile != null) {
			return new CurrentProfile(profile);
		} else {
			LOGGER.error("Profile not found for: "+username);
			throw new UsernameNotFoundException("Profile not found for: "+username);
		}
	}

	private Profile findProfile(String anyUniqueId) {
		Profile profile = profileRepository.findByUid(anyUniqueId);
		if(profile == null) {
			profile = profileRepository.findByEmail(anyUniqueId);
			if(profile == null) {
				profile = profileRepository.findByPhone(anyUniqueId);
			}
		}
		return profile;
	}
}
