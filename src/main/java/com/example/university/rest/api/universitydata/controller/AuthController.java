package com.example.university.rest.api.universitydata.controller;

import com.example.university.rest.api.universitydata.dto.LoginDto;
import com.example.university.rest.api.universitydata.dto.LoginResponseDto;
import com.example.university.rest.api.universitydata.dto.SignUpDto;
import com.example.university.rest.api.universitydata.dto.UserDto;
import com.example.university.rest.api.universitydata.services.AuthService;
import com.example.university.rest.api.universitydata.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping(path = "/auth")
@AllArgsConstructor
//@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @Value("${deploy.env}")
    private String deployEnv;


    @PostMapping("/singup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto sign)
    {
        UserDto userDto=userService.signUp(sign);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response)
    {
        LoginResponseDto loginResponseDto=authService.login(loginDto);

        Cookie cookie=new Cookie("refresh_token",loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals(deployEnv));
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request)
    {
        String refreshToken= Arrays.stream(request.getCookies())
                .filter(cookie -> "refresh_token".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(()->new AuthenticationServiceException("Refresh token not found inside the cookie"));

        LoginResponseDto loginResponseDto=authService.refreshToken(refreshToken);
        return ResponseEntity.ok(loginResponseDto);
    }

}
