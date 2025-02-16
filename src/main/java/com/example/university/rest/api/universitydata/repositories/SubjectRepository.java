package com.example.university.rest.api.universitydata.repositories;

import com.example.university.rest.api.universitydata.entities.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * SubjectRepository provides the mechanism for basic CRUD operations
 * and query methods for interacting with the SubjectEntity, which represents
 * subject-related data in the system.
 *
 * Extends JpaRepository to inherit standard database operation capabilities
 * such as saving, updating, deleting, and finding SubjectEntity records by their
 * primary key `subjectId`.
 *
 * This repository integrates with the database layer to manage the `subject` table
 * and its associated data efficiently.
 */
@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity,Long> {
}
