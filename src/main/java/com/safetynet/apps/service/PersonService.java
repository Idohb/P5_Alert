package com.safetynet.apps.service;


import com.safetynet.apps.controller.dto.FireStation.FireStationRequest;
import com.safetynet.apps.controller.dto.Person.PersonRequest;
import com.safetynet.apps.mapper.PersonConverter;
import com.safetynet.apps.model.entity.FireStationEntity;
import com.safetynet.apps.model.entity.PersonEntity;
import com.safetynet.apps.model.repository.FireStationRepository;
import com.safetynet.apps.model.repository.PersonRepository;
import com.safetynet.apps.service.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;

import java.util.*;


@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonConverter personConverter;

    @Autowired
    private MedicalRecordsService medicalRecordsService;

    @Autowired
    private FireStationService fireStationService;

    @Autowired
    private FireStationRepository fireStationRepository;

    public List<Person> getPersons() {
        return personConverter.mapperPerson( personRepository.findAll());
    }

    public Person getPerson(final Long id) {
        PersonEntity personEntity = personRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        return personConverter.mapperPerson(personEntity);
    }

    public void deletePerson(final Long id) {
        personRepository.deleteById(id);
    }

    public void deletePersons() {
        personRepository.deleteAll();
    }

    public Person addPerson(PersonRequest personRequest) {
        PersonEntity entity = new PersonEntity(0L,
                personRequest.getFirstName(),
                personRequest.getLastName(),
                personRequest.getAddress(),
                personRequest.getCity(),
                personRequest.getZip(),
                personRequest.getPhone(),
                personRequest.getEmail(),
                null,null);
        entity = personRepository.save(entity);


        // tentative de création de MedicalRecords dans la condition ci dessous
        // Les données de MedicalRecords sont récupérées par personRequest
        // qui possède MedicalRecordsRequest
        medicalRecordsService.addMedicalRecord(personRequest.getMedicalRecords(),entity);

        attributePersonToFireStation(entity);

        return personConverter.mapperPerson(entity);
    }

    public Person updatePerson(final Long id, PersonRequest personRequest) {

        PersonEntity entity = personRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        updateEntity(entity, personRequest);
        entity = personRepository.save(entity);
        return personConverter.mapperPerson(entity);

    }

    private void updateEntity(PersonEntity personEntity, PersonRequest personRequest) {

        if (personRequest.getFirstName() != null)
            personEntity.setFirstName(personRequest.getFirstName());

        if (personRequest.getLastName() != null)
            personEntity.setLastName(personRequest.getLastName());

        if (personRequest.getAddress() != null)
            personEntity.setAddress(personRequest.getAddress());

        if (personRequest.getCity() != null)
            personEntity.setCity(personRequest.getCity());

        if (personRequest.getEmail() != null)
            personEntity.setEmail(personRequest.getEmail());

        if (personRequest.getPhone() != null)
            personEntity.setPhone(personRequest.getPhone());

        if (personRequest.getZip() != null)
            personEntity.setZip(personRequest.getZip());

    }


    private void attributePersonToFireStation(PersonEntity personEntity) {
        List<PersonEntity> personEntityList = fireStationService.matchAddressPersonFireStation(personEntity);

        FireStationEntity fireStationEntity = new FireStationEntity(0L,null,null,personEntityList);
        FireStationRequest fireStationRequest = new FireStationRequest(null,null);
        fireStationService.addFireStation(fireStationRequest);
    }

    public Iterable<PersonEntity> addPersons(List<PersonEntity> persons) {
        return personRepository.saveAll(persons);
    }


    private List<Person> matchPersonWithStation(List<FireStationEntity> fireStationEntityList) {
        List<PersonEntity> personList = new ArrayList<>();
        for(FireStationEntity fe : fireStationEntityList) {
            List<PersonEntity> personEntityList = fe.getPersonFireStation();
            for(PersonEntity pe : personEntityList) {
                personList.add(pe);
            }
        }
        return personConverter.mapperPerson(personList);
    }

    public List<Person> getStation(String id) {
        return matchPersonWithStation(fireStationRepository.findByStation(id));
    }

    private List<Person> matchChildrenWithAddress(List<PersonEntity> personEntityList) {

        List<PersonEntity> personList = new ArrayList<>();

        LocalDateTime localCurrentDateTime = LocalDateTime.now().minus(Period.ofYears(18));
        Date date18YearsBefore = Date.from(localCurrentDateTime.toInstant(ZoneOffset.UTC));

        for(PersonEntity pe : personEntityList) {
            if(pe.getMedicalRecord()
                    .getBirthDate()
                    .after(date18YearsBefore))
            {
                personList.add(pe);
            }
        }

        return personConverter.mapperPerson(personList);
    }

    public List<Person> getChildAlert(String address) {
        return matchChildrenWithAddress(personRepository.findByAddress(address));
    }

    private Map<String, Object> matchPhoneNumberOfStation(List<FireStationEntity> fireStationEntityList) {
        Map<String, Object> map = new HashMap<>();

        for(FireStationEntity fe : fireStationEntityList) {
            for(PersonEntity pe : fe.getPersonFireStation())
                map.put(pe.getFirstName() + " " + pe.getLastName(), pe.getPhone());
        }

        return map;
    }

    public Map<String, Object> getPhoneNumberOfFireStation(String stationNumber) {
        return matchPhoneNumberOfStation(fireStationRepository.findByStation(stationNumber));
    }

    private Map<String, Object> matchPersonAlertForFireAtAddress(List<PersonEntity> personEntityList) {
        Map<String, Object> map = new HashMap<>();

        for(PersonEntity pe : personEntityList) {
            for (FireStationEntity fe : pe.getFireStationEntity())
                map.put(pe.getFirstName() + " " + pe.getLastName(), fe.getStation());
        }

        return map;
    }

    public List<Person> getFire(String address) {
        return personConverter.mapperPerson(personRepository.findByAddress(address));
    }

    private List<Person> listPersonFromListOfStation(String stations) {
        List<PersonEntity> personEntityList = new ArrayList<>();

        String [] listStations = stations.split(",");

        for (String station : listStations) {
            List<FireStationEntity> fireStationEntityList = fireStationRepository.findByStation(station);
            for (FireStationEntity fe : fireStationEntityList) {
                for( PersonEntity pe : fe.getPersonFireStation()){
                    personEntityList.add(pe);
                }
            }
        }
        return personConverter.mapperPerson(personEntityList);
    }

    public List<Person> getListPersonFromListOfStation(String stations) {
        return listPersonFromListOfStation(stations);
    }

    public List<Person> getListPersonInfoFromName(String firstName, String lastName) {
        return personConverter.mapperPerson(personRepository.findByFirstNameAndLastName(firstName,lastName));
    }

    private Map<String, Object> matchEmailFromCity(List<PersonEntity> personEntityList) {
        Map<String, Object> map = new HashMap<>();

        for(PersonEntity pe : personEntityList) {
                map.put(pe.getFirstName() + " " + pe.getLastName(), pe.getEmail());
        }

        return map;
    }


    public Map<String, Object> getEmailFromCity(String city) {
        return matchEmailFromCity(personRepository.findByCity(city));
    }
}