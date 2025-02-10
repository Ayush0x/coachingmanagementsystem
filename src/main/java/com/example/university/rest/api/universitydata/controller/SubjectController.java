package com.example.university.rest.api.universitydata.controller;

import com.example.university.rest.api.universitydata.dto.AdmissionDto;
import com.example.university.rest.api.universitydata.dto.SubjectDto;
import com.example.university.rest.api.universitydata.exceptions.ResourceNotFoundException;
import com.example.university.rest.api.universitydata.services.SubjectService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/subjectDetails")
//@AllArgsConstructor
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping(path = "/{subjectId}")
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable Long id)
    {
        Optional<SubjectDto> subjectDto=subjectService.getSubjectById(id);
        return subjectDto.map(subjectDto1 -> ResponseEntity.ok(subjectDto1))
                .orElseThrow(()-> new ResourceNotFoundException("Unable to fetch the subject"));
    }

    @GetMapping
    public ResponseEntity<List<SubjectDto>> getAllSubjects()
    {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @PostMapping
    public ResponseEntity<SubjectDto> createNewSubject(@RequestBody SubjectDto subjectDto)
    {
        SubjectDto createdNewSubject=subjectService.createNewSubject(subjectDto);
        return new ResponseEntity<>(createdNewSubject, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{subjectId}")
    public ResponseEntity<SubjectDto> updateExistingSubject(@RequestBody SubjectDto subjectDto,@PathVariable Long id)
    {
        return ResponseEntity.ok(subjectService.updateEsistingSubject(subjectDto,id));
    }

    @PatchMapping(path = "/{subjectId}")
    public ResponseEntity<SubjectDto> updateSubjectById(@PathVariable Long id, @RequestBody Map<String,Object> update)
    {
        SubjectDto subjectDto=subjectService.updateSubjectById(id,update);
        if(subjectDto==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(subjectDto);
    }

    @DeleteMapping(path = "/{subjectId}")
    public ResponseEntity<Boolean> deleteSubjectById(@PathVariable Long id)
    {
            boolean gotDeleted=subjectService.deleteSubjectById(id);
            if(gotDeleted) return ResponseEntity.ok(true);
            return ResponseEntity.notFound().build();
    }

}
