package com.safetynet.apps.service;

import com.safetynet.apps.model.Person;
import com.safetynet.apps.repository.PersonRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Optional<Person> getPerson(final Long id) {
        return personRepository.findById(id);
    }

    public Iterable<Person> getPersons() {
        return personRepository.findAll();
    }

    public void deletePerson(final Long id) {
        personRepository.deleteById(id);
    }

    public void deletePersons() {
        personRepository.deleteAll();
    }

    public Person savePerson(Person employee) {
        Person savedEmployee = personRepository.save(employee);
        return savedEmployee;
    }
}