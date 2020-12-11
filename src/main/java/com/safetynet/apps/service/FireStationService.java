package com.safetynet.apps.service;


import com.safetynet.apps.model.FireStation;
import com.safetynet.apps.model.Person;
import com.safetynet.apps.repository.FireStationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class FireStationService {

    @Autowired
    private FireStationRepository fireStationRepository;

    public Optional<FireStation> getFireStation(final Long id) {
        return fireStationRepository.findById(id);
    }

    public Iterable<FireStation> getFireStations() {
        return fireStationRepository.findAll();
    }

    public void deleteFireStation(final Long id) {
        fireStationRepository.deleteById(id);
    }

    public void deleteFireStations() {
        fireStationRepository.deleteAll();
    }

    public FireStation saveFireStation(FireStation employee) {
        FireStation savedEmployee = fireStationRepository.save(employee);
        return savedEmployee;
    }
}
