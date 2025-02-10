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
