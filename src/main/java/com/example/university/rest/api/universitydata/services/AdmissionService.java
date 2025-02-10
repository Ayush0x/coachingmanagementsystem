package com.example.university.rest.api.universitydata.services;

import com.example.university.rest.api.universitydata.dto.AdmissionDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface AdmissionService {
    Optional<AdmissionDto> getRecordById(Long id);

    List<AdmissionDto> getAllAdmissionRecords();

    AdmissionDto createNewRecord(AdmissionDto admissionDto);

    AdmissionDto updateEsistingAdmissionRecord(AdmissionDto admissionDto, Long id);

    AdmissionDto updateAdmissionRecordById(Long id, Map<String, Object> update);

    boolean deleteAdmissionRecordById(Long id);
}
