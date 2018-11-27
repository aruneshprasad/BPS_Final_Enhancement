package com.BPS.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.BPS.bill.entities.BillDetails;



@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(entityManagerFactoryRef = "billEntityManager", 
						transactionManagerRef = "billTransactionManager", 
						basePackages = "com.BPS.bill.dao")

public class BillDBConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public DataSource getBillDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.bill.datasource.driver.class"));
		dataSource.setUrl(env.getProperty("spring.bill.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.bill.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.bill.datasource.password"));
		return dataSource;
	}

	@Bean(name = "billEntityManager")
	public LocalContainerEntityManagerFactoryBean billEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(getBillDataSource()).properties(hibernateProperties()).packages(BillDetails.class)
				.persistenceUnit("BillPU").build();
	}

	@Bean(name = "billTransactionManager")
	public PlatformTransactionManager billTransactionManager(
			@Qualifier("billEntityManager") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	private Map<String, Object> hibernateProperties() {

		Resource resource = new ClassPathResource("hibernate.properties");
		try {
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			return properties.entrySet().stream()
					.collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue()));
		} catch (IOException e) {
			return new HashMap<String, Object>();
		}
	}
}