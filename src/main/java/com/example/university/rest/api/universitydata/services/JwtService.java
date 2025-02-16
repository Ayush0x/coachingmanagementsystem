package com.example.university.rest.api.universitydata.services;

import com.example.university.rest.api.universitydata.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
//import lombok.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;

/**
 * Service class responsible for generating and parsing JWT tokens.
 * This class provides functionalities for securely creating access and refresh tokens,
 * and extracting user information from these tokens.
 * It is integrated with the application security system to support authentication workflows.
 *
 * Features:
 * - Generates JWT access and refresh tokens based on user details.
 * - Encodes user information such as ID, email, and roles within the tokens.
 * - Extracts and verifies user ID from a provided token.
 *
 * Dependencies and Configuration:
 * - Utilizes a secret key configured through the application properties
 *   (e.g., `jwt.secretKey`) to sign tokens.
 * - Makes use of 'io.jsonwebtoken' API for token creation, signing, and parsing.
 * - Tokens are signed with HMAC SHA encryption using the secret key.
 *
 * This class is typically called within higher-level authentication services such as {@link AuthService}.
 *
 * Thread Safety:
 * - The `secretKey` is injected as a configuration property ensuring proper initialization.
 * - Thread-safe methods are used for cryptographic operations.
 */
@Service
@RequiredArgsConstructor
//@AllArgsConstructor
public class JwtService {

    @Value("${jwt.secretKey}")
    private String secretKey;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(UserEntity user)
    {
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email",user.getEmail())
                .claim("roles",user.getRoles().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*10))
                .signWith(getSecretKey())
                .compact();
    }

    public String generateRefreshToken(UserEntity user)
    {
        return Jwts.builder()
                .subject(user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*20*20*20))
                .signWith(getSecretKey())
                .compact();
    }

    public Long getUserIdFromToken(String token)
    {
        Claims claims=Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return Long.valueOf(claims.getSubject());
    }
}
