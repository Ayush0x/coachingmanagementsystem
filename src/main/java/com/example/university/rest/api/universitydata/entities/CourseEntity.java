package com.example.university.rest.api.universitydata.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    private String courseName;

    private String courseDuration;

    private String courseDescription;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<AdmissionEntity> admissionEntities;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<SubjectEntity> subjectEntities;
}
