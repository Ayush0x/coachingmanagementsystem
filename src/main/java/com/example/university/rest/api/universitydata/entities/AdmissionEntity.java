package com.example.university.rest.api.universitydata.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Admission Records")
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
}
