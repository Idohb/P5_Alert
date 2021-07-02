package com.safetynet.apps.unitTest.MedicalRecords;

import com.safetynet.apps.controller.dto.MedicalRecords.MedicalRecordsRequest;
import com.safetynet.apps.controller.dto.Person.PersonRequest;
import com.safetynet.apps.mapper.MedicalRecordsConverter;
import com.safetynet.apps.model.entity.MedicalRecordsEntity;
import com.safetynet.apps.model.entity.PersonEntity;
import com.safetynet.apps.model.repository.MedicalRecordsRepository;
import com.safetynet.apps.service.MedicalRecordsService;
import com.safetynet.apps.service.data.MedicalRecords;
import com.safetynet.apps.service.data.Person;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class MedicalRecordsServiceTest {

    @Mock
    private MedicalRecordsRepository medicalRecordsRepository;
    @Mock
    private MedicalRecordsConverter medicalRecordsConverter;

    @InjectMocks
    private MedicalRecordsService medicalRecordsService;


    @Before
    public void setup() {
    }

    @BeforeEach
    void setUp() {
        medicalRecordsRepository.deleteAll();
    }

    @Test
    void getFireStation_ShouldThrowExceptionOnMissingFireStation() {
        // Given
        // When
        when(medicalRecordsRepository.findById(1L)).thenReturn(Optional.empty());
        // Then
        assertThrows(NoSuchElementException.class, () -> medicalRecordsService.getMedicalRecord(1L));
    }

    @Test
    void addPerson_ShouldChangeEntityFromPersonrequest() {
        List<String> medications = new ArrayList<>();
        List<String> allegies = new ArrayList<>();

        MedicalRecordsEntity entity = new MedicalRecordsEntity(0L,null,"6",medications,allegies);
        MedicalRecords medicalRecords = new MedicalRecords();


        MedicalRecordsRequest medicalRecordsRequest = new MedicalRecordsRequest("6",medications,allegies);
        Person person = new Person();
        when(medicalRecordsRepository.save(any(MedicalRecordsEntity.class))).thenReturn(entity);
        when(medicalRecordsConverter.mapperMedicalRecords(entity)).thenReturn(medicalRecords);
        MedicalRecords personResult = medicalRecordsService.addMedicalRecord(medicalRecordsRequest);
        assertEquals(entity.getBirthDate(),"6");
    }

    @Test
    void updatePerson_ShouldChangeEntityFromPersonrequest() {
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        MedicalRecordsEntity entity = new MedicalRecordsEntity(1L,null,"1",medications,allergies);
        MedicalRecordsRequest personRequest = new MedicalRecordsRequest("2",medications,allergies);

        when(medicalRecordsRepository.findById(any(Long.class))).thenReturn(Optional.of(entity));
        when(medicalRecordsRepository.save(entity)).thenReturn(entity);
        when(medicalRecordsConverter.mapperMedicalRecords(any(MedicalRecordsEntity.class))).thenReturn(null);
        medicalRecordsService.updateMedicalRecords(1L,personRequest);
        assertEquals(entity.getBirthDate(),"2");
    }

    @Test
    void updatePerson_ShouldNotChangeEntityFromPersonrequest() {
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        MedicalRecordsEntity entity = new MedicalRecordsEntity(1L,null,"1",medications,allergies);
        MedicalRecordsRequest personRequest = new MedicalRecordsRequest();

        when(medicalRecordsRepository.findById(any(Long.class))).thenReturn(Optional.of(entity));
        when(medicalRecordsRepository.save(entity)).thenReturn(entity);
        when(medicalRecordsConverter.mapperMedicalRecords(any(MedicalRecordsEntity.class))).thenReturn(null);
        medicalRecordsService.updateMedicalRecords(1L,personRequest);
        assertEquals(entity.getBirthDate(),"1");
    }



//        @Test
//        void updateMedicalRecords_ShouldChangeBirthDate() {
//            MedicalRecordsEntity medicalRecords = new MedicalRecordsEntity();
//            MedicalRecordsEntity medicalRecordsToUpdate = new MedicalRecordsEntity();
//
//            medicalRecords.setBirthDate("AZERTY");
//            medicalRecordsToUpdate.setBirthDate("QWERTY");
//            List<String> medications = new ArrayList<>();
//            medications.add("Test1");
//            medications.add("Test2");
//            medicalRecordsToUpdate.setMedications(medications);
//
//            when(medicalRecordsRepository.findById(1L)).thenReturn(Optional.of(medicalRecordsToUpdate));
//            when(medicalRecordsRepository.save(any())).thenReturn(null);
//
//            medicalRecordsService.updateMedicalRecords(1L, medicalRecords);
//
//            assertEquals(medicalRecordsToUpdate.getBirthDate(), "QWERTY");
//            assertEquals(medicalRecordsToUpdate.getMedications().get(0), "Test1");
//        }
//
//    @Test
//    void updateMedicalRecords_ShouldChangeFirstName() {
//        MedicalRecordsEntity medicalRecords = new MedicalRecordsEntity();
//        MedicalRecordsEntity medicalRecordsToUpdate = new MedicalRecordsEntity();
//
//        medicalRecords.setBirthDate("AZERTY");
//        List<String> medications = new ArrayList<>();
//        medications.add("Test1");
//        medications.add("Test2");
//
//        medicalRecords.setMedications(medications);
//        medications.set(0,"Test3");
//        medications.add("Test2");
//        medicalRecordsToUpdate.setMedications(medications);
//
//        when(medicalRecordsRepository.findById(1L)).thenReturn(Optional.of(medicalRecordsToUpdate));
//        when(medicalRecordsRepository.save(any())).thenReturn(null);
//
//        medicalRecordsService.updateMedicalRecords(1L, medicalRecords);
//
//        assertEquals(medicalRecordsToUpdate.getBirthDate(), "AZERTY");
//        assertEquals(medicalRecordsToUpdate.getMedications().get(0), "Test1");
//    }
//
//
//
//    @Test
//    void updateMedicalRecords_ShouldThrowExceptionOnMissingPerson() {
//
//    }

//    @Test
//    void updateMedicalRecords_ShouldChangeFirstName() {
//        MedicalRecords medicalRecords = new MedicalRecords();
//        MedicalRecords medicalRecordToUpdate = new MedicalRecords();
//
//        medicalRecords.getClePrimaire().setFirst_name("AZERTY");
//        medicalRecordToUpdate.getClePrimaire().setFirst_name("QWERTY");
//        medicalRecordToUpdate.getClePrimaire().setLast_name("AQWZSX");
//
//        when(medicalRecordsRepository.findById(1L)).thenReturn(Optional.of(medicalRecordToUpdate)); // les deux when te permet d'isoler tes tests.
//        when(medicalRecordsRepository.save(any())).thenReturn(null);
//
//        medicalRecordsService.updateMedicalRecords(1L, medicalRecords);
//
//        assertEquals(medicalRecordToUpdate.getClePrimaire().getFirst_name(), "AZERTY");
//        assertEquals(medicalRecordToUpdate.getClePrimaire().getLast_name(), "AQWZSX");
//    }


}
