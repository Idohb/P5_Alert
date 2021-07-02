package com.safetynet.apps.IntegrationTest.Person;

import com.safetynet.apps.controller.dto.Person.PersonRequest;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceIT {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private PersonConverter personConverter;

    @InjectMocks
    private PersonService personService;

    @Test
    void addPerson_ShouldChangeEntityFromPersonrequest() {
        Person person = new Person();
            person.setId(1L);
            person.setFirstName("8");
            person.setLastName("2");
            person.setAddress("3");
            person.setCity("4");
            person.setZip("5");
            person.setPhone("6");
            person.setEmail("7");
            person.setMedicalRecords(null);
        PersonEntity personEntityRequested = new PersonEntity(0L,"1","2","3","4","5","6","7",null);

        when(personRepository.save(any(PersonEntity.class))).thenReturn(personEntityRequested);
        when(personConverter.mapperPerson(any(PersonEntity.class))).thenReturn(person);
        person = personService.addPerson(personEntityRequested);
        System.out.println(person.getFirstName());
        verify(personConverter,times(1)).mapperPerson(any(PersonEntity.class));

    }



}
