package com.example.university.rest.api.universitydata.repositories;

import com.example.university.rest.api.universitydata.entities.Session;
import com.example.university.rest.api.universitydata.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * SessionRepository provides the mechanism for CRUD operations and queries
 * related to the Session entity, which represents user session data.
 *
 * Extends JpaRepository to inherit standard database operation capabilities
 * such as saving, deleting, and retrieving Session entities by their primary key `id`.
 *
 * Custom query methods include:
 * - `findByUser(UserEntity user)`: Retrieves a list of sessions associated with the specified user.
 * - `findByRefreshToken(String refreshToken)`: Finds an Optional containing a session
 *   matching the specified refresh token.
 *
 * This repository interacts with the database to handle session-related data and operations.
 */
@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByUser(UserEntity user);

    Optional<Session> findByRefreshToken(String refreshToken);
}
