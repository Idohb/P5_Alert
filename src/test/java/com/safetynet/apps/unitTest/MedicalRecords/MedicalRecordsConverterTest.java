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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordsConverterTest {


    @InjectMocks
    private MedicalRecordsConverter medicalRecordsConverter;

    @Test
    void mapperMedicalRecords_ShouldChangeMedicalRecordsEntityToMedicalRecords() throws ParseException {

        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        MedicalRecordsEntity medicalRecordsEntity = new MedicalRecordsEntity();
        medicalRecordsEntity.setIdMedicalRecords(1L);
        medicalRecordsEntity.setBirthDate(sdf.parse("11/11/2020"));
        medicalRecordsEntity.setMedications(medications);
        medicalRecordsEntity.setAllergies(allergies);

        MedicalRecords medicalRecords = medicalRecordsConverter.mapperMedicalRecords( medicalRecordsEntity);

        assertThat(medicalRecords.getIdMedicalRecords()).isEqualTo(1L);
        assertThat(medicalRecords.getBirthdate()).isEqualTo("1");
        assertThat(medicalRecords.getMedications()).isEqualTo(medications);
        assertThat(medicalRecords.getAllergies()).isEqualTo(allergies);

    }

    @Test
    void mapperPerson_ShouldChangeListPersonEntityToListPerson() throws ParseException {

        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());



        List<MedicalRecordsEntity> medicalRecordsEntity = new ArrayList<>();
        MedicalRecordsEntity medicalRecordsEntity1 = new MedicalRecordsEntity(1L,null,sdf.parse("11/11/1998") , medications,allergies);
        MedicalRecordsEntity medicalRecordsEntity2 = new MedicalRecordsEntity(2L,null,sdf.parse("11/11/1968") , medications,allergies);
        MedicalRecordsEntity medicalRecordsEntity3 = new MedicalRecordsEntity(3L,null,sdf.parse("11/11/2003"), medications,allergies);

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
