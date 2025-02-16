package com.example.university.rest.api.universitydata.repositories;

import com.example.university.rest.api.universitydata.entities.AdmissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * AdmissionRepository provides the mechanism for CRUD operations and queries
 * related to the AdmissionEntity, which represents the admissions of students
 * into courses in the system.
 *
 * Extends JpaRepository, allowing access to common database operations such as
 * saving, deleting, and finding AdmissionEntity records by their primary key `admissionId`.
 *
 * This repository can be leveraged for handling persistence operations on AdmissionEntity,
 * integrating with the database layer for the `admission` table.
 */
@Repository
public interface AdmissionRepository extends JpaRepository<AdmissionEntity,Long> {
}
