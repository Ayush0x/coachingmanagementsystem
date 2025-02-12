package com.example.university.rest.api.universitydata.services;

import com.example.university.rest.api.universitydata.dto.StudentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface StudentService {
    Optional<StudentDto> getStudentById(Long id);

    StudentDto createNewStudent(StudentDto studentdto);

    List<StudentDto> getAllStudents();

    StudentDto updateDetailsOfStudents(StudentDto studentdto, Long id);

    boolean deleteStudentById(Long id);

    StudentDto updateStudentById(Long id, Map<String, Object> update);
}
