package com.example.university.rest.api.universitydata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The UniversityApplication class serves as the entry point for the Spring Boot application.
 * It enables auto-configuration, component scanning, and provides a main method to launch
 * the application.
 *
 * This class utilizes the @SpringBootApplication annotation, which is a convenience
 * annotation that combines @Configuration, @EnableAutoConfiguration, and @ComponentScan.
 *
 * The main method initializes the application context and starts the Spring Boot application
 * by invoking the run method from SpringApplication.
 */
@SpringBootApplication
public class UniversityApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityApplication.class, args);
	}

}
