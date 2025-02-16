package com.example.university.rest.api.universitydata.services;

import com.example.university.rest.api.universitydata.dto.AdmissionDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The AdmissionService interface defines the operations for managing admission records.
 * It provides methods to perform create, read, update, and delete (CRUD) functionality
 * for admission-related data within the system. This service acts as a contract to interact
 * with admission records in a consistent manner.
 */
@Service
public interface AdmissionService {
    Optional<AdmissionDto> getRecordById(Long id);

    List<AdmissionDto> getAllAdmissionRecords();

    AdmissionDto createNewRecord(AdmissionDto admissionDto);

    AdmissionDto updateEsistingAdmissionRecord(AdmissionDto admissionDto, Long id);

    AdmissionDto updateAdmissionRecordById(Long id, Map<String, Object> update);

    boolean deleteAdmissionRecordById(Long id);
}
