package com.safetynet.apps.service;

import com.safetynet.apps.controller.MedicalRecordsRequest;
import com.safetynet.apps.mapper.MedicalRecordsConverter;
import com.safetynet.apps.model.entity.MedicalRecordsEntity;
import com.safetynet.apps.model.repository.MedicalRecordsRepository;
import com.safetynet.apps.service.data.MedicalRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MedicalRecordsService {

    @Autowired
    private MedicalRecordsRepository medicalRecordRepository;

    @Autowired
    private MedicalRecordsConverter medicalRecordsConverter;

    public List<MedicalRecords> getMedicalRecords() {
        return medicalRecordsConverter.mapperMedicalRecords(medicalRecordRepository.findAll());
    }

    public MedicalRecords getMedicalRecord(final Long id) {
        MedicalRecordsEntity medicalRecordsEntity = medicalRecordRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        return medicalRecordsConverter.mapperMedicalRecords(medicalRecordsEntity);
    }

    public void deleteMedicalRecord(final Long id) {
        medicalRecordRepository.deleteById(id);
    }

    public void deleteMedicalRecords() {
        medicalRecordRepository.deleteAll();
    }

    public MedicalRecords addMedicalRecord(MedicalRecordsRequest medicalRecord) {
        MedicalRecordsEntity entity = new MedicalRecordsEntity();
        entity.setIdMedicalRecords(0L);
        entity.setMedications(medicalRecord.getMedications());
        entity.setAllergies(medicalRecord.getAllergies());
        entity.setBirthDate(medicalRecord.getBirthdate());

        entity = medicalRecordRepository.save(entity);

        return medicalRecordsConverter.mapperMedicalRecords(entity);
    }


    public MedicalRecords updateMedicalRecords(final Long id, MedicalRecordsRequest medicalRecordsRequest) {
        MedicalRecordsEntity entity = medicalRecordRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id " + id + " not found"));
        this.update(entity, medicalRecordsRequest);
        entity = medicalRecordRepository.save(entity);

        return medicalRecordsConverter.mapperMedicalRecords(entity);

    }

    public void update(MedicalRecordsEntity medicalRecordToUpdate, MedicalRecordsRequest medicalRecordsRequest) {
        if (medicalRecordsRequest.getBirthdate() != null)
            medicalRecordToUpdate.setBirthDate(medicalRecordsRequest.getBirthdate());
        if (medicalRecordsRequest.getMedications() != null)
            medicalRecordToUpdate.setAllergies(medicalRecordsRequest.getMedications());
        if (medicalRecordsRequest.getAllergies() != null)
            medicalRecordToUpdate.setAllergies(medicalRecordsRequest.getAllergies());
    }


    public Iterable<MedicalRecordsEntity> addMedicalRecords(List<MedicalRecordsEntity> medicalRecords) {
        return medicalRecordRepository.saveAll(medicalRecords);
    }

}
