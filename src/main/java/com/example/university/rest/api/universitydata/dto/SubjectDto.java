package com.example.university.rest.api.universitydata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the data transfer object for subject-specific information.
 * This class encapsulates details about a subject, including its ID, name,
 * and description.
 *
 * It is used to transfer subject data between different layers of the application,
 * providing a structured representation of a subject entity for easier management
 * and communication.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {
    private Long subjectId;

    private String subjectName;

    private String subjectDescription;

}
