package com.example.university.rest.api.universitydata.services;

import com.example.university.rest.api.universitydata.dto.SubjectDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service interface for managing subject-related operations.
 * Provides methods to retrieve, create, update, and delete subject data.
 *
 * Responsibilities:
 * - Handles the retrieval of subject information by ID or retrieves all subjects.
 * - Allows creation of new subject entities.
 * - Updates existing subject details based on provided information.
 * - Supports partial updates of subject attributes using a key-value pair map.
 * - Facilitates the deletion of subjects by their unique identifier.
 *
 * Contracts:
 * - Implementation of this interface should ensure data integrity and exception
 *   handling for operations like creation, retrieval, update, and deletion.
 *
 * Methods:
 * - getSubjectById(Long id): Retrieves a specific subject's details by its ID.
 * - getAllSubjects(): Fetches a list of all available subjects.
 * - createNewSubject(SubjectDto subjectDto): Creates a new subject entity.
 * - updateEsistingSubject(SubjectDto subjectDto, Long id): Updates an existing subject.
 * - updateSubjectById(Long id, Map<String, Object> update): Partially updates an existing subject using a map of properties.
 * - deleteSubjectById(Long id): Deletes a subject based on its unique identifier.
 */
@Service
public interface SubjectService {
    Optional<SubjectDto> getSubjectById(Long id);

    List<SubjectDto> getAllSubjects();

    SubjectDto createNewSubject(SubjectDto subjectDto);

    SubjectDto updateEsistingSubject(SubjectDto subjectDto, Long id);

    SubjectDto updateSubjectById(Long id, Map<String, Object> update);

    boolean deleteSubjectById(Long id);
}
