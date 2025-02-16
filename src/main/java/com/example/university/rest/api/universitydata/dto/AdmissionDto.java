package com.example.university.rest.api.universitydata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the data transfer object for admission-specific information.
 * It encapsulates details related to admissions in the university context.
 * This class is used to transfer admission data between different layers of the application.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionDto {

    private Long admissionId;

    private Integer fees;

}
