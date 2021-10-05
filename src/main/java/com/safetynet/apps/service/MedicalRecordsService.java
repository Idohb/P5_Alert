package com.safetynet.apps.service;

import com.safetynet.apps.controller.dto.MedicalRecords.MedicalRecordsRequest;
import com.safetynet.apps.controller.dto.Person.PersonRequest;
import com.safetynet.apps.mapper.MedicalRecordsConverter;
import com.safetynet.apps.model.entity.MedicalRecordsEntity;
import com.safetynet.apps.model.entity.PersonEntity;
import com.safetynet.apps.model.repository.MedicalRecordsRepository;
import com.safetynet.apps.model.repository.PersonRepository;
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

    @Autowired
    private PersonRepository personRepository;


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

    public MedicalRecords addMedicalRecord(MedicalRecordsRequest medicalRecord, PersonEntity personEntity) {

        // Because we have the possibility to create a medicalRecords
        // in that case, we need to create a Person as well
        if (medicalRecord != null && personEntity == null) {
            personEntity = personRepository.save(new PersonEntity(
                    0L, null, null, null, null, null, null, null, null, null)
            );
        }

        MedicalRecordsEntity medicalRecordsEntity = new MedicalRecordsEntity();
        medicalRecordsEntity.setIdMedicalRecords(0L);

        if (medicalRecord != null) {
            medicalRecordsEntity.setMedications(medicalRecord.getMedications());
            medicalRecordsEntity.setAllergies(medicalRecord.getAllergies());
            medicalRecordsEntity.setBirthDate(medicalRecord.getBirthdate());
            medicalRecordsEntity.setPersonMedicalRecord(personEntity);
        }

        medicalRecordsEntity = medicalRecordRepository.save(medicalRecordsEntity);
        personEntity.setMedicalRecord(medicalRecordsEntity);

        return medicalRecordsConverter.mapperMedicalRecords(medicalRecordsEntity);

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
