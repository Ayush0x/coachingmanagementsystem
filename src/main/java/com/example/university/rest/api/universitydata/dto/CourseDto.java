package com.example.university.rest.api.universitydata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the data transfer object for course-specific information.
 * This class encapsulates details about a course such as its ID, name,
 * duration, and description.
 *
 * It is used to transfer course data between different layers of the application,
 * facilitating the management and persistence of course information.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    private Long courseId;

    private String courseName;

    private String courseDuration;

    private String courseDescription;
}
