package com.example.university.rest.api.universitydata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDto {

    private Long professorId;

    private String professorName;

    private Long professorContactNumber;

    private String professorEmail;

    private String professorAddress;

    private String bloodGroup;
}
