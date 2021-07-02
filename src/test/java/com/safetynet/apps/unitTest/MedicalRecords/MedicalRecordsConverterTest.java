package com.safetynet.apps.unitTest.MedicalRecords;

import com.safetynet.apps.mapper.MedicalRecordsConverter;
import com.safetynet.apps.mapper.PersonConverter;
import com.safetynet.apps.model.entity.MedicalRecordsEntity;
import com.safetynet.apps.model.entity.PersonEntity;
import com.safetynet.apps.service.data.MedicalRecords;
import com.safetynet.apps.service.data.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordsConverterTest {


    @InjectMocks
    private MedicalRecordsConverter medicalRecordsConverter;

    @Test
    void mapperMedicalRecords_ShouldChangeMedicalRecordsEntityToMedicalRecords() {

        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        MedicalRecordsEntity medicalRecordsEntity = new MedicalRecordsEntity();
        medicalRecordsEntity.setIdMedicalRecords(1L);
        medicalRecordsEntity.setBirthDate("1");
        medicalRecordsEntity.setMedications(medications);
        medicalRecordsEntity.setAllergies(allergies);


        MedicalRecords medicalRecords = medicalRecordsConverter.mapperMedicalRecords( medicalRecordsEntity);

        assertThat(medicalRecords.getIdMedicalRecords()).isEqualTo(1L);
        assertThat(medicalRecords.getBirthdate()).isEqualTo("1");
        assertThat(medicalRecords.getMedications()).isEqualTo(medications);
        assertThat(medicalRecords.getAllergies()).isEqualTo(allergies);



    }

    @Test
    void mapperPerson_ShouldChangeListPersonEntityToListPerson() {

        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();


        List<MedicalRecordsEntity> medicalRecordsEntity = new ArrayList<>();
        MedicalRecordsEntity medicalRecordsEntity1 = new MedicalRecordsEntity(1L,null,"1" , medications,allergies);
        MedicalRecordsEntity medicalRecordsEntity2 = new MedicalRecordsEntity(2L,null,"8" , medications,allergies);
        MedicalRecordsEntity medicalRecordsEntity3 = new MedicalRecordsEntity(3L,null,"15", medications,allergies);

        medicalRecordsEntity.add(medicalRecordsEntity1);
        medicalRecordsEntity.add(medicalRecordsEntity2);
        medicalRecordsEntity.add(medicalRecordsEntity3);


        List<MedicalRecords> medicalRecords = medicalRecordsConverter.mapperMedicalRecords( medicalRecordsEntity);

        assertThat(medicalRecords.get(0).getIdMedicalRecords()).isEqualTo(1L);
        assertThat(medicalRecords.get(0).getBirthdate()).isEqualTo("1");
        assertThat(medicalRecords.get(0).getMedications()).isEqualTo(medications);
        assertThat(medicalRecords.get(0).getAllergies()).isEqualTo(allergies);


    }



}
