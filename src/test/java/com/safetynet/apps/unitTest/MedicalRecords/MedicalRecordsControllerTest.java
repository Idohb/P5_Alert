package com.safetynet.apps.unitTest.MedicalRecords;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.apps.controller.MedicalRecordsController;
import com.safetynet.apps.controller.dto.MedicalRecordsDTO;
import com.safetynet.apps.model.entity.MedicalRecordsEntity;
import com.safetynet.apps.model.entity.PersonEntity;
import com.safetynet.apps.service.MedicalRecordsService;
import com.safetynet.apps.service.data.MedicalRecords;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static java.util.Collections.singletonList;
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


//    @Test
//    void getMedicalRecordss_ShouldReturnOK() throws Exception {
//        MedicalRecordsEntity medicalRecord = new MedicalRecordsEntity();
//        MedicalRecordsDTO medicalRecordsDTO = new MedicalRecordsDTO(medicalRecord);
//        when(medicalRecordService.getMedicalRecords()).thenReturn(singletonList(medicalRecordsDTO));
//        mockMvc.perform(get("/medicalRecords")).andExpect(status().isOk());
//    }

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

//    @Test
//    void createMedicalRecords_ShouldReturnOk() throws Exception {
//        MedicalRecordsEntity medicalRecord = new MedicalRecordsEntity();
//        when(medicalRecordService.addMedicalRecord(any())).thenReturn(medicalRecord);
//        mockMvc.perform(get("/medicalRecord/1")).andExpect(status().isOk());
//    }

    @Test
    void createMedicalRecords_ShouldReturnBadRequest() throws Exception {
        when(medicalRecordService.addMedicalRecords(any())).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(post("/medicalRecord")).andExpect(status().isBadRequest());
    }

//    @Test
//    public void updateMedicalRecords_shouldReturnNoSuchElement() throws Exception {
//        when(medicalRecordService.updateMedicalRecords(any(), any())).thenThrow(NoSuchElementException.class);
//        mockMvc.perform(put("/medicalRecord/1").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void updateMedicalRecords_shouldReturnOk() throws Exception {
//        MedicalRecordsEntity medicalRecord = new MedicalRecordsEntity();
//        when(medicalRecordService.updateMedicalRecords(any(), any())).thenReturn(medicalRecord);
//        mockMvc.perform(put("/medicalRecord/1").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isOk());
//    }

//    @Test
//    public void deleteMedicalRecords_shouldReturnOk() throws Exception {
//        MedicalRecordsEntity medicalRecord = new MedicalRecordsEntity();
//        doNothing().when(medicalRecordService).deleteMedicalRecord(any());
//        when(medicalRecordService.getMedicalRecord(any())).thenReturn(medicalRecord);
//        mockMvc.perform(delete("/medicalRecord/1")).andExpect(status().isOk());
//    }
//
//    @Test
//    public void deleteMedicalRecords_shouldReturnNotFound() throws Exception {
//        MedicalRecordsEntity medicalRecord = new MedicalRecordsEntity();
//        doNothing().when(medicalRecordService).deleteMedicalRecord(any());
//        when(medicalRecordService.getMedicalRecord(any())).thenThrow(NoSuchElementException.class);
//        mockMvc.perform(delete("/medicalRecord/1")).andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void deleteMedicalRecords_shouldReturnBadRequest() throws Exception {
//        MedicalRecordsEntity medicalRecord = new MedicalRecordsEntity();
//        doNothing().when(medicalRecordService).deleteMedicalRecord(any());
//        when(medicalRecordService.getMedicalRecord(any())).thenThrow(IllegalArgumentException.class);
//        mockMvc.perform(delete("/medicalRecord/1")).andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void deleteMedicalRecords_shouldReturnIllegalArgumentException() throws Exception {
//        when(medicalRecordService.getMedicalRecords()).thenThrow(IllegalArgumentException.class);
//        mockMvc.perform(delete("/medicalRecords")).andExpect(status().isNoContent());
//    }
//
//    @Test
//    public void deleteMedicalRecords_shouldReturnNoContent() throws Exception {
//        MedicalRecordsEntity medicalRecord = new MedicalRecordsEntity();
//        medicalRecordService.addMedicalRecord(medicalRecord);
//        mockMvc.perform(delete("/medicalRecords")).andExpect(status().isNoContent());
//    }
}
