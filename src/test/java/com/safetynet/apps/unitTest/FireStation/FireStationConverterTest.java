package com.safetynet.apps.unitTest.FireStation;

import com.safetynet.apps.mapper.FireStationConverter;
import com.safetynet.apps.model.entity.FireStationEntity;
import com.safetynet.apps.model.entity.PersonEntity;
import com.safetynet.apps.service.data.FireStation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class FireStationConverterTest {


    @InjectMocks
    private FireStationConverter fireStationConverter;

    @Test
    void mapperFireStation_ShouldChangeFireStationEntityToFireStation() {

        FireStationEntity fireStationEntity = new FireStationEntity();
        fireStationEntity.setIdFireStation(1L);
        fireStationEntity.setAddress("1");
        fireStationEntity.setStation("2");


        FireStation fireStation = fireStationConverter.mapperFireStation( fireStationEntity);

        assertThat(fireStation.getIdFireStation()).isEqualTo(1L);
        assertThat(fireStation.getAddress()).isEqualTo("1");
        assertThat(fireStation.getStation()).isEqualTo("2");



    }

    @Test
    void mapperPerson_ShouldChangeListPersonEntityToListPerson() {

        List<PersonEntity> personEntity = new ArrayList<>();


        List<FireStationEntity> fireStationEntity = new ArrayList<>();
        FireStationEntity fireStationEntity1 = new FireStationEntity(1L,"1","2",personEntity);
        FireStationEntity fireStationEntity2 = new FireStationEntity(2L,"2","3",personEntity);
        FireStationEntity fireStationEntity3 = new FireStationEntity(3L,"4","5",personEntity);


        fireStationEntity.add(fireStationEntity1);
        fireStationEntity.add(fireStationEntity2);
        fireStationEntity.add(fireStationEntity3);


        List<FireStation> fireStations = fireStationConverter.mapperFireStation( fireStationEntity);

        assertThat(fireStations.get(0).getIdFireStation()).isEqualTo(1L);
        assertThat(fireStations.get(0).getAddress()).isEqualTo("1");
        assertThat(fireStations.get(0).getStation()).isEqualTo("2");


    }



}
