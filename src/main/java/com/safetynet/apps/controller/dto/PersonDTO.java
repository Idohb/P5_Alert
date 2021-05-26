package com.safetynet.apps.controller.dto;

import com.safetynet.apps.model.entity.PersonEntity;
import lombok.Data;

@Data
public class PersonDTO {

    public PersonDTO(PersonEntity person) {
        id = person.getId();
        firstName = person.getFirstName();
        lastName = person.getLastName();
        address = person.getAddress();
        phone = person.getPhone();
        city = person.getCity();
        zip = person.getZip();
        email = person.getEmail();
        if(person.getMedicalRecord() != null)
            medicalRecords = new MedicalRecordsDTO(person.getMedicalRecord());
//        fireStation = new FireStationDTO(person.getFireStation());
    }

    private Long id;
    private String firstName;
    private String city;
    private String lastName;
    private String address;
    private String phone;
    private String zip;
    private String email;
    private MedicalRecordsDTO medicalRecords;
//    private FireStationDTO fireStation;

}
