package com.safetynet.apps.unitTest.Person;

import com.safetynet.apps.controller.PersonRequest;
import com.safetynet.apps.mapper.PersonConverter;
import com.safetynet.apps.model.entity.MedicalRecordsEntity;
import com.safetynet.apps.model.entity.PersonEntity;
import com.safetynet.apps.model.repository.PersonRepository;
import com.safetynet.apps.service.PersonService;
import com.safetynet.apps.service.data.MedicalRecords;
import com.safetynet.apps.service.data.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {


    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonConverter personConverter;

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


    @Test
    void updateEntity_ShouldChangeEntityFromPersonrequest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        //Given
        Method method = PersonService.class.getDeclaredMethod("updateEntity", PersonEntity.class, PersonRequest.class);

        PersonRequest personRequest = new PersonRequest("8","9","10","11","12","13","14");

        MedicalRecordsEntity medicalRecord = new MedicalRecordsEntity();
        PersonEntity entity = new PersonEntity(1L,"1","2","3","4","5","6","7",medicalRecord);

        //When
        method.setAccessible(true);
        method.invoke(personService, entity, personRequest);

        //Then
        assertEquals(entity.getFirstName()  , "8");
        assertEquals(entity.getLastName()   , "9");
    }

    @Test
    void updatePerson_ShouldChangeFirstName() {

    }

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