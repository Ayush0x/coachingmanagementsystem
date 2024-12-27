package com.example.university.rest.api.universitydata.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Student Table")
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

    private Integer studentContactNumber;

    private String studentFatherName;

    private String studentMotherName;

    private Integer studentFatherContactNumber;

    private Integer studentMotherContactNumber;

    private String studentAddress;

    private String bloodGroup;
}
