package com.example.university.rest.api.universitydata.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

/**
 * Represents the admission entity corresponding to the `admission` table in the database.
 * This entity captures the details of a student's admission to a specific course.
 *
 * An AdmissionEntity is linked to:
 * - A {@link StudentEntity}, representing the student admitted.
 * - A {@link CourseEntity}, representing the course the student is admitted to.
 *
 * Features of the class include:
 * - Uniquely identified by `admissionId`.
 * - Includes information about the `fees` for the admission.
 * - Many-to-one relationships with both the StudentEntity and CourseEntity.
 */
@Entity
@Table(name = "admission")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AdmissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long admissionId;

    private Integer fees;

    @ManyToOne
    @JoinColumn(name = "Student_Id")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "Course_Id")
    private CourseEntity course;
}
