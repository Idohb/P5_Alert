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
    void createPerson_ShouldReturnPersonExpected() {

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


}
