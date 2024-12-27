package com.example.university.rest.api.universitydata.services.service_implementation;

import com.example.university.rest.api.universitydata.dto.StudentDto;
import com.example.university.rest.api.universitydata.entities.StudentEntity;
import com.example.university.rest.api.universitydata.exceptions.ResourceNotFoundException;
import com.example.university.rest.api.universitydata.repositories.StudentRepository;
import com.example.university.rest.api.universitydata.services.StudentService;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImplementation implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<StudentDto> getStudentById(Long id) {
        log.info("Fetching student by id: {}",id);
        StudentEntity student=studentRepository.findById(id)
                .orElseThrow(()->{
                log.error("Student not found with id: "+id);
                return new ResourceNotFoundException("Student not found with id:"+id);
                });
            log.info("Student fetched with id: "+id);
        return Optional.ofNullable(modelMapper.map(student, StudentDto.class));
    }


    @Override
    public List<StudentDto> getAllStudents() {
        List<StudentEntity> students=studentRepository.findAll();
        return students.stream()
                .map(studentEntity -> modelMapper.map(studentEntity, StudentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto updateDetailsOfStudents(StudentDto studentdto, Long id) {
        isExisting(id);
        StudentEntity student=modelMapper.map(studentdto,StudentEntity.class);
        student.setStudentId(id);
        StudentEntity savedStudent=studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDto.class);
    }

    @Override
    public boolean deleteStudentById(Long id) {
        isExisting(id);
        studentRepository.deleteById(id);
        return true;
    }

    @Override
    public StudentDto updateStudentById(Long id, Map<String, Object> update) {
        isExisting(id);
        StudentEntity student=studentRepository.findById(id).get();
        update.forEach(
                (field,value)->{
                    Field fieldToBeUpdated= ReflectionUtils.findRequiredField(StudentEntity.class,field);
                    fieldToBeUpdated.setAccessible(true);
                    ReflectionUtils.setField(fieldToBeUpdated,student,value);
                }
        );
        return modelMapper.map(studentRepository.save(student), StudentDto.class);
    }

    public void isExisting(Long id){
    Boolean exists=studentRepository.existsById(id);
        if(!exists)
    {
        throw  new ResourceNotFoundException("Student with id:"+id+" does not exists");
    }
        }

    @Override
    public StudentDto createNewStudent(StudentDto studentdto) {
        Optional<StudentEntity> existingStudent=studentRepository.findById(studentdto.getStudentId());
        if(!existingStudent.isEmpty())
        {
            log.error("Student already exists");
            throw new RuntimeException("Student already exists");
        }
        StudentEntity newStudent=modelMapper.map(studentdto, StudentEntity.class);
        StudentEntity savedStudent=studentRepository.save(newStudent);
        return modelMapper.map(savedStudent, StudentDto.class);
    }
    
}
