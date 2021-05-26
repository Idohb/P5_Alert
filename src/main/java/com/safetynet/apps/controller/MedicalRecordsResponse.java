package com.safetynet.apps.controller;

import com.safetynet.apps.model.entity.PersonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedicalRecordsResponse {

    private Long idMedicalRecords;

    private PersonEntity personMedicalRecord;

    private String birthDate;
    private List<String> medications;
    private List<String> allergies;

}
