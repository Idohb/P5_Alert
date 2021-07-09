package com.safetynet.apps.controller.dto.Person;

import com.safetynet.apps.controller.dto.MedicalRecords.MedicalRecordsRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonRequest {

    private String firstName;

    private String lastName;

    private String address;

    private String city;

    private String zip;

    private String phone;

    private String email;

    private MedicalRecordsRequest medicalRecords;
}
