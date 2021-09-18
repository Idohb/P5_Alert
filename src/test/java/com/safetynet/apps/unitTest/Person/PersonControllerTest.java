package com.safetynet.apps.unitTest.Person;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.MAP;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.apps.controller.PersonController;
import com.safetynet.apps.controller.dto.Person.PersonRequest;
import com.safetynet.apps.model.entity.PersonEntity;
import com.safetynet.apps.service.FireStationService;
import com.safetynet.apps.service.PersonService;
import com.safetynet.apps.service.data.FireStation;
import com.safetynet.apps.service.data.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;


@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;
    @MockBean
    private FireStationService fireStationService;


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
    void getPerson_ShouldReturnNoContent() throws Exception {
        //GIVEN
        when(personService.getPerson(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(get("/person/1")).andExpect(status().isNotFound());
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
    public void getListPersonFromListOfStations_shouldReturnOk() throws Exception {
        Map<String, Object> map = new HashMap<>();
        when(personService.getListPersonFromListOfStation(any())).thenReturn(map);
        mockMvc.perform(get("/flood/stations?stations=1,2")).andExpect(status().isOk());
    }

    @Test
    public void getPersonWithStation_shouldReturnOk() throws Exception {
        Map<String, Object> map = new HashMap<>();
        when(personService.getStation(any())).thenReturn(map);
        mockMvc.perform(get("/fireStation?station=3")).andExpect(status().isOk());
    }

    @Test
    public void getChildAlert_shouldReturnOk() throws Exception {
        Map<String, Object> map = new HashMap<>();
        when(personService.getChildAlert(any())).thenReturn(map);
        mockMvc.perform(get("/childAlert?address=1")).andExpect(status().isOk());
    }

    @Test
    public void getPhoneNumberOfFireStation_shouldReturnOk() throws Exception {
        Map<String, Object> map = new HashMap<>();
        when(personService.getPhoneNumberOfFireStation(any())).thenReturn(map);
        mockMvc.perform(get("/phoneAlert?station=3")).andExpect(status().isOk());
    }

    @Test
    public void getFire_shouldReturnOk() throws Exception {
        Map<String, Object> map = new HashMap<>();
        when(personService.getFire(any())).thenReturn(map);
        mockMvc.perform(get("/fire?address=3")).andExpect(status().isOk());
    }

    @Test
    public void getListPersonInfoFromName_shouldReturnOk() throws Exception {
        Map<String, Object> pl = new HashMap<>();
        when(personService.getListPersonInfoFromName(any(),any())).thenReturn(pl);
        mockMvc.perform(get("/personInfo?firstName=3&lastName=4")).andExpect(status().isOk());
    }

    @Test
    public void getEmailFromCity_shouldReturnOk() throws Exception {
        Map<String, Object> map = new HashMap<>();
        when(personService.getEmailFromCity(any())).thenReturn(map);
        mockMvc.perform(get("/communityEmail?city=3")).andExpect(status().isOk());
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
        PersonRequest personRequest = new PersonRequest();
        personService.addPerson(personRequest);
        mockMvc.perform(delete("/persons")).andExpect(status().isNoContent());
    }



}