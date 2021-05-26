package com.safetynet.apps.unitTest.FireStation;


import static org.mockito.ArgumentMatchers.any;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.apps.controller.FireStationController;
import com.safetynet.apps.model.entity.FireStationEntity;
import com.safetynet.apps.service.FireStationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = FireStationController.class)
public class FireStationControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private FireStationService fireStationService;
//    private static ObjectMapper mapper = new ObjectMapper();
//
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

