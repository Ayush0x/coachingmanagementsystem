package com.example.university.rest.api.universitydata.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Represents the student entity corresponding to the `student` table in the database.
 * This entity captures the details of students enrolled in the university and their associated information.
 *
 * Features of the class include:
 * - Uniquely identified by `studentId`.
 * - Contains several attributes such as the student's name, age, email, contact details, and address.
 * - Includes additional information about the student's father and mother, including their names and contact details.
 * - Captures optional details about the student's blood group.
 *
 * Entity Relationships:
 * - One-to-many relationship with {@code AdmissionEntity}, representing the admissions associated with the student.
 * - One-to-one relationship with {@code UserEntity}, representing the user account associated with the student.
 *
 * This entity is managed by JPA with various annotations for persistence, constraints, and relationships.
 */
@Entity
@Table(name = "student")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(nullable = false)
    private String studentName;

    private Integer studentAge;

    private String studentEmail;

    private Long studentContactNumber;

    private String studentFatherName;

    private String studentMotherName;

    private Long studentFatherContactNumber;

    private Long studentMotherContactNumber;

    private String studentAddress;

    private String bloodGroup;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<AdmissionEntity> admissionEntities;

    @OneToOne
    private UserEntity user;
}
