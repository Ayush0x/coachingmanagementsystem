package com.example.university.rest.api.universitydata.services.service_implementation;

import com.example.university.rest.api.universitydata.dto.CourseDto;
import com.example.university.rest.api.universitydata.entities.CourseEntity;
import com.example.university.rest.api.universitydata.entities.SubjectEntity;
import com.example.university.rest.api.universitydata.exceptions.ResourceNotFoundException;
import com.example.university.rest.api.universitydata.repositories.CourseRepository;
import com.example.university.rest.api.universitydata.services.CourseService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Implementation of the {@link CourseService} interface to provide business logic
 * for managing course-related operations. This class interacts with the data
 * access layer through {@link CourseRepository} and maps data between entities
 * and DTOs using {@link ModelMapper}.
 *
 * Responsibilities:
 * - Retrieve course details by ID or name.
 * - Retrieve a list of all available courses.
 * - Add new courses after verifying nonexistence.
 * - Update details of an existing course.
 * - Delete courses by their ID.
 *
 * Exception Handling:
 * Throws {@link ResourceNotFoundException} for operations involving non-existent courses.
 * Throws {@link RuntimeException} when attempting to create a course that already exists.
 *
 * Methods Overview:
 * - {@code getCourseById}: Fetches an {@link Optional} of {@link CourseDto} by the course ID.
 * - {@code getAllCourses}: Retrieves a list of all courses mapped to {@link CourseDto}.
 * - {@code createNewCourse}: Adds a new course to the database.
 * - {@code updateCourseDetails}: Updates specific fields of a course entity by ID.
 * - {@code deleteCourseById}: Deletes a course by its ID and confirms operation.
 * - {@code getCourseByName}: Fetches an {@link Optional} of {@link CourseDto} by the course name.
 * - {@code isExisting}: Utility method to verify course existence by ID.
 *
 * This implementation is annotated with `@Service` to indicate that it is a service
 * layer component and uses Lombok annotations for boilerplate reduction.
 */
@Service
@RequiredArgsConstructor
//@AllArgsConstructor
@Slf4j
public class CourseServiceImplementation implements CourseService {

    private final CourseRepository courseRepository;

    private final ModelMapper modelMapper;

    @Override
    public Optional<CourseDto> getCourseById(Long id) {
        CourseEntity course=courseRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Course with given id "+id+" is not provided by the institute"));
        return Optional.ofNullable(modelMapper.map(course, CourseDto.class));
    }

    @Override
    public List<CourseDto> getAllCourses() {
        List<CourseEntity> courseEntities=courseRepository.findAll();
        return courseEntities.stream()
                .map(courseEntity -> modelMapper.map(courseEntity, CourseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CourseDto createNewCourse(CourseDto courseDto) {
        Optional<CourseEntity> existingCourse=courseRepository.findById(courseDto.getCourseId());
        if(!existingCourse.isEmpty())
        {
            throw new RuntimeException("Course cannot be created as it already exists");
        }
        CourseEntity newCourse=modelMapper.map(courseDto, CourseEntity.class);
        CourseEntity createdCourse=courseRepository.save(newCourse);
        return modelMapper.map(createdCourse, CourseDto.class);
    }

    public void isExisting(Long id)
    {
        boolean exists=courseRepository.existsById(id);
        if(!exists)
        {
            throw new ResourceNotFoundException("Professor with id: "+id+"does not exists");
        }
    }

    @Override
    public boolean deleteCourseById(Long id) {
        isExisting(id);
        courseRepository.deleteById(id);
        return true;
    }

    @Override
    public CourseDto updateCourseDetails(Long id, Map<String, Object> update) {
        isExisting(id);
        CourseEntity course=courseRepository.findById(id).get();
        update.forEach(
                (field,value)-> {
                    Field field1= ReflectionUtils.findRequiredField(CourseEntity.class,field);
                    field1.setAccessible(true);
                    ReflectionUtils.setField(field1,course,value);
                });
        return modelMapper.map(courseRepository.save(course), CourseDto.class);
    }

    @Override
    public Optional<CourseDto> getCourseByName(String courseName) {
        CourseEntity course=courseRepository.findByCourseName(courseName)
                .orElseThrow(()-> new ResourceNotFoundException("Unable to fetch course with the given name"));
        return Optional.ofNullable(modelMapper.map(course, CourseDto.class));
    }

}
