package com.example.university.rest.api.universitydata.repositories;

import com.example.university.rest.api.universitydata.entities.AdmissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionRepository extends JpaRepository<AdmissionEntity,Long> {
}
