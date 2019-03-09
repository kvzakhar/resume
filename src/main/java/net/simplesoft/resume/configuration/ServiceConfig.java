package net.simplesoft.resume.configuration;

import java.io.IOException;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
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
	public PropertiesFactoryBean properties(){
		PropertiesFactoryBean properties = new PropertiesFactoryBean();
		properties.setLocations(new ClassPathResource("logic.properties"));
		return properties;
	}

	@Bean
	public PropertySourcesPlaceholderConfigurer placeHolderConfigurer() throws IOException{
		PropertySourcesPlaceholderConfigurer bean = new PropertySourcesPlaceholderConfigurer();
		bean.setLocations(getResources());
		return bean;
	}
	
	private static Resource[] getResources(){
		return new Resource[] {
				new ClassPathResource("logic.properties"), 
				new ClassPathResource("properties/application.properties")};
	}
}
