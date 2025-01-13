package com.example.university.rest.api.universitydata.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

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
