package net.simplesoft.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import net.simplesoft.resume.entity.Profile;
import net.simplesoft.resume.repository.storage.ProfileRepository;
import net.simplesoft.resume.service.FindProfileService;
import net.simplesoft.resume.service.impl.FindProfileServiceImpl;


public class FindProfileServiceTest {

	ProfileRepository profileRepository;	
	FindProfileService findProfileService;
	
	@Before
	public void setup() {
		profileRepository = Mockito.mock(ProfileRepository.class);
		when(profileRepository.selectProfileWithJoins(Mockito.anyString())).thenReturn(new Profile());
		
		findProfileService = new FindProfileServiceImpl(profileRepository);
	}
	
	@Test
	public void shouldCreateRepositoryMock() {
		assertNotNull(profileRepository);
	}
	
	@Test
	public void shouldFindProfileById() throws Exception{
		Profile profile = findProfileService.findByUid("TestUID");
		assertNotNull(profile);
	}
	
}
