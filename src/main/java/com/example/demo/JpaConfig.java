package com.example.demo;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;

//@Configuration
@SuppressWarnings("rawtypes")
public class JpaConfig {

//	@Bean(name = "h2DataSource")
	public DataSource h2DataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.h2.Driver");
		dataSourceBuilder.url("jdbc:h2:C:/Users/yoges/test");
		dataSourceBuilder.username("test");
		dataSourceBuilder.password("");
		return dataSourceBuilder.build();
	}

//	@Bean(name = "sqlDataSource")
//	@Primary
	public DataSource sqlDataSource() {
		return DataSourceBuilder
				.create()
				.username("root")
				.password("123456")
				.url("jdbc:mysql://localhost:3306/studentdb?createDatabaseIfNotExist=true&autoReconnect=true&useSSL=false")
				.driverClassName("com.mysql.cj.jdbc.Driver")
				.build();
	}
	
}