package com.example.university.rest.api.universitydata.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Represents the session entity corresponding to the `session` table in the database.
 * This entity is designed to capture information about user sessions, including:
 * - A unique identifier for each session.
 * - The refresh token associated with the session.
 * - The timestamp indicating the last time the session was used.
 * - The user entity associated with this session.
 *
 * Key features:
 * - Each session is uniquely identified by its `id`.
 * - The `refreshToken` field stores the token used for session authentication and renewal.
 * - The `lastUsedAt` field is automatically populated with the creation timestamp when the session is created or updated.
 * - The session is linked to a single {@code UserEntity} through a many-to-one relationship, representing the owner of the session.
 *
 * This entity is managed by JPA and integrates common annotations for persistent storage,
 * relationships, and automatic timestamping behavior.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String refreshToken;

    @CreationTimestamp
    private LocalDateTime lastUsedAt;

    @ManyToOne
    private UserEntity user;
}
