package com.safetynet.apps.mapper;

import com.safetynet.apps.model.entity.PersonEntity;
import com.safetynet.apps.service.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonConverter {
    @Autowired
    private MedicalRecordsConverter medicalRecordsConverter;

    public Person mapperPerson(PersonEntity personEntity) {
        Person person = new Person();

        person.setId(personEntity.getId());
        person.setAddress(personEntity.getAddress());
        person.setFirstName(personEntity.getFirstName());
        person.setLastName(personEntity.getLastName());
        person.setCity(personEntity.getCity());
        person.setZip(personEntity.getZip());
        person.setEmail(personEntity.getEmail());
        person.setPhone(personEntity.getPhone());

        if(personEntity.getMedicalRecord() != null) {
            System.out.println(personEntity.getMedicalRecord().getBirthDate());
            person.setMedicalRecords(medicalRecordsConverter.mapperMedicalRecords(personEntity.getMedicalRecord()));
        }

        return person;
    }

    public List<Person> mapperPerson(List<PersonEntity> personEntity) {
        return personEntity.stream().map(this::mapperPerson).collect(Collectors.toList());
    }

}
