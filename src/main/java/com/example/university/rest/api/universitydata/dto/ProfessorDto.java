package com.example.university.rest.api.universitydata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the data transfer object for professor-specific information.
 * This class encapsulates details about a professor such as their ID, name,
 * contact information, email, address, and blood group.
 *
 * It is used to transfer professor data between different layers of the application,
 * thereby enabling structured communication and management of professor-specific data.
 */
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
