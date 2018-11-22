package org.evoke.user.hibernateconfig;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
// @EnableJpaRepositories(basePackages ="org.evoke.userapplication")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class HibernateConfig {

	@Autowired
	private Environment env;

	@Bean
    public SessionFactory getSessionFactory(){
        LocalSessionFactoryBuilder sessionFactory = new LocalSessionFactoryBuilder(dataSource());
        sessionFactory.scanPackages("org.evoke.user.model").addProperties(hibernateProperties());
       
        return sessionFactory.buildSessionFactory();
    }
	
	 @Bean
	    public Session getSession() {
	    	try {
	    	return getSessionFactory().getCurrentSession();
	    	}catch(HibernateException ex) {
	    		return getSessionFactory().openSession();
	    	}
	    }
	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("datasource.url"));
		dataSource.setUsername(env.getProperty("datasource.username"));
		dataSource.setPassword(env.getProperty("datasource.password"));

		return dataSource;
	}

	@Bean
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.sql", "debug");
		return properties;
	}

	@Bean("transactionManager")
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager htm = new HibernateTransactionManager();
		htm.setSessionFactory(sessionFactory);
		return htm;
	}

	@Bean
	public HibernateTemplate getHibernateTemplate(SessionFactory sessionFactory) {
		HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
		return hibernateTemplate;
	}

}
