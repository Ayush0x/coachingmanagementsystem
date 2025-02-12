package com.example.university.rest.api.universitydata.services;

import com.example.university.rest.api.universitydata.dto.ProfessorDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface ProfessorService {
    Optional<ProfessorDto> getProfessorById(Long id);

    List<ProfessorDto> getAllProfessors();

    ProfessorDto createNewProfessor(ProfessorDto professorDto);

    ProfessorDto updateExistingProfessor(ProfessorDto professorDto, Long id);

    Boolean deleteProfessorById(Long id);

    ProfessorDto updateProfessorById(Long id, Map<String, Object> update);
}
