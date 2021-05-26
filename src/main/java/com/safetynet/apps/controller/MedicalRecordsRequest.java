package com.safetynet.apps.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedicalRecordsRequest {

    private String birthdate;
    private List<String> medications;
    private List<String> allergies;
}

