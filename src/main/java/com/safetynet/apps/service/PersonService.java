package com.safetynet.apps.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsoniter.JsonIterator;
import com.safetynet.apps.controller.dto.Person.PersonRequest;
import com.safetynet.apps.mapper.MedicalRecordsConverter;
import com.safetynet.apps.mapper.PersonConverter;
import com.safetynet.apps.model.entity.MedicalRecordsEntity;
import com.safetynet.apps.model.entity.PersonEntity;
import com.safetynet.apps.model.repository.MedicalRecordsRepository;
import com.safetynet.apps.model.repository.PersonRepository;
import com.safetynet.apps.service.data.MedicalRecords;
import com.safetynet.apps.service.data.Person;
import org.hibernate.annotations.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonConverter personConverter;

    @Autowired
    MedicalRecordsService medicalRecordsService;

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
                null
        );
        entity = personRepository.save(entity);


        // tentative de création de MedicalRecords dans la condition ci dessous
        // Les données de MedicalRecords sont récupérées par personRequest
        // qui possède MedicalRecordsRequest
        if (personRequest.getMedicalRecords() != null) {
            medicalRecordsService.addMedicalRecord(personRequest.getMedicalRecords(),entity);
        }

        //faire une méthode qui fait un update de FireStation
        // selon la nouvelle adresse de Person.

        //!\\ //!\\//!\\//!\\//!\\//!\\//!\\//!\\//!\\//!\\//!\\//!\\
        //!\\ //!\\ Configurer une base de donnée de test //!\\ //!\\
        //!\\ //!\\ //!\\//!\\//!\\//!\\//!\\//!\\//!\\//!\\//!\\//!\\

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


    public Iterable<PersonEntity> addPersons(List<PersonEntity> persons) {
        return personRepository.saveAll(persons);
    }
}