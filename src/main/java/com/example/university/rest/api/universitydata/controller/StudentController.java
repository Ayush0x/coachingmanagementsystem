package com.example.university.rest.api.universitydata.controller;

import com.example.university.rest.api.universitydata.dto.StudentDto;
import com.example.university.rest.api.universitydata.exceptions.ResourceNotFoundException;
import com.example.university.rest.api.universitydata.services.StudentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
//@AllArgsConstructor
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id)
    {
        Optional<StudentDto> studentdto=studentService.getStudentById(id);
        return studentdto.map(studentdto1 ->ResponseEntity.ok(studentdto1))
                .orElseThrow(()->new ResourceNotFoundException("Unable to fetch student with student id: "+id));
    }


    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents()
    {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping
    public ResponseEntity<StudentDto> createNewStudent(@RequestBody StudentDto studentdto)
    {
        StudentDto createdStudent=studentService.createNewStudent(studentdto);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{studentId}")
    public ResponseEntity<StudentDto> updateDetailsOfStudent(@RequestBody StudentDto studentdto, Long id)
    {
        return ResponseEntity.ok(studentService.updateDetailsOfStudents(studentdto,id));
    }

    @DeleteMapping(path = "/{studentId}")
    public ResponseEntity<Boolean> deleteStudentById(@PathVariable Long id)
    {
        boolean gotDeleted=studentService.deleteStudentById(id);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{studentId}")
    public ResponseEntity<StudentDto> updateStudentById(@PathVariable Long id, @RequestBody Map<String, Object> update)
    {
        StudentDto studentdto=studentService.updateStudentById(id,update);
        if(studentdto==null)return ResponseEntity.notFound().build();
        return ResponseEntity.ok(studentdto);
    }

}
