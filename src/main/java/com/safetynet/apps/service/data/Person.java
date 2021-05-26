package com.safetynet.apps.service.data;

import lombok.Data;

@Data
public class Person {
    private Long id;
    private String firstName;
    private String city;
    private String lastName;
    private String address;
    private String phone;
    private String zip;
    private String email;
    private MedicalRecords medicalRecords;
}