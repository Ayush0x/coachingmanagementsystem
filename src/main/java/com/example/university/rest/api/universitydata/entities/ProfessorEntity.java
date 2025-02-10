package com.example.university.rest.api.universitydata.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
