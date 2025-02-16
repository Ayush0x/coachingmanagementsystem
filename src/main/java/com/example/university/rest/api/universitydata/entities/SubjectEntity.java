package com.example.university.rest.api.universitydata.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents the subject entity corresponding to the `subject` table in the database.
 * This entity captures the details of a subject offered within a course and associated with a professor.
 *
 * Features of the class include:
 * - Uniquely identified by `subjectId`.
 * - Contains properties such as `subjectName` and `subjectDescription` to describe the subject.
 *
 * Entity Relationships:
 * - Many-to-one relationship with {@link ProfessorEntity}, representing the professor teaching the subject.
 * - Many-to-one relationship with {@link CourseEntity}, representing the course to which the subject belongs.
 *
 * This entity is managed by JPA and integrates common database and ORM configurations.
 */
@Entity
@Table(name = "subject")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId;

    private String subjectName;

    private String subjectDescription;

    @ManyToOne
    @JoinColumn(name = "Professor_Id")
    private ProfessorEntity professor;

    @ManyToOne
    @JoinColumn(name = "Course_Id")
    private CourseEntity course;
}
