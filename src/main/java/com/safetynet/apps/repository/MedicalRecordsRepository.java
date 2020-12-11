package com.safetynet.apps.repository;

import com.safetynet.apps.model.MedicalRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordsRepository extends JpaRepository<MedicalRecords, Long> {
}
