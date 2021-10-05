package com.safetynet.apps.controller.dto.MedicalRecords;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedicalRecordsRequest {

    private Date birthdate;
    private List<String> medications;
    private List<String> allergies;
}

