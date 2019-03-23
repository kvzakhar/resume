package net.simplesoft.resume.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import net.simplesoft.resume.Constants;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private DataSource dataSource;	
	private UserDetailsService userDetailsService;
	
	/*@Autowired
	private AccessDeniedHandler accessDeniedHandler;*/
	
	@Autowired
	public SecurityConfig(DataSource dataSource, UserDetailsService userDetailsService) {
		this.dataSource = dataSource;
		this.userDetailsService = userDetailsService;
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository(){
		JdbcTokenRepositoryImpl persistentTokenRepository = new JdbcTokenRepositoryImpl();
		persistentTokenRepository.setDataSource(dataSource);
		return persistentTokenRepository;
	}	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().
			antMatchers("/my-profile/", "/edit", "/edit/**", "/remove").hasAuthority(Constants.USER).
			anyRequest().permitAll();

		http.formLogin().
			loginPage("/sign-in").
			loginProcessingUrl("/sign-in-handler").
			usernameParameter("uid").
			passwordParameter("password").
			defaultSuccessUrl("/my-profile").
			failureUrl("/sign-in-failed");

		http.logout().
			logoutUrl("/logout").
			logoutSuccessUrl("/welcome").
			invalidateHttpSession(true).
			deleteCookies("JSESSIONID");
		
		http.rememberMe().
			rememberMeParameter("remember-me").
			key("resume-online").
			tokenRepository(persistentTokenRepository());
		
	//	http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
		
		http.csrf().disable();
	}

}
