package com.example.university.rest.api.universitydata.services;

import com.example.university.rest.api.universitydata.dto.LoginDto;
import com.example.university.rest.api.universitydata.dto.LoginResponseDto;
import com.example.university.rest.api.universitydata.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * Service class that handles user authentication and session management.
 * It provides functionalities for user login and token refresh operations.
 * This service integrates with the security framework for authentication,
 * generates JWT tokens for authenticated users, and manages user sessions.
 *
 * Dependencies:
 * - {@link AuthenticationManager}: For authentication of user credentials.
 * - {@link JwtService}: For generating and managing JWT tokens.
 * - {@link UserService}: For accessing user details and information.
 * - {@link SessionService}: For managing user sessions with refresh tokens.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final SessionService sessionService;

    public LoginResponseDto login(LoginDto loginDto) {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));

        UserEntity user=(UserEntity) authentication.getPrincipal();
        String accessToken= jwtService.generateAccessToken(user);
        String refreshToken= jwtService.generateRefreshToken(user);
        sessionService.generateNewSession(user,refreshToken);

        return new LoginResponseDto(user.getId(),accessToken,refreshToken);
    }

    public LoginResponseDto refreshToken(String refreshToken) {
        Long userId=jwtService.getUserIdFromToken(refreshToken);
        sessionService.validateSession(refreshToken);
        UserEntity user=userService.getUserById(userId);

        String accessToken= jwtService.generateAccessToken(user);
        return new LoginResponseDto(user.getId(),accessToken,refreshToken);
    }
}
