package com.safetynet.apps.unitTest.MedicalRecords;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.apps.controller.MedicalRecordsController;
import com.safetynet.apps.controller.dto.MedicalRecords.MedicalRecordsRequest;
import com.safetynet.apps.model.entity.PersonEntity;
import com.safetynet.apps.service.MedicalRecordsService;
import com.safetynet.apps.service.data.MedicalRecords;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = MedicalRecordsController.class)
public class MedicalRecordsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordsService medicalRecordService;
    @MockBean
    private PersonEntity personEntity;

    private static ObjectMapper mapper = new ObjectMapper();


    @Test
    void getMedicalRecordss_ShouldReturnNotFound() throws Exception {
        when(medicalRecordService.getMedicalRecords()).thenThrow(NoSuchElementException.class);
        mockMvc.perform(get("/medicalRecords")).andExpect(status().isNoContent());
    }

    @Test
    void getMedicalRecords_ShouldReturnOK() throws Exception {
        //GIVEN
        MedicalRecords medicalRecords = new MedicalRecords();
        when(medicalRecordService.getMedicalRecord(any())).thenReturn(medicalRecords);
        mockMvc.perform(get("/medicalRecord/1")).andExpect(status().isOk());
    }

    @Test
    void getMedicalRecords_ShouldReturnNotFound() throws Exception {
        //GIVEN
        when(medicalRecordService.getMedicalRecord(any())).thenThrow(NoSuchElementException.class);
        mockMvc.perform(get("/medicalRecord/1")).andExpect(status().isNotFound());
    }

    @Test
    void createMedicalRecords_ShouldReturnOk() throws Exception {
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        ObjectMapper obj = new ObjectMapper();
        MedicalRecords medicalRecords = new MedicalRecords();
        medicalRecords.setIdMedicalRecords(1L);
        medicalRecords.setBirthdate("1");
        medicalRecords.setMedications(medications);
        medicalRecords.setAllergies(allergies);


        when(medicalRecordService.addMedicalRecord(any())).thenReturn(medicalRecords);
        mockMvc.perform(post("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(obj.writeValueAsString(medicalRecords)))
                .andExpect(status().isOk());

    }

    @Test
    void createMedicalRecords_ShouldReturnBadRequest() throws Exception {
        when(medicalRecordService.addMedicalRecords(any())).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(post("/medicalRecord")).andExpect(status().isBadRequest());
    }

    @Test
    public void updateMedicalRecords_shouldReturnNoSuchElement() throws Exception {
        when(medicalRecordService.updateMedicalRecords(any(), any())).thenThrow(NoSuchElementException.class);
        mockMvc.perform(put("/medicalRecord/1").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isNotFound());
    }

    @Test
    public void updateMedicalRecords_shouldReturnOk() throws Exception {
        MedicalRecords medicalRecord = new MedicalRecords();
        when(medicalRecordService.updateMedicalRecords(any(), any())).thenReturn(medicalRecord);
        mockMvc.perform(put("/medicalRecord/1").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isOk());
    }

    @Test
    public void deleteMedicalRecords_shouldReturnOk() throws Exception {
        MedicalRecords medicalRecord = new MedicalRecords();
        doNothing().when(medicalRecordService).deleteMedicalRecord(any());
        when(medicalRecordService.getMedicalRecord(any())).thenReturn(medicalRecord);
        mockMvc.perform(delete("/medicalRecord/1")).andExpect(status().isOk());
    }

    @Test
    public void deleteMedicalRecords_shouldReturnNotFound() throws Exception {
        doNothing().when(medicalRecordService).deleteMedicalRecord(any());
        when(medicalRecordService.getMedicalRecord(any())).thenThrow(NoSuchElementException.class);
        mockMvc.perform(delete("/medicalRecord/1")).andExpect(status().isNotFound());
    }

    @Test
    public void deleteMedicalRecords_shouldReturnBadRequest() throws Exception {
        doNothing().when(medicalRecordService).deleteMedicalRecord(any());
        when(medicalRecordService.getMedicalRecord(any())).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(delete("/medicalRecord/1")).andExpect(status().isBadRequest());
    }

    @Test
    public void deleteMedicalRecords_shouldReturnIllegalArgumentException() throws Exception {
        when(medicalRecordService.getMedicalRecords()).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(delete("/medicalRecords")).andExpect(status().isNoContent());
    }

    @Test
    public void deleteMedicalRecords_shouldReturnNoContent() throws Exception {
        MedicalRecordsRequest medicalRecord = new MedicalRecordsRequest();
        medicalRecordService.addMedicalRecord(medicalRecord);
        mockMvc.perform(delete("/medicalRecords")).andExpect(status().isNoContent());
    }
}
