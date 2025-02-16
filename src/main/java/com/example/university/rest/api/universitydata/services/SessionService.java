package com.example.university.rest.api.universitydata.services;

import com.example.university.rest.api.universitydata.entities.Session;
import com.example.university.rest.api.universitydata.entities.UserEntity;
import com.example.university.rest.api.universitydata.repositories.SessionRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

/**
 * The SessionService class handles operations related to user sessions,
 * including the generation of new sessions and validation of existing sessions.
 *
 * This service ensures that a user does not exceed the defined session limit
 * and manages the removal of the least recently used session when the limit is reached.
 *
 * Dependencies:
 * - SessionRepository: Interacts with the database to manage session data.
 *
 * Core Functionalities:
 * - generateNewSession(UserEntity user, String refreshToken):
 *   Creates a new session for a user and enforces a session limit by deleting the least
 *   recently used session if necessary.
 * - validateSession(String refreshToken):
 *   Validates an existing session using a given refresh token, updating the session's
 *   "last used" timestamp to reflect the current time.
 */
@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final int SESSION_LIMIT=2;

    public void generateNewSession(UserEntity user,String refreshToken)
    {
        List<Session> sessions=sessionRepository.findByUser(user);
        if(sessions.size()==SESSION_LIMIT)
        {
            sessions.sort(Comparator.comparing(Session::getLastUsedAt));

            Session leastRecentlyUsedSession=sessions.getFirst();
            sessionRepository.delete(leastRecentlyUsedSession);
        }

        Session newSession=Session.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();

        sessionRepository.save(newSession);
    }

    public void validateSession(String refreshToken)
    {
        Session session=sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()-> new SessionAuthenticationException("Session not found for the refresh token "+refreshToken));
        session.setLastUsedAt(LocalDateTime.now());
        sessionRepository.save(session);
    }
}
