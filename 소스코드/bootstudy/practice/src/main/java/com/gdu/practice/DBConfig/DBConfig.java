package com.gdu.practice.DBConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = {"classpath:application.yml"})

@Configuration
public class DBConfig {

	@Value(value = "${spring.datasource.hikari.driver-class-name}")
	private String driverClassName;
	
	@Value(value="${spring.datasource.hikari.jdbc-url}")
	private String jdbcUrl;
	
	@Value(value="${spring.datasource.hikari.username}")
	private String username;
	
	@Value(value="${spring.datasource.hikari.password}")
	private String password;
	
	@Value(value="${mybatis.mapper-locations}")
	private String mapperLocations;
	
	@Value(value="${mybatis.config-location}")
	private String configLocation;
	
	
	
	
	

	
}