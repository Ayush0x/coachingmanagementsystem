package com.example.university.rest.api.universitydata.services;

import com.example.university.rest.api.universitydata.dto.ProfessorDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service interface defining operations for managing professors in the system.
 *
 * This interface provides methods for performing CRUD (Create, Read, Update, Delete)
 * operations on professors, including fetching professor details, creating new
 * professors, updating existing professors, and deleting professors. It leverages
 * DTOs (Data Transfer Objects) to encapsulate and transfer data.
 *
 * Responsibilities:
 * - Retrieve a professor by their unique identifier.
 * - Fetch all professors available in the system.
 * - Create a new professor with the specified details.
 * - Update details of an existing professor using their ID.
 * - Partially update a professor's information based on a map of updates.
 * - Delete a professor using their unique identifier.
 *
 * Usage:
 * This service acts as a contract for implementing professor-specific business logic.
 * It is typically used in the service or controller layer to manage professor data.
 */
@Service
public interface ProfessorService {
    Optional<ProfessorDto> getProfessorById(Long id);

    List<ProfessorDto> getAllProfessors();

    ProfessorDto createNewProfessor(ProfessorDto professorDto);

    ProfessorDto updateExistingProfessor(ProfessorDto professorDto, Long id);

    Boolean deleteProfessorById(Long id);

    ProfessorDto updateProfessorById(Long id, Map<String, Object> update);
}
