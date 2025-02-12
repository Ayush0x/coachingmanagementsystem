package com.example.university.rest.api.universitydata.repositories;

import com.example.university.rest.api.universitydata.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity,Long> {
    Optional<CourseEntity> findByName(String name);
}
