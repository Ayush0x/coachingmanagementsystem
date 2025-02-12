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
