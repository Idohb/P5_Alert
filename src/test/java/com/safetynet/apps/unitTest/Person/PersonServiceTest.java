package com.safetynet.apps.unitTest.Person;

import com.safetynet.apps.controller.PersonRequest;
import com.safetynet.apps.mapper.PersonConverter;
import com.safetynet.apps.model.entity.PersonEntity;
import com.safetynet.apps.model.repository.PersonRepository;
import com.safetynet.apps.service.PersonService;
import com.safetynet.apps.service.data.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {


    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void getPerson_ShouldThrowExceptionOnMissingPerson() {
        // Given
        // When
        when(personRepository.findById(1L)).thenReturn(Optional.empty());
        // Then
        assertThrows(NoSuchElementException.class, () -> personService.getPerson(1L));
    }


//    @Test
//    void createPerson_ShouldReturnPersonExpected() {
//        Person person = new Person();
//        person.setId(1L);
//
//        PersonEntity personEntity = new PersonEntity();
//        personEntity.setFirstName("1");
//        personEntity.setZip("2");
//        personEntity.setPhone("3");
//        personEntity.setAddress("4");
//        personEntity.setCity("5");
//        personEntity.setEmail("6");
//        personEntity.setLastName("7");
//
//        when(personRepository.save(any())).thenReturn(personEntity);
//
//
//        person = personConverter.mapperPerson( personEntity);
//
//        assertThat(person.getFirstName()).isEqualTo("1");
//        assertThat(person.getZip()).isEqualTo("2");
//        assertThat(person.getPhone()).isEqualTo("3");
//        assertThat(person.getAddress()).isEqualTo("4");
//        assertThat(person.getCity()).isEqualTo("5");
//        assertThat(person.getEmail()).isEqualTo("6");
//        assertThat(person.getLastName()).isEqualTo("7");
//
//
//    }


//    @Test
//    void updatePerson_ShouldThrowExceptionOnMissingPerson() {
//
//    }
//
//    @Test
//    void updatePerson_ShouldChangeFirstName() {
//        PersonEntity personEntity = new PersonEntity();
//        personEntity.setFirstName("titi");
//
//        Person person = new Person();
//
//        PersonRequest personRequest = new PersonRequest();
//        personRequest.setFirstName("toto");
//        personRequest.setLastName("tutu");
//
//        when(personRepository.findById(1L)).thenReturn(Optional.of(personEntity));
//        when(personRepository.save(any())).thenReturn(null);
////        when(personConverter.mapperPerson(any())).thenReturn(null);
//
//        personService.updatePerson(1L, personRequest);
//
//        assertEquals(personEntity.getFirstName(), "AZERTY");
//        assertEquals(personEntity.getLastName(), "AQWZSX");
//
//
//    }


//    @Test
//    void updatePerson_ShouldChangeFirstName() {
//        MedicalRecords medicalRecords = new MedicalRecords();
//        Person person = new Person(1L,"1","2","3","4","5","6","7",medicalRecords/*, fireStation*/);
//        person.setFirstName("AZERTY");
//
//        PersonDTO personToUpdate = new PersonDTO(person);
//
//        personToUpdate.setFirstName("QWERTY");
//        personToUpdate.setLastName("AQWZSX");
//
//        when(personRepository.findById(1L).map(p -> new PersonDTO(p)).get()).thenReturn(personToUpdate);
//        when(personRepository.save(any())).thenReturn(null);
//
//        personService.updatePerson(1L, personToUpdate);
//
//        assertEquals(personToUpdate.getFirstName(), "AZERTY");
//        assertEquals(personToUpdate.getLastName(), "AQWZSX");
//    }

//    @Test
//    void updatePerson_ShouldChangeLastName() {
//        Person person = new Person();
//        Person personToUpdate = new Person();
//
//        person.setLastName("AZERTY");
//        personToUpdate.setFirstName("QWERTY");
//        personToUpdate.setLastName("AQWZSX");
//
//        when(personRepository.findById(1L)).thenReturn(Optional.of(personToUpdate));
//        when(personRepository.save(any())).thenReturn(null);
//
//        personService.updatePerson(1L, person);
//
//        assertEquals(personToUpdate.getFirstName(), "QWERTY");
//        assertEquals(personToUpdate.getLastName(), "AZERTY");
//    }
//
//    @Test
//    void updatePerson_ShouldChangeAddress() {
//        Person person = new Person();
//        Person personToUpdate = new Person();
//
//        person.setAddress("AZERTY");
//        personToUpdate.setFirstName("QWERTY");
//        personToUpdate.setAddress("AQWZSX");
//
//        when(personRepository.findById(1L)).thenReturn(Optional.of(personToUpdate));
//        when(personRepository.save(any())).thenReturn(null);
//
//        personService.updatePerson(1L, person);
//
//        assertEquals(personToUpdate.getFirstName(), "QWERTY");
//        assertEquals(personToUpdate.getAddress(), "AZERTY");
//    }
//
//    //city
//    @Test
//    void updatePerson_ShouldChangeCity() {
//        Person person = new Person();
//        Person personToUpdate = new Person();
//
//        person.setCity("AZERTY");
//        personToUpdate.setFirstName("QWERTY");
//        personToUpdate.setCity("AQWZSX");
//
//        when(personRepository.findById(1L)).thenReturn(Optional.of(personToUpdate));
//        when(personRepository.save(any())).thenReturn(null);
//
//        personService.updatePerson(1L, person);
//
//        assertEquals(personToUpdate.getFirstName(), "QWERTY");
//        assertEquals(personToUpdate.getCity(), "AZERTY");
//    }
//
//    //Zip
//    @Test
//    void updatePerson_ShouldChangeZip() {
//        Person person = new Person();
//        Person personToUpdate = new Person();
//
//        person.setZip("2");
//        personToUpdate.setFirstName("QWERTY");
//        personToUpdate.setZip("1");
//
//        when(personRepository.findById(1L)).thenReturn(Optional.of(personToUpdate));
//        when(personRepository.save(any())).thenReturn(null);
//
//        personService.updatePerson(1L, person);
//
//        assertEquals(personToUpdate.getFirstName(), "QWERTY");
//        assertEquals(personToUpdate.getZip(), "2");
//    }
//
//    //Phone
//    @Test
//    void updatePerson_ShouldChangePhone() {
//        Person person = new Person();
//        Person personToUpdate = new Person();
//
//        person.setPhone("AZERTY");
//        personToUpdate.setFirstName("QWERTY");
//        personToUpdate.setPhone("AQWZSX");
//
//        when(personRepository.findById(1L)).thenReturn(Optional.of(personToUpdate));
//        when(personRepository.save(any())).thenReturn(null);
//
//        personService.updatePerson(1L, person);
//
//        assertEquals(personToUpdate.getFirstName(), "QWERTY");
//        assertEquals(personToUpdate.getPhone(), "AZERTY");
//    }
//
//
//    //mail
//    @Test
//    void updatePerson_ShouldChangeEmail() {
//        Person person = new Person();
//        Person personToUpdate = new Person();
//
//        person.setEmail("AZERTY");
//        personToUpdate.setFirstName("QWERTY");
//        personToUpdate.setEmail("AQWZSX");
//
//        when(personRepository.findById(1L)).thenReturn(Optional.of(personToUpdate));
//        when(personRepository.save(any())).thenReturn(null);
//
//        personService.updatePerson(1L, person);
//
//        assertEquals(personToUpdate.getFirstName(), "QWERTY");
//        assertEquals(personToUpdate.getEmail(), "AZERTY");
//    }
//
//    @Test
//    void updatePerson_ShouldNotChangeFirstName() {
//        Person person = new Person();
//        Person personToUpdate = new Person();
//
//        person.setFirstName(null);
//        personToUpdate.setFirstName("QWERTY");
//        personToUpdate.setLastName("AQWZSX");
//
//        when(personRepository.findById(1L)).thenReturn(Optional.of(personToUpdate));
//        when(personRepository.save(any())).thenReturn(null);
//
//        personService.updatePerson(1L, person);
//
//        assertEquals(personToUpdate.getFirstName(), "QWERTY");
//        assertEquals(personToUpdate.getLastName(), "AQWZSX");
//    }
//
//    @Test
//    void updatePerson_ShouldNotChangeLastName() {
//        Person person = new Person();
//        Person personToUpdate = new Person();
//
//        person.setLastName(null);
//        personToUpdate.setFirstName("QWERTY");
//        personToUpdate.setLastName("AQWZSX");
//
//        when(personRepository.findById(1L)).thenReturn(Optional.of(personToUpdate));
//        when(personRepository.save(any())).thenReturn(null);
//
//        personService.updatePerson(1L, person);
//
//        assertEquals(personToUpdate.getFirstName(), "QWERTY");
//        assertEquals(personToUpdate.getLastName(), "AQWZSX");
//    }
//
//    @Test
//    void updatePerson_ShouldNotChangeAddress() {
//        Person person = new Person();
//        Person personToUpdate = new Person();
//
//        person.setAddress(null);
//        personToUpdate.setFirstName("QWERTY");
//        personToUpdate.setAddress("AQWZSX");
//
//        when(personRepository.findById(1L)).thenReturn(Optional.of(personToUpdate));
//        when(personRepository.save(any())).thenReturn(null);
//
//        personService.updatePerson(1L, person);
//
//        assertEquals(personToUpdate.getFirstName(), "QWERTY");
//        assertEquals(personToUpdate.getAddress(), "AQWZSX");
//    }
//
//    //city
//    @Test
//    void updatePerson_ShouldNotChangeCity() {
//        Person person = new Person();
//        Person personToUpdate = new Person();
//
//        person.setCity(null);
//        personToUpdate.setFirstName("QWERTY");
//        personToUpdate.setCity("AQWZSX");
//
//        when(personRepository.findById(1L)).thenReturn(Optional.of(personToUpdate));
//        when(personRepository.save(any())).thenReturn(null);
//
//        personService.updatePerson(1L, person);
//
//        assertEquals(personToUpdate.getFirstName(), "QWERTY");
//        assertEquals(personToUpdate.getCity(), "AQWZSX");
//    }
//
//    //Zip
//    @Test
//    void updatePerson_ShouldNotChangeZip() {
//        Person person = new Person();
//        Person personToUpdate = new Person();
//
//        person.setZip(null);
//        personToUpdate.setFirstName("QWERTY");
//        personToUpdate.setZip("1");
//
//        when(personRepository.findById(1L)).thenReturn(Optional.of(personToUpdate));
//        when(personRepository.save(any())).thenReturn(null);
//
//        personService.updatePerson(1L, person);
//
//        assertEquals(personToUpdate.getFirstName(), "QWERTY");
//        assertEquals(personToUpdate.getZip(), "1");
//    }
//
//    //Phone
//    @Test
//    void updatePerson_ShouldNotChangePhone() {
//        Person person = new Person();
//        Person personToUpdate = new Person();
//
//        person.setPhone(null);
//        personToUpdate.setFirstName("QWERTY");
//        personToUpdate.setPhone("AQWZSX");
//
//        when(personRepository.findById(1L)).thenReturn(Optional.of(personToUpdate));
//        when(personRepository.save(any())).thenReturn(null);
//
//        personService.updatePerson(1L, person);
//
//        assertEquals(personToUpdate.getFirstName(), "QWERTY");
//        assertEquals(personToUpdate.getPhone(), "AQWZSX");
//    }
//
//
//    //mail
//    @Test
//    void updatePerson_ShouldNotChangeEmail() {
//        Person person = new Person();
//        Person personToUpdate = new Person();
//
//        person.setEmail(null);
//        personToUpdate.setFirstName("QWERTY");
//        personToUpdate.setEmail("AQWZSX");
//
//        when(personRepository.findById(1L)).thenReturn(Optional.of(personToUpdate));
//        when(personRepository.save(any())).thenReturn(null);
//
//        personService.updatePerson(1L, person);
//
//        assertEquals(personToUpdate.getFirstName(), "QWERTY");
//        assertEquals(personToUpdate.getEmail(), "AQWZSX");
//    }

}