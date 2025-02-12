package com.example.university.rest.api.universitydata.repositories;

import com.example.university.rest.api.universitydata.entities.Session;
import com.example.university.rest.api.universitydata.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByUser(UserEntity user);

    Optional<Session> findByRefreshToken(String refreshToken);
}
