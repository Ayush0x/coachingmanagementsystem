package com.example.university.rest.api.universitydata.dto;

import com.example.university.rest.api.universitydata.entities.enums.Permissions;
import com.example.university.rest.api.universitydata.entities.enums.Role;
import lombok.Data;

import java.util.Set;

/**
 * Represents the data transfer object (DTO) for user registration details.
 * This class encapsulates the information necessary for signing up a new user.
 *
 * It includes fields such as username, email, name, password, and contact number,
 * ensuring all essential details required for user registration are captured.
 *
 * Typically, this DTO is used to transfer data from the client side to the server
 * during the user registration process.
 */
@Data
public class SignUpDto {
    private String userName;
    private String email;
    private String name;
    private String password;
    private Long contactNumber;
}
