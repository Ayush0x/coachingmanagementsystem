package com.example.university.rest.api.universitydata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionDto {

    private Long admissionId;

    private Integer fees;

}
