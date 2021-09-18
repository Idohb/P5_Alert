package com.safetynet.apps.service;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.jsoniter.annotation.JsonObject;
import com.safetynet.apps.controller.dto.FireStation.FireStationRequest;
import com.safetynet.apps.controller.dto.Person.PersonRequest;
import com.safetynet.apps.mapper.PersonConverter;
import com.safetynet.apps.model.entity.FireStationEntity;
import com.safetynet.apps.model.entity.MedicalRecordsEntity;
import com.safetynet.apps.model.entity.PersonEntity;
import com.safetynet.apps.model.repository.FireStationRepository;
import com.safetynet.apps.model.repository.PersonRepository;
import com.safetynet.apps.service.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.StringWriter;
import java.time.*;

import java.util.*;
import java.util.stream.Collectors;


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
        return personConverter.mapperPerson(personRepository.findAll());
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
                null, null);
        entity = personRepository.save(entity);


        // tentative de création de MedicalRecords dans la condition ci dessous
        // Les données de MedicalRecords sont récupérées par personRequest
        // qui possède MedicalRecordsRequest
        medicalRecordsService.addMedicalRecord(personRequest.getMedicalRecords(), entity);

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

        FireStationEntity fireStationEntity = new FireStationEntity(0L, null, null, personEntityList);
        FireStationRequest fireStationRequest = new FireStationRequest(null, null);
        fireStationService.addFireStation(fireStationRequest);
    }

    public Iterable<PersonEntity> addPersons(List<PersonEntity> persons) {
        return personRepository.saveAll(persons);
    }


    private Map<String, Object> matchPersonWithStation(List<FireStationEntity> fireStationEntityList) {
        final int AGE_ENFANT = 18;
        int adult_number = 0, child_number = 0;
        Map<String, Object> map = new HashMap<>();

        for (FireStationEntity fe : fireStationEntityList) {
            for (PersonEntity pe : fe.getPersonFireStation()) {
                List information = new ArrayList<>();
                information.add(pe.getAddress());
                information.add(pe.getPhone());
                map.put(pe.getFirstName() + " " + pe.getLastName(), information);
                if (evaluateAgeOfPerson(pe.getMedicalRecord().getBirthDate()) <= AGE_ENFANT) {
                    child_number++;
                } else {
                    adult_number++;
                }
            }
        }
        map.put("adult", adult_number);
        map.put("child", child_number);

        return map;
    }


    public Map<String, Object> getStation(String id) {
        return matchPersonWithStation(fireStationRepository.findByStation(id));
    }

//    private List<Person> matchChildrenWithAddress(List<PersonEntity> personEntityList) {
//
//        List<PersonEntity> personList = new ArrayList<>();
//
//        LocalDateTime localCurrentDateTime = LocalDateTime.now().minus(Period.ofYears(18));
//        Date date18YearsBefore = Date.from(localCurrentDateTime.toInstant(ZoneOffset.UTC));
//
//        for (PersonEntity pe : personEntityList) {
//            if (pe.getMedicalRecord()
//                    .getBirthDate()
//                    .after(date18YearsBefore)) {
//                personList.add(pe);
//            }
//        }
//
//        return personConverter.mapperPerson(personList);
//    }

//    private Map<String, Object> matchPersonWithStation(List<FireStationEntity> fireStationEntityList) {
//        Map<String, Object> map = new HashMap<>();
//
//        for (FireStationEntity fe : fireStationEntityList) {
//            for (PersonEntity pe : fe.getPersonFireStation()) {
//                List information = new ArrayList<>();
//                information.add(pe.getAddress());
//                information.add(pe.getPhone());
//                map.put(pe.getFirstName() + " " + pe.getLastName(), information);
//            }
//        }
//
//        return map;
//    }


    private Integer evaluateAgeOfPerson(Date birthdate) {
        LocalDate currentTime = LocalDate.now();
//        System.out.println("----");
//        System.out.println(birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//        System.out.println(currentTime);

        return Period.between(
                birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                currentTime
        ).getYears();
//        System.out.println("age : " + age);

        //return age;

    }


    private Map<String, Object> matchChildrenWithAddress(List<PersonEntity> personEntityList) {
        final int AGE_ENFANT = 18;

        Map<String, Object> map = new HashMap<>();

        for (PersonEntity pe : personEntityList) {
            int age = evaluateAgeOfPerson(pe.getMedicalRecord().getBirthDate());
            if (age <= AGE_ENFANT ) {
                Map<String, Object> information = new HashMap<>();
                List <String> family = new ArrayList<>();

                information.put( "Age", age
                );
                for (PersonEntity searchFamily : personEntityList) {
                    if (searchFamily.getLastName().equals(pe.getLastName()) && !searchFamily.getFirstName().equals(pe.getFirstName()))
                        family.add( searchFamily.getFirstName() + " " + searchFamily.getLastName());
                }
                information.put("family", family);

                map.put(pe.getFirstName() + " " + pe.getLastName(), information);
            }
        }

        return map;
    }

    public Map<String, Object> getChildAlert(String address) {
        return matchChildrenWithAddress(personRepository.findByAddress(address));
    }

    private Map<String, Object> matchPhoneNumberOfStation(List<FireStationEntity> fireStationEntityList) {
        Map<String, Object> map = new HashMap<>();

        for (FireStationEntity fe : fireStationEntityList) {
            for (PersonEntity pe : fe.getPersonFireStation())
                map.put(pe.getFirstName() + " " + pe.getLastName(), pe.getPhone());
        }

        return map;
    }

    public Map<String, Object> getPhoneNumberOfFireStation(String stationNumber) {
        return matchPhoneNumberOfStation(fireStationRepository.findByStation(stationNumber));
    }


    private Map<String, Object> matchPersonByAddress(List<PersonEntity> personEntityList) {
        Map<String, Object> map = new HashMap<>();

        for(PersonEntity pe : personEntityList) {
            Map<String, Object> info = new HashMap<>();
            info.put("phone", pe.getPhone());
            info.put("age", evaluateAgeOfPerson(pe.getMedicalRecord().getBirthDate()));
            info.put("allergies", pe.getMedicalRecord().getAllergies());
            info.put("medication", pe.getMedicalRecord().getMedications());

            List<String> station = new ArrayList<>();
            for (FireStationEntity fe : pe.getFireStationEntity())
                station.add(fe.getStation());

            info.put("station", station);
            map.put(pe.getFirstName() + " " + pe.getLastName() , info);
        }

        return  map;
    }

    public Map<String, Object> getFire(String address) {
        return matchPersonByAddress(personRepository.findByAddress(address));
    }


