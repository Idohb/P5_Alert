package com.safetynet.apps.mapper;

import com.safetynet.apps.model.entity.FireStationEntity;
import com.safetynet.apps.service.data.FireStation;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FireStationConverter {

    public FireStation mapperFireStation(FireStationEntity fireStationEntity) {
        FireStation fireStation = new FireStation();
        fireStation.setIdFireStation(fireStationEntity.getIdFireStation());
        fireStation.setAddress(fireStationEntity.getAddress());
        fireStation.setStation(fireStationEntity.getStation());
        return fireStation;
    }

    public List<FireStation> mapperFireStation(List<FireStationEntity> fireStationEntities) {
        return fireStationEntities.stream().map(this::mapperFireStation).collect(Collectors.toList());
    }
}
