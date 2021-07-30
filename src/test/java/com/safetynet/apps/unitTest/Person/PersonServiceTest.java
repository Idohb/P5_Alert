//package com.safetynet.apps.unitTest.Person;
//
//import com.safetynet.apps.controller.dto.Person.PersonRequest;
//import com.safetynet.apps.mapper.PersonConverter;
//import com.safetynet.apps.model.entity.PersonEntity;
//import com.safetynet.apps.model.repository.PersonRepository;
//import com.safetynet.apps.service.PersonService;
//
//import com.safetynet.apps.service.data.Person;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.*;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class PersonServiceTest {
//
//
//    @Mock
//    private PersonRepository personRepository;
//    @Mock
//    private PersonConverter personConverter;
//    @InjectMocks
//    private PersonService personService;
//
//    @Test
//    void getPerson_ShouldThrowExceptionOnMissingPerson() {
//        // Given
//        // When
//        when(personRepository.findById(1L)).thenReturn(Optional.empty());
//        // Then
//        assertThrows(NoSuchElementException.class, () -> personService.getPerson(1L));
//    }
//
//    @Test
//    void addPerson_ShouldChangeEntityFromPersonrequest() {
//        PersonRequest personRequest = new PersonRequest("1","2","3","4","5","6","7", null);
//        PersonEntity personEntity = new PersonEntity(0L,"1","2","3","4","5","6","7", null);
//        Person person = new Person();
//        when(personRepository.save(any(PersonEntity.class))).thenReturn(personEntity);
//        when(personConverter.mapperPerson(personEntity)).thenReturn(person);
//
//        personService.addPerson(personRequest);
//        verify(personRepository,times(1)).save(any());
//    }
//
//
//
//
//    @Test
//    void updatePerson_ShouldChangeEntityFromPersonrequest() {
//        PersonEntity entity = new PersonEntity    (1L,"1","2","3","4","5","6","7",null);
//        PersonRequest personRequest = new PersonRequest("6","2","3","4","5","6","7",null);
//
//        when(personRepository.findById(any(Long.class))).thenReturn(Optional.of(entity));
//        when(personRepository.save(entity)).thenReturn(entity);
//        when(personConverter.mapperPerson(any(PersonEntity.class))).thenReturn(null);
//        personService.updatePerson(1L,personRequest);
//        assertEquals(entity.getFirstName(),"6");
//    }
//
//    @Test
//    void updateEntity_ShouldNotChangeEntityFromNullPersonrequest() {
//
//        PersonEntity entity = new PersonEntity(1L,"1","2","3","4","5","6","7",null);
//        PersonRequest personRequest = new PersonRequest();
//
//        when(personRepository.findById(any(Long.class))).thenReturn(Optional.of(entity));
//        when(personRepository.save(entity)).thenReturn(entity);
//        when(personConverter.mapperPerson(any(PersonEntity.class))).thenReturn(null);
//        personService.updatePerson(1L,personRequest);
//
//        //Then
//        assertEquals(entity.getFirstName()  ,"1");
//        assertEquals(entity.getLastName()   ,"2");
//        assertEquals(entity.getAddress()    ,"3");
//        assertEquals(entity.getCity()       ,"4");
//        assertEquals(entity.getZip()        ,"5");
//        assertEquals(entity.getPhone()      ,"6");
//        assertEquals(entity.getEmail()      ,"7");
//    }
//
//
//}