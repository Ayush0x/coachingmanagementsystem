package com.example.university.rest.api.universitydata.services.service_implementation;


import com.example.university.rest.api.universitydata.dto.ProfessorDto;
import com.example.university.rest.api.universitydata.entities.ProfessorEntity;
import com.example.university.rest.api.universitydata.exceptions.ResourceNotFoundException;
import com.example.university.rest.api.universitydata.repositories.ProfessorRepository;
import com.example.university.rest.api.universitydata.services.ProfessorService;
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
//@AllArgsConstructor
@RequiredArgsConstructor
@Slf4j
public class ProfessorServiceImplementation implements ProfessorService {

    private final ProfessorRepository professorRepository;

    private final ModelMapper modelMapper;

    @Override
    public Optional<ProfessorDto> getProfessorById(Long id) {
        ProfessorEntity professor=professorRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Unable to fetch professor with id: "+id));
        return Optional.ofNullable(modelMapper.map(professor, ProfessorDto.class));
    }

    @Override
    public List<ProfessorDto> getAllProfessors() {
        List<ProfessorEntity> professorEntities=professorRepository.findAll();
        return professorEntities.stream()
                .map(professorEntity -> modelMapper.map(professorEntity, ProfessorDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public ProfessorDto createNewProfessor(ProfessorDto professorDto) {
        Optional<ProfessorEntity> existingProfessor =professorRepository.findById(professorDto.getProfessorId());
        if(!existingProfessor.isEmpty())
        {
            throw new RuntimeException("Unable to create new professor as the professor with id: "+professorDto.getProfessorId()+"Already exists");
        }
        ProfessorEntity createNewProfessor=modelMapper.map(professorDto, ProfessorEntity.class);
        ProfessorEntity createdProfessor=professorRepository.save(createNewProfessor);
        return modelMapper.map(createdProfessor, ProfessorDto.class);
    }

    public void isExisting(Long id)
    {
        boolean exists=professorRepository.existsById(id);
        if(!exists)
        {
            throw new ResourceNotFoundException("Professor with id: "+id+"does not exists");
        }
    }

    @Override
    public ProfessorDto updateExistingProfessor(ProfessorDto professorDto, Long id) {
        isExisting(id);
        ProfessorEntity professor=modelMapper.map(professorDto, ProfessorEntity.class);
        professor.setProfessorId(id);
        ProfessorEntity savedProfessor=professorRepository.save(professor);
        return modelMapper.map(savedProfessor,ProfessorDto.class);
    }

    @Override
    public Boolean deleteProfessorById(Long id) {
        isExisting(id);
        professorRepository.deleteById(id);
        return true;
    }

    @Override
    public ProfessorDto updateProfessorById(Long id, Map<String, Object> update) {
        isExisting(id);
        ProfessorEntity professor=professorRepository.findById(id).get();
        update.forEach((feild,value)->
        {
            Field fieldToBeUpdated= ReflectionUtils.findRequiredField(ProfessorEntity.class,feild);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,professor,value);
        });
        return modelMapper.map(professorRepository.save(professor), ProfessorDto.class);
    }
}
