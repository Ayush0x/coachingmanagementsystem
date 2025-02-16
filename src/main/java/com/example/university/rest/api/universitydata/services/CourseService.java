package com.example.university.rest.api.universitydata.services;

import com.example.university.rest.api.universitydata.dto.CourseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service interface for managing courses within the system.
 * Provides operations to retrieve, create, update, and delete courses.
 * Also includes functionality for searching courses by ID or name.
 */
@Service
public interface CourseService {
    Optional<CourseDto> getCourseById(Long id);

    List<CourseDto> getAllCourses();

    CourseDto createNewCourse(CourseDto courseDto);

    boolean deleteCourseById(Long id);

    CourseDto updateCourseDetails(Long id, Map<String, Object> update);

    Optional<CourseDto> getCourseByName(String name);
}
