package com.example.university.rest.api.universitydata.services;

import com.example.university.rest.api.universitydata.dto.StudentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service interface defining the operations for managing student data.
 * This interface provides core capabilities for creating, retrieving,
 * updating, and deleting student information in the application.
 *
 * Methods:
 * - Allows fetching a specific student by their unique identifier.
 * - Provides functionality to create new student records.
 * - Supports retrieval of all stored student records.
 * - Offers the ability to update existing student details fully or partially.
 * - Enables deletion of a student record by their unique identifier.
 * - Supports patch operations for selective updates to student details.
 *
 * Thread Safety:
 * - This is a stateless service and assumes thread safety when implemented correctly.
 *
 * Dependencies:
 * - Relies on the `StudentDto` object for data transfer between layers.
 *
 * Usage:
 * - Designed to be consumed by service layers, controllers, or other business logic to manipulate student data.
 */
@Service
public interface StudentService {
    Optional<StudentDto> getStudentById(Long id);

    StudentDto createNewStudent(StudentDto studentdto);

    List<StudentDto> getAllStudents();

    StudentDto updateDetailsOfStudents(StudentDto studentdto, Long id);

    boolean deleteStudentById(Long id);

    StudentDto updateStudentById(Long id, Map<String, Object> update);
}
