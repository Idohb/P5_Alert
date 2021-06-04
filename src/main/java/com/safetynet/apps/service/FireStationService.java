package com.safetynet.apps.service;


import com.safetynet.apps.controller.FireStationRequest;
import com.safetynet.apps.mapper.FireStationConverter;
import com.safetynet.apps.model.entity.FireStationEntity;
import com.safetynet.apps.model.repository.FireStationRepository;
import com.safetynet.apps.service.data.FireStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FireStationService {

    @Autowired
    private FireStationRepository fireStationRepository;

    @Autowired
    private FireStationConverter fireStationConverter;

    public List<FireStation> getFireStations() {
        return fireStationConverter.mapperFireStation( fireStationRepository.findAll());
    }

    public FireStation getFireStation(final Long id) {
        FireStationEntity fireStationEntity = fireStationRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        return fireStationConverter.mapperFireStation(fireStationEntity);
    }


    public void deleteFireStation(final Long id) {
        fireStationRepository.deleteById(id);
    }

    public void deleteFireStations() {
        fireStationRepository.deleteAll();
    }

    public FireStation addFireStation(FireStationRequest fireStationRequest) {
        FireStationEntity entity = new FireStationEntity();
        entity.setIdFireStation(0L);
        entity.setAddress(fireStationRequest.getAddress());
        entity.setStation(fireStationRequest.getStation());

        entity = fireStationRepository.save(entity);

        return fireStationConverter.mapperFireStation(entity);
    }


    public FireStation updateFireStation(final Long id, FireStationRequest fireStation) {
        FireStationEntity entity = fireStationRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        this.update(entity, fireStation);
        entity = fireStationRepository.save(entity);

        return fireStationConverter.mapperFireStation(entity);
    }

    public void update(FireStationEntity fireStationToUpdate, FireStationRequest fireStation) {

        if (fireStation.getStation() != null)
            fireStationToUpdate.setStation(fireStation.getStation());

        if (fireStation.getAddress() != null)
            fireStationToUpdate.setAddress(fireStation.getAddress());
    }

    public Iterable<FireStationEntity> addFireStations(List<FireStationEntity> fireStations) {
        return fireStationRepository.saveAll(fireStations);
    }

}
