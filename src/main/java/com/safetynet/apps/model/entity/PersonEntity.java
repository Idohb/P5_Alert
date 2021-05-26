package com.safetynet.apps.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
public class PersonEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String zip;

    @Column
    private String phone;

    @Column
    private String email;

    @OneToOne(mappedBy = "personMedicalRecord")
    private MedicalRecordsEntity medicalRecord;

}
