package com.safetynet.apps.service.data;

import lombok.Data;

import java.util.List;

@Data
public class MedicalRecords {
    private Long idMedicalRecords;
    private String birthdate;
    private List<String> allergies;
    private List<String> medications;
}
