package com.safetynet.apps.controller.dto.Person;

import com.safetynet.apps.model.entity.MedicalRecordsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonResponse {
    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String city;

    private String zip;

    private String phone;

    private String email;

    private MedicalRecordsEntity medicalRecord;
}
