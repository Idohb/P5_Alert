package com.safetynet.apps.controller;

import com.safetynet.apps.model.MedicalRecords;
import com.safetynet.apps.service.MedicalRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import java.util.Date;
import java.util.Optional;

@RestController
public class MedicalRecordsController {

    @Autowired
    private MedicalRecordsService medicalRecordsService;

    /**
     * Read - get all medicalRecords
     * @return - An iterrable objext of MedicalRecords full filled
     */

    @RequestMapping(value = "/medicalRecords", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<MedicalRecords> getMedicalRecords() {
        return medicalRecordsService.getMedicalRecords();
    }

    @GetMapping("medicalRecord/{id}")
    public MedicalRecords getMedicalRecords(@PathVariable("id") final Long id) {
        Optional<MedicalRecords> medicalRecords = medicalRecordsService.getMedicalRecord(id);
        if(medicalRecords.isPresent()) {
            return medicalRecords.get();
        } else {
            return null;
        }
    }

    /**
     * Create a medicalRecords
     * @param medicalRecords
     * @return
     */
    @PostMapping("/medicalRecords")
    public MedicalRecords createMedicalRecords(@RequestBody MedicalRecords medicalRecords) {
        return medicalRecordsService.saveMedicalRecords(medicalRecords);
    }

    /**
     * Update - Update an existing employee
     * @param id - The id of the employee to update
     * @param medicalRecords - The employee object updated
     * @return
     */
    @PutMapping("/medicalRecords/{id}")
    public MedicalRecords updateMedicalRecords(@PathVariable("id") final Long id, @RequestBody MedicalRecords medicalRecords) {
        Optional<MedicalRecords> e = medicalRecordsService.getMedicalRecord(id);
        if(e.isPresent()) {
            MedicalRecords currentMedicalRecords = e.get();

            String firstName = medicalRecords.getFirstName();
            if(firstName != null) {
                currentMedicalRecords.setFirstName(firstName);
            }


            String lastName = medicalRecords.getLastName();
            if(lastName != null) {                  // need test
                currentMedicalRecords.setLastName(lastName);
            }

            Date birthDate = medicalRecords.getBirthDate();
            if (birthDate != null) {
                currentMedicalRecords.setBirthDate(birthDate);
            }

            String medications = medicalRecords.getMedications();
            if(medications != null) {                  // need test
                currentMedicalRecords.setMedications(medications);
            }

            String allergies = medicalRecords.getAllergies();
            if(allergies != null) {                  // need test
                currentMedicalRecords.setAllergies(allergies);
            }

            medicalRecordsService.saveMedicalRecords(currentMedicalRecords);
            return currentMedicalRecords;

        } else {
            return null;
        }
    }


    /**
     * Delete - Delete an employee
     * @param id - The id of the employee to delete
     */
    @DeleteMapping("/medicalRecords/{id}")
    public void deleteMedicalRecord(@PathVariable("id") final Long id) {
        medicalRecordsService.deleteMedicalRecord(id);
    }

    /**
     * Delete - Delete an employee
     */
    @DeleteMapping("/medicalRecords")
    public void deleteMedicalRecords() {
        medicalRecordsService.deleteMedicalRecords();
    }

}
