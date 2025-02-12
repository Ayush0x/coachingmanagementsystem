package com.example.university.rest.api.universitydata.controller;

import com.example.university.rest.api.universitydata.dto.ProfessorDto;
import com.example.university.rest.api.universitydata.exceptions.ResourceNotFoundException;
import com.example.university.rest.api.universitydata.services.ProfessorService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/professor")
//@AllArgsConstructor
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping(path = "/{professorId}")
    public ResponseEntity<ProfessorDto> getProfessorById(@PathVariable Long id)
    {
        Optional<ProfessorDto> professorDto=professorService.getProfessorById(id);
        return professorDto.map(ResponseEntity::ok)
                .orElseThrow(()-> new ResourceNotFoundException("Unable to fetch professor with id: "+id));
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDto>> getAllProfessors()
    {
        return ResponseEntity.ok(professorService.getAllProfessors());
    }

    @PostMapping
    public ResponseEntity<ProfessorDto> createNewProfessor(@RequestBody ProfessorDto professorDto)
    {
        ProfessorDto createdProfessor=professorService.createNewProfessor(professorDto);
        return new ResponseEntity<>(createdProfessor, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{professorId}")
    public ResponseEntity<ProfessorDto> updateExistingProfessor(@RequestBody ProfessorDto professorDto,@PathVariable Long id)
    {
        return ResponseEntity.ok(professorService.updateExistingProfessor(professorDto,id));
    }

    @DeleteMapping(path = "/{professorId}")
    public ResponseEntity<Boolean> deleteProfessorById(@PathVariable Long id)
    {
        boolean gotDeleted=professorService.deleteProfessorById(id);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{professorId}")
    public ResponseEntity<ProfessorDto> updateProfessorById(@PathVariable Long id, @RequestBody Map<String,Object> update)
    {
        ProfessorDto professorDto=professorService.updateProfessorById(id,update);
        if(professorDto==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(professorDto);
    }

}
