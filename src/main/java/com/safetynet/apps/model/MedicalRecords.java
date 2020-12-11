package com.safetynet.apps.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "medicalRecords")
public class MedicalRecords {
//        "medicalrecords": [
//    { "firstName":"John", "lastName":"Boyd", "birthdate":"03/06/1984", "medications":["aznol:350mg", "hydrapermazol:100mg"], "allergies":["nillacilan"] },

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstName")
    private String  firstName;

    @Column(name = "lastName")
    private String  lastName;

    @Column(name = "birthDate")
    private Date    birthDate;

    @Column(name = "medications")
    private String  medications;

    @Column(name = "allergies")
    private String  allergies;

}
