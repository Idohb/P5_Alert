package com.safetynet.apps.service;

import com.safetynet.apps.model.MedicalRecords;
import com.safetynet.apps.repository.MedicalRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicalRecordsService {

    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;

    public Optional<MedicalRecords> getMedicalRecord(final Long id) {
        return medicalRecordsRepository.findById(id);
    }

    public Iterable<MedicalRecords> getMedicalRecords() {
        return medicalRecordsRepository.findAll();
    }

    public void deleteMedicalRecord(final Long id) {
        medicalRecordsRepository.deleteById(id);
    }

    public void deleteMedicalRecords() {
        medicalRecordsRepository.deleteAll();
    }

    public MedicalRecords saveMedicalRecords(MedicalRecords employee) {
        MedicalRecords savedEmployee = medicalRecordsRepository.save(employee);
        return savedEmployee;
    }

}
