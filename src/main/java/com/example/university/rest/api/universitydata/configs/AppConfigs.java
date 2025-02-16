package com.example.university.rest.api.universitydata.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * AppConfigs is a Spring configuration class that defines common application-level
 * beans to be used across the application.
 *
 * The provided beans include:
 * - A ModelMapper instance, which is a utility for object mapping, commonly used for
 *   transforming DTOs to entities and vice versa.
 * - A PasswordEncoder implementation, specifically BCryptPasswordEncoder, used for
 *   securely hashing passwords.
 *
 * These beans enable dependency injection in other components of the application.
 */
@Configuration
public class AppConfigs {
    @Bean
    ModelMapper getModelMapper()
    {
        return new ModelMapper();
    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


}
