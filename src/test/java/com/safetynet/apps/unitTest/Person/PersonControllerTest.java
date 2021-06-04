package com.safetynet.apps.unitTest.Person;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import static java.util.Collections.singletonList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.apps.controller.PersonController;
import com.safetynet.apps.controller.PersonRequest;
import com.safetynet.apps.controller.PersonResponse;
import com.safetynet.apps.model.entity.MedicalRecordsEntity;
import com.safetynet.apps.model.entity.PersonEntity;
import com.safetynet.apps.service.PersonService;
import com.safetynet.apps.service.data.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;
    @MockBean
    private MedicalRecordsEntity medicalRecords;


    @Test
    void getPersons_ShouldReturnOK() throws Exception {
        List<Person> person = new ArrayList<>();
        when(personService.getPersons()).thenReturn(person);
        mockMvc.perform(get("/persons")).andExpect(status().isOk());
    }


    @Test
    void getPerson_ShouldReturnOK() throws Exception {
        //GIVEN
        Person person = new Person();
        when(personService.getPerson(any())).thenReturn(person);
        mockMvc.perform(get("/person/1")).andExpect(status().isOk());
    }


    @Test
    void createPerson_ShouldReturnOkStatus() throws Exception {
        ObjectMapper obj = new ObjectMapper();
        Person person = new Person();
        person.setId(1L);
        person.setFirstName("1");
        person.setCity("2");
        person.setAddress("3");
        person.setEmail("4");
        person.setPhone("5");
        person.setZip("6");
        person.setLastName("7");
        person.setMedicalRecords(null);

        when(personService.addPerson(any())).thenReturn(person);
        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(obj.writeValueAsString(person)))
                .andExpect(status().isOk());
    }




    @Test
    void createPerson_ShouldReturnBadRequest() throws Exception {
        when(personService.addPerson(any())).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(post("/person")).andExpect(status().isBadRequest());
    }

    @Test
    public void updatePerson_shouldReturnNoSuchElement() throws Exception {
        when(personService.updatePerson(any(), any())).thenThrow(NoSuchElementException.class);
        mockMvc.perform(put("/person/1").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isNotFound());
    }

    @Test
    public void updatePerson_shouldReturnOk() throws Exception {
        Person person = new Person();
        when(personService.updatePerson(any(), any())).thenReturn(person);
        mockMvc.perform(put("/person/1").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isOk());
    }

    @Test
    public void deletePerson_shouldReturnOk() throws Exception {
        Person person = new Person();
        doNothing().when(personService).deletePerson(any());
        when(personService.getPerson(any())).thenReturn(person);
        mockMvc.perform(delete("/person/1")).andExpect(status().isOk());
    }

    @Test
    public void deletePerson_shouldReturnNotFound() throws Exception {
        doNothing().when(personService).deletePerson(any());
        when(personService.getPerson(any())).thenThrow(NoSuchElementException.class);
        mockMvc.perform(delete("/person/1")).andExpect(status().isNotFound());
    }

    @Test
    public void deletePerson_shouldReturnBadRequest() throws Exception {
        doNothing().when(personService).deletePerson(any());
        when(personService.getPerson(any())).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(delete("/person/1")).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Test Exception to delete all Person")
    public void deletePersons_shouldReturnIllegalArgumentException() throws Exception {
        when(personService.getPersons()).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(delete("/persons")).andExpect(status().isNoContent());
    }

    @Test
    public void deletePersons_shouldReturnNoContent() throws Exception {
        PersonRequest person = new PersonRequest();
        personService.addPerson(person);
        mockMvc.perform(delete("/persons")).andExpect(status().isNoContent());
    }

}