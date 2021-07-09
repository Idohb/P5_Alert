package com.safetynet.apps.controller;


import com.safetynet.apps.controller.dto.MedicalRecords.MedicalRecordsRequest;
import com.safetynet.apps.service.MedicalRecordsService;
import com.safetynet.apps.service.data.MedicalRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
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
            return ResponseEntity.ok(medicalRecordsService.getMedicalRecords());
        } catch (NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("medicalRecord/{id}")
    public ResponseEntity<MedicalRecords> getMedicalRecord(@PathVariable("id") final Long id) {
        try {
            return ResponseEntity.ok(medicalRecordsService.getMedicalRecord(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/medicalRecord")
    public ResponseEntity<MedicalRecords> createMedicalRecord(@RequestBody MedicalRecordsRequest medicalRecords) {
        try {
            return ResponseEntity.ok(medicalRecordsService.addMedicalRecord(medicalRecords, null));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/medicalRecord/{id}")
    public ResponseEntity<MedicalRecords> updateMedicalRecord(@PathVariable("id") final Long id, @RequestBody MedicalRecordsRequest medicalRecords) {
        try {
            return ResponseEntity.ok(medicalRecordsService.updateMedicalRecords(id, medicalRecords));
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/medicalRecord/{id}")
    public ResponseEntity<?> deleteMedicalRecords(@PathVariable("id") final Long id) {
        try {
            medicalRecordsService.getMedicalRecord(id);
            medicalRecordsService.deleteMedicalRecord(id);

            return ResponseEntity.ok().build();
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/medicalRecords")
    public ResponseEntity<?> deletePMedicalRecords() {
        medicalRecordsService.deleteMedicalRecords();
        return ResponseEntity.noContent().build();
    }


}
