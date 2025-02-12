package com.example.university.rest.api.universitydata.configs;

import com.example.university.rest.api.universitydata.entities.UserEntity;
import com.example.university.rest.api.universitydata.entities.enums.Role;
import com.example.university.rest.api.universitydata.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

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
