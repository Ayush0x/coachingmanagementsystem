package com.example.university.rest.api.universitydata.configs;

import com.example.university.rest.api.universitydata.filters.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The WebSecurityFilter class is a Spring Security configuration class responsible for
 * configuring security filters and access rules for the application.
 *
 * This class leverages multiple Spring Security annotations and components to set up
 * security mechanisms such as CSRF protection, request authorization, session management,
 * and custom JWT-based authentication.
 *
 * Components:
 * 1. SecurityFilterChain:
 *    - Configures security settings for HTTP requests, including:
 *      - Disabling CSRF protection.
 *      - Allowing public access to specified endpoints.
 *      - Enforcing authentication for all other endpoints.
 *    - Utilizes a stateless session management policy to support token-based authentication.
 *    - Adds a custom JwtAuthFilter to the filter chain, which processes incoming requests
 *      to validate and authenticate JWT tokens.
 *
 * 2. AuthenticationManager:
 *    - Exposes an AuthenticationManager bean, enabling the application to handle
 *      authentication requests.
 *
 * Annotations:
 * - @Configuration: Marks this class as a Spring configuration class.
 * - @EnableWebSecurity: Enables Spring Security and integrates its configuration.
 * - @EnableMethodSecurity: Enables method-level security, such as using @Secured annotations.
 * - @RequiredArgsConstructor: Generates a constructor for initializing final fields.
 *
 * Dependencies:
 * - JwtAuthFilter: A custom filter responsible for extracting and validating JWT tokens
 *   from incoming HTTP requests.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurtityFilter {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(
                                "/course/allCourses",
                                "/course/courseName",
                                "/auth/login",
                                "/auth/signup",
                                "/auth/refresh"
                        ).permitAll()
                        .anyRequest()
                        .authenticated())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception
    {
        return configuration.getAuthenticationManager();
    }
}
