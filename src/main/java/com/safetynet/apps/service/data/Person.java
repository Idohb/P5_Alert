package com.safetynet.apps.service.data;

import com.safetynet.apps.mapper.FireStationConverter;
import com.safetynet.apps.mapper.MedicalRecordsConverter;
import com.safetynet.apps.model.entity.FireStationEntity;
import com.safetynet.apps.model.entity.PersonEntity;
import lombok.Data;

import java.util.List;

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
    private List<FireStation> fireStation;

    public Person() {
    }

    public Person(PersonEntity p) {
        MedicalRecordsConverter medicalRecordsConverter = new MedicalRecordsConverter();
        FireStationConverter fireStationConverter = new FireStationConverter();
        setId(p.getId());
        setFirstName(p.getFirstName());
        setLastName(p.getLastName());
        setAddress(p.getAddress());
        setCity(p.getCity());
        setZip(p.getZip());
        setEmail(p.getEmail());
        setPhone(p.getPhone());
        setMedicalRecords(medicalRecordsConverter.mapperMedicalRecords(p.getMedicalRecord()));
        setFireStation(fireStationConverter.mapperFireStation(p.getFireStationEntity()));
    }

}