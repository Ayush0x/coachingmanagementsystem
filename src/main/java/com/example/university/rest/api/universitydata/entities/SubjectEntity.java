package com.example.university.rest.api.universitydata.entities;

import jakarta.persistence.*;
import lombok.*;

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
