package com.example.university.rest.api.universitydata.services.service_implementation;

import com.example.university.rest.api.universitydata.dto.AdmissionDto;
import com.example.university.rest.api.universitydata.dto.SubjectDto;
import com.example.university.rest.api.universitydata.entities.AdmissionEntity;
import com.example.university.rest.api.universitydata.entities.ProfessorEntity;
import com.example.university.rest.api.universitydata.entities.SubjectEntity;
import com.example.university.rest.api.universitydata.exceptions.ResourceNotFoundException;
import com.example.university.rest.api.universitydata.repositories.SubjectRepository;
import com.example.university.rest.api.universitydata.services.SubjectService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for managing subjects within the university system.
 * This class provides the core business logic for handling operations related
 * to subjects such as retrieval, creation, updating, and deletion of subject data.
 * It uses {@link SubjectRepository} for database interactions and {@link ModelMapper}
 * for data conversion between entities and DTOs.
 *
 * This class is annotated with:
 * - {@code @Service}: Marks it as a Spring service bean.
 * - {@code @RequiredArgsConstructor}: Generates a constructor for final fields during runtime.
 * - {@code @Slf4j}: Provides logging capabilities.
 *
 * Methods:
 * - {@link #getSubjectById(Long)}: Fetches a specific subject by its unique identifier.
 * - {@link #getAllSubjects()}: Retrieves a list of all subjects available in the system.
 * - {@link #createNewSubject(SubjectDto)}: Creates a new subject in the system.
 * - {@link #updateEsistingSubject(SubjectDto, Long)}: Updates an existing subject by its ID.
 * - {@link #updateSubjectById(Long, Map)}: Updates specific fields of a subject by its ID.
 * - {@link #deleteSubjectById(Long)}: Deletes a subject by its unique identifier.
 * - {@link #isExisting(Long)}: Checks if a subject exists based on its ID.
 *
 * Throws:
 * - {@link ResourceNotFoundException}: If a subject with the given ID does not exist.
 * - {@link RuntimeException}: If the creation of a subject fails due to duplicate entries.
 */
@Service
@RequiredArgsConstructor
//@AllArgsConstructor
@Slf4j
public class SubjectServiceImplementation implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<SubjectDto> getSubjectById(Long id) {
        SubjectEntity subject=subjectRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Subject with id: "+id+" is not provided within the college ciriculum"));
        return Optional.ofNullable(modelMapper.map(subject, SubjectDto.class));
    }

    @Override
    public List<SubjectDto> getAllSubjects() {
        List<SubjectEntity> subjects=subjectRepository.findAll();
        return subjects.stream()
                .map(subjectEntity -> modelMapper.map(subjectEntity, SubjectDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public SubjectDto createNewSubject(SubjectDto subjectDto) {
        Optional<SubjectEntity> existingSubject=subjectRepository.findById(subjectDto.getSubjectId());
        if(!existingSubject.isEmpty())
        {
            throw new RuntimeException("Unable to create new subject as subject with "+subjectDto.getSubjectId()+" alread exists");
        }
        SubjectEntity createNewSubject=modelMapper.map(subjectDto, SubjectEntity.class);
        SubjectEntity created=subjectRepository.save(createNewSubject);
        return modelMapper.map(created, SubjectDto.class);
    }

    public void isExisting(Long id)
    {
        boolean exists=subjectRepository.existsById(id);
        if(!exists)
        {
            throw new ResourceNotFoundException("Professor with id: "+id+"does not exists");
        }
    }

    @Override
    public SubjectDto updateEsistingSubject(SubjectDto subjectDto, Long id) {
        isExisting(id);
        SubjectEntity subject=modelMapper.map(subjectDto, SubjectEntity.class);
        subject.setSubjectId(id);
        SubjectEntity savedSubject=subjectRepository.save(subject);
        return modelMapper.map(savedSubject, SubjectDto.class);
    }

    @Override
    public SubjectDto updateSubjectById(Long id, Map<String, Object> update) {
        isExisting(id);
        SubjectEntity subject=subjectRepository.findById(id).get();
        update.forEach((feild,value)->
        {
            Field fieldToBeUpdated= ReflectionUtils.findRequiredField(SubjectEntity.class,feild);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,subject,value);
        });
        return modelMapper.map(subjectRepository.save(subject), SubjectDto.class);
    }

    @Override
    public boolean deleteSubjectById(Long id) {
        isExisting(id);
        subjectRepository.deleteById(id);
        return true;
    }

}
