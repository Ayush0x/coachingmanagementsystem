package com.example.university.rest.api.universitydata.repositories;

import com.example.university.rest.api.universitydata.entities.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ProfessorRepository provides an interface for CRUD operations and queries
 * related to the ProfessorEntity, which represents the professors associated
 * with the university.
 *
 * This repository extends JpaRepository, enabling standard database operations
 * such as saving, finding, and deleting ProfessorEntity records using their primary key `professorId`.
 *
 * It facilitates interaction with the underlying database layer for managing the
 * `professor` table and associated professor data.
 */
@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorEntity,Long> {
}
