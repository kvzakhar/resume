package net.simplesoft.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.simplesoft.resume.configuration.JPAConfig;
import net.simplesoft.resume.entity.Profile;
import net.simplesoft.resume.repository.storage.ProfileRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfig.class})
@ActiveProfiles("local")
public class ProfileSearchRepositoryTest {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private ProfileRepository profileRepository;
	
	@Before
	public void setup() {

	}

	@Test
	public void shouldInjectContext() {
		assertNotNull(context);		
	}
	
	@Test
	public void shouldInjectRepository() {
		assertNotNull(profileRepository);		
	}
	
	@Test
	public void shouldFindById() {
		Profile profile = profileRepository.findOne(1L);
		assertNotNull(profile);
		assertEquals("Aly",profile.getFirstName());
	}	
	
	@Test
	public void shouldCreateProfile() throws Exception{
		long countBefore = profileRepository.count();
		Profile saved = profileRepository.save(createNewProfile());
		assertNotNull(saved.getId());	
		assertEquals("Test", saved.getFirstName());
		assertEquals(profileRepository.count() - countBefore, 1);
		profileRepository.delete(saved);
		assertNull(profileRepository.findOne(saved.getId()));
		assertEquals(profileRepository.count(), countBefore);
	}	
	
	@Test
	public void shouldFindByPage() throws Exception{
		Page<Profile> page = profileRepository.findAll(new PageRequest(0, 10));
		assertEquals(10, page.getNumberOfElements());
	}	
	
	private Profile createNewProfile() {
		Profile profile = new Profile();
		profile.setBirthDay(new Date(LocalDate.of(1990, 05, 12).toEpochDay()));
		profile.setFirstName("Test");
		profile.setLastName("Testov");
		profile.setPassword("123");
		profile.setUid("XXX123");
		profile.setCompleted(false);
		return profile;
	}
	
}
