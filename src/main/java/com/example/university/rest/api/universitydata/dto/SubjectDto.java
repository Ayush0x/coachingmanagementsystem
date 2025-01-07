package com.example.university.rest.api.universitydata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {
    private Long subjectId;

    private String subjectName;

    private String subjectCode;

    private String subjectDescription;

    private Integer subjectCredits;
}
