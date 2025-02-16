package com.example.university.rest.api.universitydata.filters;

import com.example.university.rest.api.universitydata.entities.UserEntity;
import com.example.university.rest.api.universitydata.services.JwtService;
import com.example.university.rest.api.universitydata.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

/**
 * JwtAuthFilter is a Spring Security filter that intercepts HTTP requests
 * and validates JWT tokens to authenticate users.
 *
 * This filter extends OncePerRequestFilter, ensuring that it is invoked once per request
 * in the filter chain. It processes requests by checking for the presence of a JWT
 * token in the `Authorization` header and, if valid, sets the authentication in
 * the SecurityContext.
 *
 * Key responsibilities:
 * - Extracts the JWT token from the `Authorization` header.
 * - Validates the token and extracts the user ID using JwtService.
 * - Fetches the corresponding user details using UserService.
 * - Creates an authentication token and sets it in the SecurityContext if authentication
 *   is successful.
 * - Delegates unprocessed requests to the next filter in the chain.
 * - Handles exceptions during filtration and resolves them using a HandlerExceptionResolver.
 *
 * Dependencies:
 * - JwtService: Handles token validation and extraction of user details from the token.
 * - UserService: Retrieves user information based on the extracted user ID.
 * - HandlerExceptionResolver: Used to resolve exceptions that may occur during the filtering process.
 *
 * Behavior:
 * - If the `Authorization` header is missing or the token is invalid, the request processing
 *   is delegated to the next filter without setting authentication.
 * - If authentication succeeds, the user is authenticated, and their authentication token is
 *   registered in the SecurityContextHolder.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver handlerExceptionResolver;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String requestTokenHeader=request.getHeader("Authorization");
            if(requestTokenHeader==null|| !requestTokenHeader.startsWith("Bearer"))
            {
                filterChain.doFilter(request,response);
                return;
            }

            String token=requestTokenHeader.split("Bearer")[1];
            Long userId=jwtService.getUserIdFromToken(token);

            if(userId!=null && SecurityContextHolder.getContext().getAuthentication()==null)
            {
                UserEntity user=userService.getUserById(userId);
                UsernamePasswordAuthenticationToken authenticationToken=
                        new UsernamePasswordAuthenticationToken(user,null ,user.getAuthorities());
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request,response);
        }
        catch (Exception ex)
        {
            handlerExceptionResolver.resolveException(request,response,null,ex);
        }

    }
}
