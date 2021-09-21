package com.safetynet.apps.service.data;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MedicalRecords {
    private Long idMedicalRecords;
    private Date birthdate;
    private List<String> allergies;
    private List<String> medications;
}
