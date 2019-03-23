package net.simplesoft.resume.model;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import net.simplesoft.resume.Constants;
import net.simplesoft.resume.entity.Profile;

public class CurrentProfile extends User {
	
	private static final long serialVersionUID = -2891281077427764117L;
	private final Long id;
	private final String fullName;

	public CurrentProfile(Profile profile) {
		super(profile.getUid(), profile.getPassword(), true, true, true, true, 
				Collections.singleton(new SimpleGrantedAuthority(Constants.USER)));
		this.id = profile.getId();
		this.fullName = profile.getFullName();
	}

	public Long getId() {
		return id;
	}
	
	public String getFullName() {
		return fullName;
	}

	@Override
	public String toString() {
		return String.format("CurrentProfile [id=%s, username=%s]", id, getUsername());
	}
}