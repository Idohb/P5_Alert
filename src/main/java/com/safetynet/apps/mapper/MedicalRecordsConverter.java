package com.safetynet.apps.mapper;

import com.safetynet.apps.model.entity.MedicalRecordsEntity;
import com.safetynet.apps.service.data.MedicalRecords;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicalRecordsConverter {

    
    public MedicalRecords mapperMedicalRecords(MedicalRecordsEntity medicalRecordsEntity) {
        MedicalRecords medicalRecords = new MedicalRecords();
        medicalRecords.setIdMedicalRecords(medicalRecordsEntity.getIdMedicalRecords());
        medicalRecords.setBirthdate(medicalRecordsEntity.getBirthDate());
        medicalRecords.setMedications(medicalRecordsEntity.getMedications());
        medicalRecords.setAllergies(medicalRecordsEntity.getAllergies());
        return medicalRecords;
    }

    public List<MedicalRecords> mapperMedicalRecords(List<MedicalRecordsEntity> medicalRecordsEntity) {
        return medicalRecordsEntity.stream().map(this::mapperMedicalRecords).collect(Collectors.toList());
    }
    
}
