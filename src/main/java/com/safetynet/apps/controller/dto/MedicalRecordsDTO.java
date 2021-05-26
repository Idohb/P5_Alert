package com.safetynet.apps.controller.dto;

import com.safetynet.apps.model.entity.MedicalRecordsEntity;
import lombok.Data;

import java.util.List;

@Data
public class MedicalRecordsDTO {

    private String birthdate;
    private List<String> allergies;
    private List<String> medications;

    public MedicalRecordsDTO(MedicalRecordsEntity medicalRecords) {
        birthdate = medicalRecords.getBirthDate();
        allergies = medicalRecords.getAllergies();
        medications = medicalRecords.getMedications();
    }
}
