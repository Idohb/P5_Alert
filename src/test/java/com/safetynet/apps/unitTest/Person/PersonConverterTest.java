package com.safetynet.apps.unitTest.Person;


import com.safetynet.apps.mapper.MedicalRecordsConverter;
import com.safetynet.apps.mapper.PersonConverter;
import com.safetynet.apps.model.entity.PersonEntity;
import com.safetynet.apps.service.data.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonConverterTest {

    @Mock
    private MedicalRecordsConverter medicalRecordsConverter;

    @InjectMocks
    private PersonConverter personConverter;

    @Test
    void mapperPerson_ShouldChangePersonEntityToPerson() {

        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(1L);
        personEntity.setFirstName("1");
        personEntity.setZip("2");
        personEntity.setPhone("3");
        personEntity.setAddress("4");
        personEntity.setCity("5");
        personEntity.setEmail("6");
        personEntity.setLastName("7");

        Person person = personConverter.mapperPerson( personEntity);

        assertThat(person.getId()).isEqualTo(1L);
        assertThat(person.getFirstName()).isEqualTo("1");
        assertThat(person.getZip()).isEqualTo("2");
        assertThat(person.getPhone()).isEqualTo("3");
        assertThat(person.getAddress()).isEqualTo("4");
        assertThat(person.getCity()).isEqualTo("5");
        assertThat(person.getEmail()).isEqualTo("6");
        assertThat(person.getLastName()).isEqualTo("7");


    }


//    @Test
//    void mapperPerson_ShouldChangeListPersonEntityToListPerson() {
//
//        List<PersonEntity> personEntity = new ArrayList<>();
//        PersonEntity personEntity1 = new PersonEntity(1L,"1","2","3","4","5","6","7",null);
//        PersonEntity personEntity2 = new PersonEntity(2L,"8","9","10","11","12","13","14",null);
//        PersonEntity personEntity3 = new PersonEntity(3L,"15","16","17","18","19","20","21",null);
//
//        personEntity.add(personEntity1);
//        personEntity.add(personEntity2);
//        personEntity.add(personEntity3);
//
//
//        List<Person> person = personConverter.mapperPerson( personEntity);
//
//        assertThat(person.get(0).getId()).isEqualTo(1L);
//        assertThat(person.get(0).getFirstName()).isEqualTo("1");
//        assertThat(person.get(0).getLastName()).isEqualTo("2");
//        assertThat(person.get(0).getAddress()).isEqualTo("3");
//        assertThat(person.get(0).getCity()).isEqualTo("4");
//        assertThat(person.get(0).getZip()).isEqualTo("5");
//        assertThat(person.get(0).getPhone()).isEqualTo("6");
//        assertThat(person.get(0).getEmail()).isEqualTo("7");
//
//
//    }


}
