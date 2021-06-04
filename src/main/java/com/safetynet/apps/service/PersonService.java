package com.safetynet.apps.service;

import com.safetynet.apps.controller.PersonRequest;
import com.safetynet.apps.mapper.PersonConverter;
import com.safetynet.apps.model.entity.PersonEntity;
import com.safetynet.apps.model.repository.PersonRepository;
import com.safetynet.apps.service.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonConverter personConverter;

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



    public Person addPerson(PersonRequest person) {
        PersonEntity entity = new PersonEntity();
        entity.setId(0L);
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setCity(person.getCity());
        entity.setZip(person.getZip());
        entity.setPhone(person.getPhone());
        entity.setEmail(person.getEmail());
        entity.setMedicalRecord(null);
        entity = personRepository.save(entity);

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