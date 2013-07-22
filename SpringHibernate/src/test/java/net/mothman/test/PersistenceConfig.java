package net.mothman.test;

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

@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:persistence-mysql-test.properties" })
@ComponentScan({ "net.mothman.persistence" })
public class PersistenceConfig {

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "jdbc.driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "jdbc.pass";
    private static final String PROPERTY_NAME_DATABASE_URL = "jdbc.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "jdbc.user";
	
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "hibernate.package_scan";
	
    /*
     * private static final String PROPERTY_NAME_hibernate.use_sql_comments = "hibernate.use_sql_comments";
     * private static final String PROPERTY_NAME_hibernate.format_sql = "hibernate.format_sql"
     * private static final String PROPERTY_NAME_hibernate.format_sql = "hibernate.format_sql"
     * private static final String PROPERTY_NAME_hibernate.generate_statistics = "hibernate.generate_statistics"
     * private static final String PROPERTY_NAME_javax.persistence.validation.mode = "javax.persistence.validation.mode"
     * private static final String PROPERTY_NAME_org.hibernate.envers.store_data_at_delete = "org.hibernate.envers.store_data_at_delete"
     * private static final String PROPERTY_NAME_org.hibernate.envers.global_with_modified_flag = "org.hibernate.envers.global_with_modified_flag"
     * 
     */
    
    
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
		
//      properties.setProperty("hibernate.use_sql_comments", env.getProperty("hibernate.use_sql_comments"));
//      properties.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
//      properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//      properties.setProperty("hibernate.generate_statistics", env.getProperty("hibernate.generate_statistics"));
//      properties.setProperty("javax.persistence.validation.mode", env.getProperty("javax.persistence.validation.mode"));
//
//      //Audit History flags
//      properties.setProperty("org.hibernate.envers.store_data_at_delete", env.getProperty("org.hibernate.envers.store_data_at_delete"));
//      properties.setProperty("org.hibernate.envers.global_with_modified_flag", env.getProperty("org.hibernate.envers.global_with_modified_flag"));

		
		return properties;	
    }
    
    
}