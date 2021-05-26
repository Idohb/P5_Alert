package com.safetynet.apps.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
public class MedicalRecordsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedicalRecords;

    @OneToOne(fetch = FetchType.LAZY)
    private PersonEntity personMedicalRecord;

    @Column
    private String birthDate;

    @ElementCollection
    private List<String> medications;
    @ElementCollection
    private List<String> allergies;

}
