package com.example.university.rest.api.universitydata.services.service_implementation;

import com.example.university.rest.api.universitydata.dto.AdmissionDto;
import com.example.university.rest.api.universitydata.dto.ProfessorDto;
import com.example.university.rest.api.universitydata.entities.AdmissionEntity;
import com.example.university.rest.api.universitydata.entities.ProfessorEntity;
import com.example.university.rest.api.universitydata.exceptions.ResourceNotFoundException;
import com.example.university.rest.api.universitydata.repositories.AdmissionRepository;
import com.example.university.rest.api.universitydata.services.AdmissionService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
 * The AdmissionServiceImplementation class provides the implementation of the AdmissionService interface.
 * It handles the core business logic for managing admission-related records, interacting with the data
 * persistence layer through AdmissionRepository and mapping entities to DTOs using ModelMapper.
 *
 * Responsibilities include:
 * - Retrieving specific admissions by ID
 * - Retrieving all admission records
 * - Creating new admission records
 * - Updating existing admission records completely or partially
 * - Deleting admission records by ID
 *
 * Dependencies:
 * - AdmissionRepository: For interacting with the database layer.
 * - ModelMapper: For mapping between entity and DTO objects.
 *
 * Uses logging through Lombok's @Slf4j for error and event tracking.
 * Throws ResourceNotFoundException when specific admission records are not found.
 */
@Service
//@RequiredArgsConstructor
@AllArgsConstructor
@Slf4j
public class AdmissionServiceImplementation implements AdmissionService {

    private AdmissionRepository admissionRepository;

    private ModelMapper modelMapper;

    @Override
    public Optional<AdmissionDto> getRecordById(Long id) {
        AdmissionEntity admission=admissionRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Unable to fetch records of the student"));
        return Optional.ofNullable(modelMapper.map(admission, AdmissionDto.class));
    }

    @Override
    public List<AdmissionDto> getAllAdmissionRecords() {
        List<AdmissionEntity> admissionEntities=admissionRepository.findAll();
        return admissionEntities.stream()
                .map(admissionEntity -> modelMapper.map(admissionEntity, AdmissionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AdmissionDto createNewRecord(AdmissionDto admissionDto) {
        Optional<AdmissionEntity> existtingRecord=admissionRepository.findById(admissionDto.getAdmissionId());
        if(!existtingRecord.isEmpty())
        {
            throw new RuntimeException("Unable to create admission record as admission record with "+admissionDto.getAdmissionId()+" alread exists");
        }
        AdmissionEntity createNewRecord=modelMapper.map(admissionDto, AdmissionEntity.class);
        AdmissionEntity createdRecord=admissionRepository.save(createNewRecord);
        return modelMapper.map(createdRecord, AdmissionDto.class);
    }


    public void isExisting(Long id)
    {
        boolean exists=admissionRepository.existsById(id);
        if(!exists)
        {
            throw new ResourceNotFoundException("Professor with id: "+id+"does not exists");
        }
    }

    @Override
    public AdmissionDto updateEsistingAdmissionRecord(AdmissionDto admissionDto, Long id) {
        isExisting(id);
        AdmissionEntity admission=modelMapper.map(admissionDto, AdmissionEntity.class);
        admission.setAdmissionId(id);
        AdmissionEntity savedAdmission=admissionRepository.save(admission);
        return modelMapper.map(savedAdmission, AdmissionDto.class);
    }

    @Override
    public AdmissionDto updateAdmissionRecordById(Long id, Map<String, Object> update) {
        isExisting(id);
        AdmissionEntity admission=admissionRepository.findById(id).get();
        update.forEach((feild,value)->
        {
            Field fieldToBeUpdated= ReflectionUtils.findRequiredField(AdmissionEntity.class,feild);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,admission,value);
        });
        return modelMapper.map(admissionRepository.save(admission), AdmissionDto.class);
    }

    @Override
    public boolean deleteAdmissionRecordById(Long id) {
        isExisting(id);
        admissionRepository.deleteById(id);
        return true;
    }


}
