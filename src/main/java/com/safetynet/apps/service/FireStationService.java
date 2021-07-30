package com.safetynet.apps.service;


import com.safetynet.apps.controller.dto.FireStation.FireStationRequest;
import com.safetynet.apps.mapper.FireStationConverter;
import com.safetynet.apps.model.entity.FireStationEntity;
import com.safetynet.apps.model.entity.PersonEntity;
import com.safetynet.apps.model.repository.FireStationRepository;
import com.safetynet.apps.model.repository.PersonRepository;
import com.safetynet.apps.service.data.FireStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FireStationService {

    @Autowired
    private FireStationRepository fireStationRepository;

    @Autowired
    private FireStationConverter fireStationConverter;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

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

    public void attributePersonToFireStation (List<PersonEntity> globalPersonEntityList, List<FireStationEntity> globalFireStationEntityList) {

        for (FireStationEntity fireStationEntity: globalFireStationEntityList) {

            String addressFireStation = fireStationEntity.getAddress();
            List<PersonEntity> personEntityList = new ArrayList<>();

            for(PersonEntity personEntity : globalPersonEntityList) {
                if (personEntity.getAddress().equals(addressFireStation)) {
                    personEntityList.add(personEntity);
                }
            }

            fireStationEntity.setPersonFireStation(personEntityList);

        }

    }

    public void listAllPersonFromFireStation(FireStationEntity fireStationEntity) {
        List<PersonEntity> personEntityList = fireStationEntity.getPersonFireStation();
        System.out.println("FireStation number : " + fireStationEntity.getStation() + " address : " + fireStationEntity.getAddress());
        for (PersonEntity personEntity : personEntityList) {
            System.out.println("Person : " + personEntity.getFirstName() + "/" + personEntity.getLastName() + " firestation " + personEntity.getFireStationEntity());

        }
    }


    public List<PersonEntity> matchAddressPersonFireStation(PersonEntity personEntity) {
        List<PersonEntity> personEntityList = new ArrayList<>();
        if(personEntity != null) {

            List<FireStationEntity> fireStationEntityList = fireStationRepository.findAll();
            for (FireStationEntity fireStationEntity: fireStationEntityList) {

                if (personEntity.getAddress().equals(fireStationEntity.getAddress())) {
                    personEntity.setFireStationEntity(fireStationEntityList);
                    personEntityList.add(personEntity);
                }
            }
        }

        return personEntityList;

    }

}
