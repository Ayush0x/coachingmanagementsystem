package com.example.university.rest.api.universitydata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the data transfer object for student-specific information.
 * This class encapsulates various attributes related to a student,
 * including their personal details, contact information, parental information,
 * address, and blood group.
 *
 * It is primarily used to transfer student data between different layers
 * of the application in a structured and efficient manner.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private Long studentId;

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
}
