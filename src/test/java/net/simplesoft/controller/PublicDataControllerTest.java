package net.simplesoft.controller;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import net.simplesoft.resume.configuration.JPAConfig;
import net.simplesoft.resume.configuration.MVCConfig;
import net.simplesoft.resume.configuration.SecurityConfig;
import net.simplesoft.resume.configuration.ServiceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {ServiceConfig.class, MVCConfig.class, JPAConfig.class, SecurityConfig.class})
@WebAppConfiguration
@ActiveProfiles("local")
@EnableSpringDataWebSupport
public class PublicDataControllerTest {

	private MockMvc mockMvc;	

/*	@Autowired
	private FindProfileService findProfileService;
	
	@Autowired
	ProfileRepository profileRepository;*/
	
    @Autowired
	private WebApplicationContext webApplicationContext;
    
   // private Pageable pageRequest;
	
	@Before
	public void setup() {
		//findProfileService = Mockito.spy(new FindProfileServiceImpl(profileRepository));
		//Mockito.reset(findProfileService);		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
		//pageRequest = new PageRequest(0, 10, new Sort("id"));
	}

	@Test
	public void testWelcomePage() throws Exception{		
		mockMvc.perform(get("/welcome")).
			andExpect(status().isOk()).
			andExpect(view().name("welcome")).
			andExpect(model().attribute("profiles", hasSize(10)));
	}
	
	@Test
	public void testFindByUid() throws Exception{		
		mockMvc.perform(get("/aly-dutta")).
			andExpect(status().isOk()).
			andExpect(view().name("profile")).
			andExpect(model().attribute("profile",  hasProperty("id", is(1L))));		
	}	
	
	@Test
	public void shouldSumbitSingUpFormSuccess() throws Exception{
		mockMvc.perform(
				post("/sign-up").
				contentType(MediaType.APPLICATION_FORM_URLENCODED).
				param("firstName", "FirstName").
				param("lastName", "LastName").
				param("password", "P123p456!").
				param("confirmPassword", "P123p456!")		
		)
				.andExpect(status().is3xxRedirection()).				
				andExpect(view().name("redirect:/sign-up/success"));
	}
	
	@Test
	public void shouldFailSingUpFormWithWrongConfirmPassword() throws Exception{
		mockMvc.perform(
				post("/sign-up").
				contentType(MediaType.APPLICATION_FORM_URLENCODED).
				param("firstName", "FirstName").
				param("lastName", "LastName").
				param("password", "P1456p123!").
				param("confirmPassword", "P1p45!")		
		)
				.andExpect(status().isOk()).	
				andExpect(model().attributeHasFieldErrors("profileForm", "confirmPassword")).
				andExpect(view().name("sign-up"));
	}
	
	@Test
	public void shouldSuccessSingIn() throws Exception{
		mockMvc.perform(
					formLogin().
					loginProcessingUrl("/sign-in-handler").
					user("uid", "zakhar-kvasov").
					password("P123456p!")
				).andDo(print())
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/my-profile"))
				.andExpect(authenticated().withUsername("zakhar-kvasov"));
	}	
	
	@Test
	public void shouldFailSingInWrongPassword() throws Exception{
		mockMvc.perform(
					formLogin().
					loginProcessingUrl("/sign-in-handler").
					user("uid", "zakhar-kvasov").
					password("invalid")
				).andDo(print())
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/sign-in-failed"))
				.andExpect(unauthenticated());

	}		
}
