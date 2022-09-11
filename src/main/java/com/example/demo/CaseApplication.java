package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class CaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseApplication.class, args);
		log.info("----------------------------  Test Case POC Application Start -----------------------------");
	}

}
