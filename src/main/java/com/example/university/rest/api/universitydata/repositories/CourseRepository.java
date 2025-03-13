package com.example.university.rest.api.universitydata.repositories;

import com.example.university.rest.api.universitydata.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * CourseRepository provides the mechanism for CRUD operations and queries
 * related to the CourseEntity, which represents the details of courses offered
 * by the university.
 *
 * Extends JpaRepository, enabling access to common database operations such as
 * saving, deleting, and finding CourseEntity records using their primary key `courseId`.
 *
 * Custom query methods include:
 * - `findByName(String name)`: Retrieves an Optional containing a CourseEntity
 *   that matches the specified course name, if it exists.
 *
 * This repository allows interaction with the database layer for managing
 * the `course` table and its associated data.
 */
@Repository
public interface CourseRepository extends JpaRepository<CourseEntity,Long> {
    Optional<CourseEntity> findByCourseName(String courseName);
}
