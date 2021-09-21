package com.safetynet.apps.model.repository;

import com.safetynet.apps.model.entity.MedicalRecordsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordsRepository extends JpaRepository<MedicalRecordsEntity, Long> {
}
