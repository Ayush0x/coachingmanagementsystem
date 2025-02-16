package com.example.university.rest.api.universitydata.dto;

import lombok.Data;

/**
 * Represents the data transfer object (DTO) for handling login information.
 * This class encapsulates the data required during the authentication process,
 * primarily involving the user's email and password.
 *
 * It is used to transfer login credentials between the client and the server,
 * facilitating secure and structured communication within the authentication flow.
 */
@Data
public class LoginDto {
    String email;
    String password;
}
