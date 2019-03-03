package net.simplesoft.resume.configuration;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@ComponentScan({"net.simplesoft.resume.service.impl",
				"net.simplesoft.resume.listener",
				"net.simplesoft.resume.filter"})
public class ServiceConfig {

	@Bean
	public PropertySourcesPlaceholderConfigurer placeHolderConfigurer() throws IOException{
		PropertySourcesPlaceholderConfigurer bean = new PropertySourcesPlaceholderConfigurer();
		bean.setLocations(getResources());
		return bean;
	}
	
	private static Resource[] getResources(){
		return new Resource[] {new ClassPathResource("application.properties")};
	}
}
