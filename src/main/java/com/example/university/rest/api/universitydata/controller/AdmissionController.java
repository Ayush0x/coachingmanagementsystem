package com.example.university.rest.api.universitydata.controller;

import com.example.university.rest.api.universitydata.dto.AdmissionDto;
import com.example.university.rest.api.universitydata.dto.ProfessorDto;
import com.example.university.rest.api.universitydata.exceptions.ResourceNotFoundException;
import com.example.university.rest.api.universitydata.services.AdmissionService;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controller class for managing admission records. Provides endpoints to perform
 * CRUD operations on admission data of students.
 *
 * This controller interacts with {@link AdmissionService} to handle business logic
 * related to admission records.
 */
@RestController
@RequestMapping(path = "/admissionRecords")
//@AllArgsConstructor
@RequiredArgsConstructor
public class AdmissionController {

    private final AdmissionService admissionService;


@GetMapping(path = "/{admissionId}")
public ResponseEntity<AdmissionDto> getRecordById(@PathVariable Long id)
{
    Optional<AdmissionDto> admissionDto=admissionService.getRecordById(id);
    return admissionDto.map(admissionDto1 -> ResponseEntity.ok(admissionDto1))
            .orElseThrow(()-> new ResourceNotFoundException("Unable to fetch admission records of the student"));
}

@GetMapping
public ResponseEntity<List<AdmissionDto>> getAllAdmissionRecords()
{
    return ResponseEntity.ok(admissionService.getAllAdmissionRecords());
}

@PostMapping
public ResponseEntity<AdmissionDto> createNewRecord(@RequestBody AdmissionDto admissionDto)
{
    AdmissionDto createdNewRecord=admissionService.createNewRecord(admissionDto);
    return new ResponseEntity<>(createdNewRecord, HttpStatus.CREATED);
}

@PutMapping(path = "/{admissionId}")
public ResponseEntity<AdmissionDto> updateExistingAdmissionRecord(@RequestBody AdmissionDto admissionDto,@PathVariable Long id)
{
    return ResponseEntity.ok(admissionService.updateEsistingAdmissionRecord(admissionDto,id));
}

@PatchMapping(path = "/{admissionId}")
public ResponseEntity<AdmissionDto> updateAdmissionRecorddyId(@PathVariable Long id, @RequestBody Map<String,Object> update)
{
    AdmissionDto admissionDto=admissionService.updateAdmissionRecordById(id,update);
    if(admissionDto==null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(admissionDto);
}

@DeleteMapping(path = "/{admissionId}")
public ResponseEntity<Boolean> deleteAdmissionRecordById(@PathVariable Long id)
{
    boolean gotDeleted=admissionService.deleteAdmissionRecordById(id);
    if(gotDeleted) return ResponseEntity.ok(true);
    return ResponseEntity.notFound().build();
}

}