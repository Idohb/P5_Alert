package com.safetynet.apps.unitTest.FireStation;

import com.safetynet.apps.model.entity.FireStationEntity;
import com.safetynet.apps.model.repository.FireStationRepository;
import com.safetynet.apps.service.FireStationService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FireStationServiceTest {

//
//    @Mock
//    private FireStationRepository fireStationRepository;
//
//    @InjectMocks
//    private FireStationService fireStationService;
//
//
//    @Before
//    public void setup() {
//    }
//
//    @BeforeEach
//    void setUp() {
//        fireStationRepository.deleteAll();
//    }
//
//    @Test
//    void getFireStation_ShouldThrowExceptionOnMissingFireStation() {
//        // Given
//        // When
//        when(fireStationRepository.findById(1L)).thenReturn(Optional.empty());
//        // Then
//        assertThrows(NoSuchElementException.class, () -> fireStationService.getFireStation(1L));
//    }
//
//    @Test
//    void updateFireStation_ShouldChangeAddress() {
//        FireStationEntity fireStation = new FireStationEntity();
//        FireStationEntity fireStationToUpdate = new FireStationEntity();
//
//        fireStation/*.getPersonFireStation()*/.setAddress("AZERTY");
//        fireStationToUpdate/*.getPersonFireStation()*/.setAddress("QWERTY");
//        fireStationToUpdate.setStation("1");
//
//        when(fireStationRepository.findById(1L)).thenReturn(Optional.of(fireStationToUpdate));
//        when(fireStationRepository.save(any())).thenReturn(null);
//
//        fireStationService.updateFireStation(1L, fireStation);
//
//        assertEquals(fireStationToUpdate/*.getPersonFireStation()*/.getAddress(), "AZERTY");
//        assertEquals(fireStationToUpdate.getStation(), 1);
//    }
//
//    @Test
//    void updateFireStation_ShouldNotChangeAddress() {
//        FireStationEntity fireStation = new FireStationEntity();
//        FireStationEntity fireStationToUpdate = new FireStationEntity();
//
//        fireStation/*.getPersonFireStation()*/.setAddress(null);
//        fireStationToUpdate/*.getPersonFireStation()*/.setAddress("QWERTY");
//        fireStationToUpdate.setStation("1");
//
//        when(fireStationRepository.findById(1L)).thenReturn(Optional.of(fireStationToUpdate));
//        when(fireStationRepository.save(any())).thenReturn(null);
//
//        fireStationService.updateFireStation(1L, fireStation);
//
//        assertEquals(fireStationToUpdate/*.getPersonFireStation()*/.getAddress(), "QWERTY");
//        assertEquals(fireStationToUpdate.getStation(), "1");
//    }
//
//    @Test
//    void updateFireStation_ShouldChangeStation() {
//        FireStationEntity fireStation = new FireStationEntity();
//        FireStationEntity fireStationToUpdate = new FireStationEntity();
//
//        fireStation.setStation("2");
//        fireStationToUpdate/*.getPersonFireStation()*/.setAddress("QWERTY");
//        fireStationToUpdate.setStation("1");
//
//        when(fireStationRepository.findById(1L)).thenReturn(Optional.of(fireStationToUpdate));
//        when(fireStationRepository.save(any())).thenReturn(null);
//
//        fireStationService.updateFireStation(1L, fireStation);
//
//        assertEquals(fireStationToUpdate/*.getPersonFireStation()*/.getAddress(), "QWERTY");
//        assertEquals(fireStationToUpdate.getStation(), "2");
//    }
//
//    @Test
//    void updateFireStation_ShouldNotChangeStation() {
//        FireStationEntity fireStation = new FireStationEntity();
//        FireStationEntity fireStationToUpdate = new FireStationEntity();
//
//        fireStation.setStation("0");
//        fireStationToUpdate/*.getPersonFireStation()*/.setAddress("QWERTY");
//        fireStationToUpdate.setStation("1");
//
//        when(fireStationRepository.findById(1L)).thenReturn(Optional.of(fireStationToUpdate));
//        when(fireStationRepository.save(any())).thenReturn(null);
//
//        fireStationService.updateFireStation(1L, fireStation);
//
//        assertEquals(fireStationToUpdate/*.getPersonFireStation()*/.getAddress(), "QWERTY");
//        assertEquals(fireStationToUpdate.getStation(), "1");
//    }


}
