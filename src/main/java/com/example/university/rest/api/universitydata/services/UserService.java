package com.example.university.rest.api.universitydata.services;

import com.example.university.rest.api.universitydata.dto.SignUpDto;
import com.example.university.rest.api.universitydata.dto.UserDto;
import com.example.university.rest.api.universitydata.entities.UserEntity;
import com.example.university.rest.api.universitydata.entities.enums.Role;
import com.example.university.rest.api.universitydata.exceptions.ResourceNotFoundException;
import com.example.university.rest.api.universitydata.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

/**
 * UserService handles the business logic related to user management within the application.
 * It integrates functionality for user registration, authentication, and retrieval of user details.
 * This service also implements the {@link UserDetailsService} interface from Spring Security
 * for authentication purposes.
 *
 * Key Methods:
 * - `signUp(SignUpDto sign)`: Registers a new user in the system. Throws an exception if the email
 *   is already in use. Encodes passwords and assigns default roles before saving the user.
 * - `loadUserByUsername(String username)`: Implementation of the `UserDetailsService` method.
 *   Fetches user details by email for authentication. Throws an exception if the user is not found.
 * - `getUserById(Long userId)`: Retrieves a user record by its unique identifier. Throws
 *   an exception if no user is found.
 * - `getUserByEmail(String email)`: Fetches a user by their email. Returns null if the user is not found.
 *
 * Annotations:
 * - `@Service`: Indicates that this is a service class in the Spring framework.
 * - `@RequiredArgsConstructor`: Generates a constructor with required (final) dependencies, ensuring
 *   safe dependency injection.
 */
@Service
@RequiredArgsConstructor
//@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;



    public UserDto signUp(SignUpDto sign) {
        Optional<UserEntity> user=userRepository.findByEmail(sign.getEmail());
        if(user.isPresent())
        {
            throw new BadCredentialsException("User with email id "+sign.getEmail()+" already esists");
        }
        UserEntity user1=modelMapper.map(sign,UserEntity.class);
        user1.setEmail(sign.getEmail());
        user1.setUserName(sign.getUserName());
        user1.setRoles(Collections.singleton(Role.STUDENT));
        user1.setPassword(passwordEncoder.encode(user1.getPassword()));

        UserEntity savedUser=userRepository.save(user1);
        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()-> new BadCredentialsException("User with email "+username+" not found"));
    }

    public UserEntity getUserById(Long userId)
    {
        return userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User with id "+userId+" not found"));
    }

    public UserEntity getUserByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }
}
