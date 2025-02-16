package com.example.university.rest.api.universitydata.repositories;

import com.example.university.rest.api.universitydata.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepository provides the mechanism for CRUD operations and queries
 * related to the UserEntity, which represents user details within the system.
 *
 * Extends JpaRepository, enabling access to common database operations such as
 * saving, deleting, and finding UserEntity records using their primary key `id`.
 *
 * Custom query methods include:
 * - `findByEmail(String email)`: Retrieves an Optional containing a UserEntity
 *   that matches the specified email, if it exists.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String username);
}
