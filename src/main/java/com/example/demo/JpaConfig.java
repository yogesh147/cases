package com.example.demo;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JpaConfig {

	@Bean(name = "h2DataSource")
    @Primary
	public DataSource h2DataSource() {
		@SuppressWarnings("rawtypes")
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.h2.Driver");
		dataSourceBuilder.url("jdbc:h2:C:/Users/yoges/test");
		dataSourceBuilder.username("test");
		dataSourceBuilder.password("");
		return dataSourceBuilder.build();
	}
}