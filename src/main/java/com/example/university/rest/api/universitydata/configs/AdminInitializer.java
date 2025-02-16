package com.example.university.rest.api.universitydata.configs;

import com.example.university.rest.api.universitydata.entities.UserEntity;
import com.example.university.rest.api.universitydata.entities.enums.Role;
import com.example.university.rest.api.universitydata.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * AdminInitializer is a component class responsible for initializing a default
 * admin user within the application's data store if it does not already exist.
 * It ensures that a user with administrative privileges is available for managing
 * the application from the start.
 *
 * This class leverages Spring's CommandLineRunner to execute the initialization
 * logic upon application startup.
 *
 * Key operations:
 * 1. Checks the existence of a user with the predefined admin email.
 * 2. If the admin user does not exist, it creates a new user, assigns a secure
 *    password, and grants the ADMIN role.
 * 3. Persists the newly created admin user in the database.
 *
 * Dependencies:
 * - UserRepository: Used to check and save user information.
 * - PasswordEncoder: Used to securely encode passwords before saving them.
 */
@Component
public class AdminInitializer {

    @Bean
    CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        return args -> {
          if(userRepository.findByEmail("Admin123@gmail.com").isEmpty())
          {
              UserEntity admin=new UserEntity();
              admin.setEmail("Admin123@gmail.com");
              admin.setPassword(passwordEncoder.encode("Admin@1234#"));
              admin.setRoles(Collections.singleton(Role.ADMIN));

              userRepository.save(admin);
          }
        };
    }
}
