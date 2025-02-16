package com.example.university.rest.api.universitydata.dto;

import com.example.university.rest.api.universitydata.entities.enums.Permissions;
import com.example.university.rest.api.universitydata.entities.enums.Role;
import lombok.Data;

import java.util.Set;

/**
 * Represents the data transfer object (DTO) for user-specific information.
 * This class encapsulates the fundamental details about a user, including
 * their unique identifier, email, and name.
 *
 * It is used to transfer user data between different layers of the application,
 * providing a structured representation of a user entity for handling and communication.
 */
@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
//    private Set<Role> roles;
//    private Set<Permissions> permissions;
}