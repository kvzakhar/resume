package net.simplesoft.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import net.simplesoft.resume.configuration.JPAConfig;
import net.simplesoft.resume.repository.storage.ProfileRepository;


@Configuration
@EnableJpaRepositories(basePackageClasses = ProfileRepository.class)
public class EmbeddedDatabaseConfig extends JPAConfig{

    @Override
	@Bean    
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().
        		setType(EmbeddedDatabaseType.HSQL).
        		addScript("db/sql/create.sql").
        		build();
    }
    
    @Override
	protected Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
		properties.put("hibernate.hbm2ddl.auto","create-drop");
		properties.put("hibernate.show_sql", true);
		properties.put("javax.persistence.validation.mode", "none");
		return properties;
	}
    
}
