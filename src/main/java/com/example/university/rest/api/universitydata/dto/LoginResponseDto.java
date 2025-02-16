package com.example.university.rest.api.universitydata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the response data transfer object (DTO) returned upon a successful login.
 * This class encapsulates the login response data, including the user ID, access token,
 * and refresh token.
 *
 * It is primarily used to communicate authentication results between the server and
 * the client, facilitating secure and efficient token-based authentication workflows.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    private Long id;
    private String accessToken;
    private String refreshToken;
}
