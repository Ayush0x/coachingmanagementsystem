package com.example.university.rest.api.universitydata.services;

import com.example.university.rest.api.universitydata.dto.SubjectDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface SubjectService {
    Optional<SubjectDto> getSubjectById(Long id);

    List<SubjectDto> getAllSubjects();

    SubjectDto createNewSubject(SubjectDto subjectDto);

    SubjectDto updateEsistingSubject(SubjectDto subjectDto, Long id);

    SubjectDto updateSubjectById(Long id, Map<String, Object> update);

    boolean deleteSubjectById(Long id);
}
