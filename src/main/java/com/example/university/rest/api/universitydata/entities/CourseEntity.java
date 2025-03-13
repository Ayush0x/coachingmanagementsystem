package com.example.university.rest.api.universitydata.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Represents the course entity corresponding to the `course` table in the database.
 * This entity captures the details of a course offered by the university.
 *
 * Features of the class include:
 * - Uniquely identified by `courseId`.
 * - Includes properties for the course's name, duration, and description.
 * - Defines relationships with other entities:
 *   - A one-to-many relationship with {@link AdmissionEntity}, representing the admissions associated with the course.
 *   - A one-to-many relationship with {@link SubjectEntity}, representing the subjects included in the course.
 *
 * This entity is annotated as JPA-managed and integrates common database and ORM configurations.
 */
@Entity
@Table(name = "course")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(name = "courseName",nullable = false,unique = true)
    private String courseName;

    private String courseDuration;

    private String courseDescription;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<AdmissionEntity> admissionEntities;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<SubjectEntity> subjectEntities;
}
