package com.safetynet.apps.unitTest.FireStation;

import com.safetynet.apps.controller.dto.FireStation.FireStationRequest;
import com.safetynet.apps.controller.dto.MedicalRecords.MedicalRecordsRequest;
import com.safetynet.apps.mapper.FireStationConverter;
import com.safetynet.apps.model.entity.FireStationEntity;
import com.safetynet.apps.model.entity.MedicalRecordsEntity;
import com.safetynet.apps.model.entity.PersonEntity;
import com.safetynet.apps.model.repository.FireStationRepository;
import com.safetynet.apps.service.FireStationService;
import com.safetynet.apps.service.data.FireStation;
import com.safetynet.apps.service.data.MedicalRecords;
import com.safetynet.apps.service.data.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FireStationServiceTest {


    @Mock
    private FireStationRepository fireStationRepository;

    @Mock
    private FireStationConverter fireStationConverter;

    @InjectMocks
    private FireStationService fireStationService;


    @Test
    void getPerson_ShouldThrowExceptionOnMissingPerson() {
        // Given
        // When
        when(fireStationRepository.findById(1L)).thenReturn(Optional.empty());
        // Then
        assertThrows(NoSuchElementException.class, () -> fireStationService.getFireStation(1L));
    }

    @Test
    void addPerson_ShouldChangeEntityFromPersonrequest() {

        FireStationEntity entity = new FireStationEntity(0L,"1","2", null);
        FireStation fireStation = new FireStation();


        FireStationRequest fireStationRequest = new FireStationRequest("1","2");
        when(fireStationRepository.save(any(FireStationEntity.class))).thenReturn(entity);
        when(fireStationConverter.mapperFireStation(entity)).thenReturn(fireStation);
        fireStationService.addFireStation(fireStationRequest);
        Assertions.assertEquals(entity.getAddress(),"1");
    }


    @Test
    void updatePerson_ShouldChangeEntityFromPersonrequest() {
        List<PersonEntity> personFireStation = new ArrayList<>();
        FireStationEntity entity = new FireStationEntity(1L,"1","2",personFireStation);
        FireStationRequest personRequest = new FireStationRequest("6","2");

        when(fireStationRepository.findById(any(Long.class))).thenReturn(Optional.of(entity));
        when(fireStationRepository.save(entity)).thenReturn(entity);
        when(fireStationConverter.mapperFireStation(any(FireStationEntity.class))).thenReturn(null);
        fireStationService.updateFireStation(1L,personRequest);
        Assertions.assertEquals(entity.getAddress(),"6");
    }

    @Test
    void updateEntity_ShouldNotChangeEntityFromNullPersonrequest() {

        List<PersonEntity> personFireStation = new ArrayList<>();
        FireStationEntity entity = new FireStationEntity(1L,"1","2",personFireStation);
        FireStationRequest personRequest = new FireStationRequest();

        when(fireStationRepository.findById(any(Long.class))).thenReturn(Optional.of(entity));
        when(fireStationRepository.save(entity)).thenReturn(entity);
        when(fireStationConverter.mapperFireStation(any(FireStationEntity.class))).thenReturn(null);
        fireStationService.updateFireStation(1L,personRequest);
        Assertions.assertEquals(entity.getAddress(),"1");
    }


}
