package com.safetynet.apps.unitTest.FireStation;


import static org.mockito.ArgumentMatchers.any;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.apps.controller.FireStationController;
import com.safetynet.apps.controller.dto.FireStation.FireStationRequest;
import com.safetynet.apps.service.FireStationService;
import com.safetynet.apps.service.data.FireStation;
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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = FireStationController.class)
public class FireStationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationService fireStationService;


    @Test
    void getFireStations_ShouldReturnOK() throws Exception {
        List<FireStation> fireStation = new ArrayList<>();
        when(fireStationService.getFireStations()).thenReturn(fireStation);
        mockMvc.perform(get("/fireStations")).andExpect(status().isOk());
    }


    @Test
    void getFireStation_ShouldReturnOK() throws Exception {
        //GIVEN
        FireStation fireStation = new FireStation();
        when(fireStationService.getFireStation(any())).thenReturn(fireStation);
        mockMvc.perform(get("/fireStation/1")).andExpect(status().isOk());
    }

    @Test
    void getFireStations_ShouldReturnNoContent() throws Exception {
        //GIVEN
        when(fireStationService.getFireStations()).thenThrow(new NoSuchElementException());
        mockMvc.perform(get("/fireStations")).andExpect(status().isNoContent());
    }

    @Test
    void getFireStation_ShouldReturnNoContent() throws Exception {
        //GIVEN
        when(fireStationService.getFireStation(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(get("/fireStation/1")).andExpect(status().isNotFound());
    }


    @Test
    void createPerson_ShouldReturnOkStatus() throws Exception {
        ObjectMapper obj = new ObjectMapper();
        FireStation fireStation = new FireStation();
        fireStation.setIdFireStation(1L);
        fireStation.setStation("1");
        fireStation.setAddress("2");


        when(fireStationService.addFireStation(any())).thenReturn(fireStation);
        mockMvc.perform(post("/fireStation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(obj.writeValueAsString(fireStation)))
                .andExpect(status().isOk());
    }

    @Test
    void createPerson_ShouldReturnBadRequest() throws Exception {
        when(fireStationService.addFireStation(any())).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(post("/fireStation")).andExpect(status().isBadRequest());
    }

    @Test
    public void updatePerson_shouldReturnNoSuchElement() throws Exception {
        when(fireStationService.updateFireStation(any(), any())).thenThrow(NoSuchElementException.class);
        mockMvc.perform(put("/fireStation/1").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isNotFound());
    }

    @Test
    public void updatePerson_shouldReturnOk() throws Exception {
        FireStation fireStation = new FireStation();
        when(fireStationService.updateFireStation(any(), any())).thenReturn(fireStation);
        mockMvc.perform(put("/fireStation/1").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isOk());
    }

    @Test
    public void deletePerson_shouldReturnOk() throws Exception {
        FireStation fireStation = new FireStation();
        doNothing().when(fireStationService).deleteFireStation(any());
        when(fireStationService.getFireStation(any())).thenReturn(fireStation);
        mockMvc.perform(delete("/fireStation/1")).andExpect(status().isOk());
    }

    @Test
    public void deletePerson_shouldReturnNotFound() throws Exception {
        doNothing().when(fireStationService).deleteFireStation(any());
        when(fireStationService.getFireStation(any())).thenThrow(NoSuchElementException.class);
        mockMvc.perform(delete("/fireStation/1")).andExpect(status().isNotFound());
    }

    @Test
    public void deletePerson_shouldReturnBadRequest() throws Exception {
        doNothing().when(fireStationService).deleteFireStation(any());
        when(fireStationService.getFireStation(any())).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(delete("/fireStation/1")).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Test Exception to delete all Person")
    public void deletePersons_shouldReturnIllegalArgumentException() throws Exception {
        when(fireStationService.getFireStations()).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(delete("/fireStations")).andExpect(status().isNoContent());
    }

    @Test
    public void deletePersons_shouldReturnNoContent() throws Exception {
        FireStationRequest fireStation = new FireStationRequest();
        fireStationService.addFireStation(fireStation);
        mockMvc.perform(delete("/fireStations")).andExpect(status().isNoContent());
    }



    //////////////////////////////////////////////////////////////////////////////////////////////////


//
//    @Test
//    void getFireStations_ShouldReturnOK() throws Exception {
//        FireStationEntity fireStation = new FireStationEntity();
//        when(fireStationService.getFireStations()).thenReturn(singletonList(fireStation));
//        mockMvc.perform(get("/fireStations")).andExpect(status().isOk());
//    }
//
//    @Test
//    void getFireStations_ShouldReturnNotFound() throws Exception {
//        when(fireStationService.getFireStations()).thenThrow(NoSuchElementException.class);
//        mockMvc.perform(get("/fireStations")).andExpect(status().isNoContent());
//    }
//
//    @Test
//    void getFireStation_ShouldReturnOK() throws Exception {
//        //GIVEN
//        FireStationEntity fireStation = new FireStationEntity();
//        when(fireStationService.getFireStation(any())).thenReturn(fireStation);
//        mockMvc.perform(get("/fireStation/1")).andExpect(status().isOk());
//    }
//
//    @Test
//    void getFireStation_ShouldReturnNotFound() throws Exception {
//        //GIVEN
//        when(fireStationService.getFireStation(any())).thenThrow(NoSuchElementException.class);
//        mockMvc.perform(get("/fireStation/1")).andExpect(status().isNotFound());
//    }
//
//    @Test
//    void createFireStation_ShouldReturnOk() throws Exception {
//        FireStationEntity fireStation = new FireStationEntity();
//        when(fireStationService.addFireStation(any())).thenReturn(fireStation);
//        mockMvc.perform(get("/fireStation/1")).andExpect(status().isOk());
//    }
//
//    @Test
//    void createFireStation_ShouldReturnBadRequest() throws Exception {
//        when(fireStationService.addFireStation(any())).thenThrow(IllegalArgumentException.class);
//        mockMvc.perform(post("/fireStation")).andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void updateFireStation_shouldReturnNoSuchElement() throws Exception {
//        when(fireStationService.updateFireStation(any(), any())).thenThrow(NoSuchElementException.class);
//        mockMvc.perform(put("/fireStation/1").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void updateFireStation_shouldReturnOk() throws Exception {
//        FireStationEntity fireStation = new FireStationEntity();
//        when(fireStationService.updateFireStation(any(), any())).thenReturn(fireStation);
//        mockMvc.perform(put("/fireStation/1").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isOk());
//    }
//
//    @Test
//    public void deleteFireStation_shouldReturnOk() throws Exception {
//        FireStationEntity fireStation = new FireStationEntity();
//        doNothing().when(fireStationService).deleteFireStation(any());
//        when(fireStationService.getFireStation(any())).thenReturn(fireStation);
//        mockMvc.perform(delete("/fireStation/1")).andExpect(status().isOk());
//    }
//
//    @Test
//    public void deleteFireStation_shouldReturnNotFound() throws Exception {
//        FireStationEntity fireStation = new FireStationEntity();
//        doNothing().when(fireStationService).deleteFireStation(any());
//        when(fireStationService.getFireStation(any())).thenThrow(NoSuchElementException.class);
//        mockMvc.perform(delete("/fireStation/1")).andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void deleteFireStation_shouldReturnBadRequest() throws Exception {
//        FireStationEntity fireStation = new FireStationEntity();
//        doNothing().when(fireStationService).deleteFireStation(any());
//        when(fireStationService.getFireStation(any())).thenThrow(IllegalArgumentException.class);
//        mockMvc.perform(delete("/fireStation/1")).andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void deleteFireStations_shouldReturnIllegalArgumentException() throws Exception {
//        when(fireStationService.getFireStations()).thenThrow(IllegalArgumentException.class);
//        mockMvc.perform(delete("/fireStations")).andExpect(status().isNoContent());
//    }
//
//    @Test
//    public void deleteFireStations_shouldReturnNoContent() throws Exception {
//        FireStationEntity fireStation = new FireStationEntity();
//        fireStationService.addFireStation(fireStation);
//        mockMvc.perform(delete("/fireStations")).andExpect(status().isNoContent());
//    }

}

