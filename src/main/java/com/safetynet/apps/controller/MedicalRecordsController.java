package com.safetynet.apps.controller;


import com.safetynet.apps.controller.dto.MedicalRecords.MedicalRecordsRequest;
import com.safetynet.apps.service.MedicalRecordsService;
import com.safetynet.apps.service.data.MedicalRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@Slf4j
public class MedicalRecordsController {

    @Autowired
    private MedicalRecordsService medicalRecordsService;

    /**
     * Read - get all medicalRecords
     *
     * @return - An iterrable objext of MedicalRecords full filled
     */

    @GetMapping("medicalRecords")
    public ResponseEntity<List<MedicalRecords>> getMedicalRecords() {
        try {
            log.info("GET medicalRecords succeed");
            return ResponseEntity.ok(medicalRecordsService.getMedicalRecords());
        } catch (NoSuchElementException e) {
            log.error("GET medicalRecords error : not found");
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("medicalRecord/{id}")
    public ResponseEntity<MedicalRecords> getMedicalRecord(@PathVariable("id") final Long id) {
        try {
            log.info("GET medicalRecords succeed");

            return ResponseEntity.ok(medicalRecordsService.getMedicalRecord(id));
        } catch (NoSuchElementException e) {
            log.error("GET medicalRecords error : not found");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/medicalRecord")
    public ResponseEntity<MedicalRecords> createMedicalRecord(@RequestBody MedicalRecordsRequest medicalRecords) {
        log.info("POST add medicalRecords succeed");
        return ResponseEntity.ok(medicalRecordsService.addMedicalRecord(medicalRecords, null));
    }

    @PutMapping("/medicalRecord/{id}")
    public ResponseEntity<MedicalRecords> updateMedicalRecord(@PathVariable("id") final Long id, @RequestBody MedicalRecordsRequest medicalRecords) {
        try {
            log.info("PUT medicalRecord updated");
            return ResponseEntity.ok(medicalRecordsService.updateMedicalRecords(id, medicalRecords));
        } catch (NoSuchElementException exception) {
            log.error("PUT medicalRecord update error : not found");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/medicalRecord/{id}")
    public ResponseEntity<?> deleteMedicalRecords(@PathVariable("id") final Long id) {
        try {
            medicalRecordsService.getMedicalRecord(id);
            medicalRecordsService.deleteMedicalRecord(id);
            log.info("DELETE medicalRecord succeed");
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException exception) {
            log.error("DELETE medicalRecords error : not found");
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException exception) {
            log.error("DELETE medicalRecords error : illegal argument");
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/medicalRecords")
    public ResponseEntity<?> deletePMedicalRecords() {
        log.info("DELETE all medicalRecords succeed");
        medicalRecordsService.deleteMedicalRecords();
        return ResponseEntity.noContent().build();
    }


}
