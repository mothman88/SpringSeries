package net.mothman.spring;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@PropertySource({ "classpath:persistence-mysql.properties" })
@ComponentScan({ "net.mothman.persistence" })
public class PersistenceConfig {

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "jdbc.driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "jdbc.pass";
    private static final String PROPERTY_NAME_DATABASE_URL = "jdbc.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "jdbc.user";
	
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "hibernate.package_scan";
	
    @Resource
	private Environment env;
	
	// --------------------------------------------------------------------------------------------------------------------
	// HIBERNATE CONFIGURATION

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(env.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
		
		return dataSource;
	}
    
    @Bean
    public HibernateTransactionManager transactionManager() {
        final HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject());
        return txManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    final Properties hibernateProperties() {
    	Properties properties = new Properties();
		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		return properties;	
    }
    
    // --------------------------------------------------------------------------------------------------------------------
 	// VIEW CONFIGURATION
 	
 	@Bean
 	public UrlBasedViewResolver setupViewResolver() {
 		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
 		resolver.setPrefix("/WEB-INF/pages/");
 		resolver.setSuffix(".jsp");
 		resolver.setViewClass(JstlView.class);
 		return resolver;
 	}
    
}