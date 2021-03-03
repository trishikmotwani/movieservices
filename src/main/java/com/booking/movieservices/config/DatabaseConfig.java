package com.booking.movieservices.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
//@EnableTransactionManagement
public class DatabaseConfig {

	@Autowired
	Environment env;
	
	@Bean(name = "hdb2")
	public DataSource dataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();

	    dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
	    dataSource.setUsername(env.getProperty("spring.datasource.username"));
	    dataSource.setPassword(env.getProperty("spring.datasource.password"));
	    dataSource.setUrl(env.getProperty("spring.datasource.url"));
//	    dataSource.setUrl(
//	      "jdbc:mysql://localhost:3306/myDb?createDatabaseIfNotExist=true"); 
	    
	    return dataSource;
	}
	
	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		
		emf.setDataSource(dataSource());
		emf.setJpaProperties(properties);
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setPackagesToScan("com.booking.movieservices");
		
		return emf;
		
		
	}
	
	@Bean
	PlatformTransactionManager transactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return jpaTransactionManager;
	}
}