//    private List<PersonEntity> listPersonFromListOfStation(String stations) {
//        Map<String, Object> map = new HashMap<>();
//
//
//        List<PersonEntity> personEntityList = new ArrayList<>();
//
//        String[] listStations = stations.split(",");
//
//        for (String station : listStations) {
//
//            List<FireStationEntity> fireStationEntityList = fireStationRepository.findByStation(station);
//            for (FireStationEntity fe : fireStationEntityList) {
//                for (PersonEntity pe : fe.getPersonFireStation()) {
//                    personEntityList.add(pe);
//                }
//            }
////            map.put("station",personEntityList);
//
//        }
//        return personEntityList;
//    }
//
//    public List<Person> getListPersonFromListOfStation(String stations) {
//        return personConverter.mapperPerson(listPersonFromListOfStation(stations));
//    }

    private Map<String, Object> listPersonFromListOfStation(String stations) {
        Map<String, Object>map = new HashMap<>();

        String[] listStations = stations.split(",");

        for(String station : listStations) {
            List<FireStationEntity> fireStationEntityList = fireStationRepository.findByStation(station);
            Map<String, Object> mapAddress = new HashMap<>();

            for(FireStationEntity fe : fireStationEntityList) {
                List<PersonEntity> personList = fe.getPersonFireStation();
                Map<String, Object> mapName = new HashMap<>();
                for(PersonEntity pe : personList) {
                    Map<String, Object> mapInfo = new HashMap<>();
                    mapInfo.put("phone", pe.getPhone());
                    mapInfo.put("age", evaluateAgeOfPerson(pe.getMedicalRecord().getBirthDate()).toString());
                    mapInfo.put("medication", pe.getMedicalRecord().getMedications());
                    mapInfo.put("allergies", pe.getMedicalRecord().getAllergies());
                    mapName.put(pe.getFirstName() + " " + pe.getLastName(), mapInfo);
                 }

                mapAddress.put(fe.getAddress(), mapName);

            }

            map.put("station " + station, mapAddress);
        }
        return map;
    }
    public Map<String, Object> getListPersonFromListOfStation(String stations) {
        return listPersonFromListOfStation(stations);
    }

    private Map<String, Object> matchPersonInfoByFirstnameAndLastname(List<PersonEntity> personEntityList) {
        Map<String, Object> map = new HashMap<>();

        for (PersonEntity pe : personEntityList) {
            int age = evaluateAgeOfPerson(pe.getMedicalRecord().getBirthDate());
            Map<String, Object> information = new HashMap<>();
            List <String> family = new ArrayList<>();

            information.put( "Age", age
            );
            List<PersonEntity> personEntities = personRepository.findAll();
            for (PersonEntity searchFamily : personEntities) {
                if (searchFamily.getLastName().equals(pe.getLastName()) && !searchFamily.getFirstName().equals(pe.getFirstName()))
                    family.add( searchFamily.getFirstName() + " " + searchFamily.getLastName());
            }
            information.put("family", family);
            information.put("mail",pe.getEmail());
            information.put("medications",pe.getMedicalRecord().getMedications());
            information.put("allergies",pe.getMedicalRecord().getAllergies());
            information.put("address",pe.getAddress());
            map.put(pe.getFirstName() + " " + pe.getLastName(), information);

        }

        return  map;
    }


    public Map<String, Object> getListPersonInfoFromName(String firstName, String lastName) {
        return matchPersonInfoByFirstnameAndLastname(personRepository.findByFirstNameAndLastName(firstName, lastName));
    }


    private Map<String, Object> matchEmailFromCity(List<PersonEntity> personEntityList) {
        Map<String, Object> map = new HashMap<>();

        for (PersonEntity pe : personEntityList) {
            map.put(pe.getFirstName() + " " + pe.getLastName(), pe.getEmail());
        }

        return map;
    }

    public Map<String, Object> getEmailFromCity(String city) {
        return matchEmailFromCity(personRepository.findByCity(city));
    }
}