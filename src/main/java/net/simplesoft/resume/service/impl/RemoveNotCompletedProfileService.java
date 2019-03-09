package net.simplesoft.resume.service.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.simplesoft.resume.entity.Profile;
import net.simplesoft.resume.repository.storage.ProfileRepository;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RemoveNotCompletedProfileService {

	@Value("${remove.not.completed.profiles.interval}")
	private long removeNotCompletedProfilesInterval;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Transactional
	@Scheduled(cron = "0 59 23 * * *")
	public void removeNotCompletedProfiles() {
		LocalDate date = LocalDate.now().minusDays(removeNotCompletedProfilesInterval);
		List<Long> idsToRemove = new ArrayList<>();
		for(Profile profile : profileRepository.findByCompletedFalseAndCreatedBefore(new Timestamp(date.toEpochDay()))) {
			idsToRemove.add(profile.getId());
			profileRepository.delete(profile);
		}
	}
}
