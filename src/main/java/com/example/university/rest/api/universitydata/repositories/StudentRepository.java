package com.example.university.rest.api.universitydata.repositories;

import com.example.university.rest.api.universitydata.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * StudentRepository provides the mechanism for basic CRUD operations
 * and query methods for interacting with the StudentEntity, which represents
 * student-related data in the system.
 *
 * Extends JpaRepository to inherit standard database operation capabilities
 * such as saving, updating, deleting, and finding StudentEntity records by their
 * primary key `studentId`.
 *
 * This repository integrates with the database layer to manage the `student` table
 * and its associated data efficiently.
 */
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {

}
