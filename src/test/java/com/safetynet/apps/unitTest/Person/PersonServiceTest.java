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

        PersonRequest personRequest = new PersonRequest(       "8","9","10","11","12","13","14");

        PersonEntity personEntity   = new PersonEntity(1L,  "1","2","3" ,"4" ,"5" ,"6" ,"7",null);

        //When
        method.setAccessible(true);
        method.invoke(personService, personEntity, personRequest);

        //Then
        assertEquals(personEntity.getFirstName()  ,"8");
        assertEquals(personEntity.getLastName()   ,"9");
        assertEquals(personEntity.getAddress()    ,"10");
        assertEquals(personEntity.getCity()       ,"11");
        assertEquals(personEntity.getZip()        ,"12");
        assertEquals(personEntity.getPhone()      ,"13");
        assertEquals(personEntity.getEmail()      ,"14");
    }

    @Test
    void updateEntity_ShouldNotChangeEntityFromNullPersonrequest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        //Given
        Method method = PersonService.class.getDeclaredMethod("updateEntity", PersonEntity.class, PersonRequest.class);

        PersonRequest personRequest = new PersonRequest(); // instantiate PersonRequest with no arguments to set it null;

        PersonEntity personEntity = new PersonEntity(1L,"1","2","3","4","5","6","7",null);

        //When
        method.setAccessible(true);
        method.invoke(personService, personEntity, personRequest);

        //Then
        assertEquals(personEntity.getFirstName()  ,"1");
        assertEquals(personEntity.getLastName()   ,"2");
        assertEquals(personEntity.getAddress()    ,"3");
        assertEquals(personEntity.getCity()       ,"4");
        assertEquals(personEntity.getZip()        ,"5");
        assertEquals(personEntity.getPhone()      ,"6");
        assertEquals(personEntity.getEmail()      ,"7");
    }

    @Test
    void updatePerson_ShouldChangeFirstName() {

    }

}