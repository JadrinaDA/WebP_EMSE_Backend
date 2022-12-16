package com.emse.spring.faircorp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@EnableGlobalMethodSecurity(securedEnabled = true)
//@SpringBootApplication()
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Application {

	private static final Logger LOGGER = LogManager.getLogger(Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		LOGGER.debug("Application started successfully");
	}

}
