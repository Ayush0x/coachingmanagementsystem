package com.example.university.rest.api.universitydata.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Subject Table")
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

    private String subjectCode;

    private String subjectDescription;

    private Integer subjectCredits;
}
