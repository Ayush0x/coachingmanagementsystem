package com.example.university.rest.api.universitydata.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
}
