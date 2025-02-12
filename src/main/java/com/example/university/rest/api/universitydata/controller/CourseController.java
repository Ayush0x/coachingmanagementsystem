package com.example.university.rest.api.universitydata.controller;

import com.example.university.rest.api.universitydata.dto.CourseDto;
import com.example.university.rest.api.universitydata.dto.SubjectDto;
import com.example.university.rest.api.universitydata.exceptions.ResourceNotFoundException;
import com.example.university.rest.api.universitydata.services.CourseService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/course")
//@AllArgsConstructor
@RequiredArgsConstructor
public class CourseController {

    private CourseService courseService;

    @GetMapping(path = "/{courseId}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id)
    {
        Optional<CourseDto> courseDto=courseService.getCourseById(id);
        return courseDto.map(courseDto1 -> ResponseEntity.ok(courseDto1))
                .orElseThrow(()-> new ResourceNotFoundException("Unable to fetch course"));
    }

    @GetMapping(path = "/{allCourses}")
    public ResponseEntity<List<CourseDto>> getAllCourses()
    {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PostMapping
    public ResponseEntity<CourseDto> createNewCourse(@RequestBody CourseDto courseDto)
    {
        CourseDto createdCourse=courseService.createNewCourse(courseDto);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{courseId}")
    public ResponseEntity<Boolean> deleteCourseById(@PathVariable Long id)
    {
        boolean gotDeleted=courseService.deleteCourseById(id);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{courseId}")
    public ResponseEntity<CourseDto> updateCourseDetails(@PathVariable Long id, @RequestBody Map<String,Object> update)
    {
        CourseDto courseDto=courseService.updateCourseDetails(id,update);
        if(courseDto==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(courseDto);
    }

    @GetMapping(path = "/{courseName}")
    public ResponseEntity<CourseDto> getCourseByName(@PathVariable String name)
    {
        Optional<CourseDto> courseDto=courseService.getCourseByName(name);
        return courseDto.map(courseDto1 -> ResponseEntity.ok(courseDto1))
                .orElseThrow(()->new ResourceNotFoundException("Unable to fetch course with the given name"));
    }

}
