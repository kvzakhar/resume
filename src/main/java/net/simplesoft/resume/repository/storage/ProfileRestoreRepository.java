package net.simplesoft.resume.repository.storage;

import org.springframework.data.repository.CrudRepository;

import net.simplesoft.resume.entity.ProfileRestore;

public interface ProfileRestoreRepository extends CrudRepository<ProfileRestore, Long> {
	
	ProfileRestore findByToken(String token);
}
