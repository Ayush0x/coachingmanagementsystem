package com.example.university.rest.api.universitydata.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Represents the professor entity corresponding to the `professor` table in the database.
 * This entity captures the details of a professor associated with the university.
 *
 * Key features of the class include:
 * - Uniquely identified by `professorId`.
 * - Contains properties such as `professorName`, `professorContactNumber`, `professorEmail`,
 *   `professorAddress`, and `bloodGroup`.
 * - Defines a one-to-many relationship with {@link SubjectEntity}, representing the subjects
 *   taught by the professor.
 *
 * This entity is annotated to be JPA-managed and integrates common database configurations.
 */
@Entity
@Table(name = "professor")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long professorId;

    private String professorName;

    private Long professorContactNumber;

    private String professorEmail;

    private String professorAddress;

    private String bloodGroup;

    @OneToMany(mappedBy = "professor",cascade = CascadeType.ALL)
    private List<SubjectEntity> subjectEntities;
}
