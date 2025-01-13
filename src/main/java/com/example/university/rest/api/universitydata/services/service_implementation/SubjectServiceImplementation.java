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

@Service
@RequiredArgsConstructor
@AllArgsConstructor
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
