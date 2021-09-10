package com.safetynet.apps;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.apps.model.entity.FireStationEntity;
import com.safetynet.apps.model.entity.MedicalRecordsEntity;
import com.safetynet.apps.model.entity.PersonEntity;
import com.safetynet.apps.service.FireStationService;
import com.safetynet.apps.service.MedicalRecordsService;
import com.safetynet.apps.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class DataInitialisationMapper implements ApplicationRunner {

    @Autowired
    private PersonService personService;
    @Autowired
    private FireStationService fireStationService;
    @Autowired
    private MedicalRecordsService medicalRecordsService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //open file json
        Any any = this.readJson("src/main/resources/json/data.json");

        // Fill attribute of Persons to database
        List<PersonEntity> persons = this.fillPerson(any.get("persons"));

        // Fill attribute of FireStation and MedicalRecords to database
        this.fillFireStation   (any.get("firestations")  , persons);
        this.fillMedicalRecords(any.get("medicalrecords"), persons);

    }

    private Any readJson(String filePath) throws IOException {
        byte[] bytesFile = Files.readAllBytes(new File(filePath).toPath());
        JsonIterator iter = JsonIterator.parse(bytesFile);
        return iter.readAny();
    }

    private List<PersonEntity> fillPerson(Any personAny) {

        List<PersonEntity> persons = new ArrayList<>();
        personAny.forEach(a -> persons.add( PersonEntity.builder().firstName(a.get("firstName").toString())
                .address (a.get("address").toString())
                .city    (a.get("city").toString())
                .lastName(a.get("lastName").toString())
                .phone   (a.get("phone").toString())
                .zip     (a.get("zip").toString())
                .email   (a.get("email").toString())
                .build()));
        personService.addPersons(persons);

        return persons;
    }

    private List<FireStationEntity>  fillFireStation(Any fireStationAny, List<PersonEntity> persons) {

        List<FireStationEntity> fireStations = new ArrayList<>();
        fireStationAny.forEach(a -> fireStations.add( FireStationEntity.builder().address(a.get("address").toString())
                .station(a.get("station").toString())
                .build()));

        fireStationService.attributePersonToFireStation(persons, fireStations);
        fireStationService.addFireStations(fireStations);
        personService.addPersons(persons);

        //fireStations.forEach(p -> System.out.println(p.getAddress().concat(p.getStation())));
//        for(FireStationEntity fireStationEntity : fireStations) {
//            fireStationService.listAllPersonFromFireStation(fireStationEntity);
//        }


        return fireStations;
    }

    private List<MedicalRecordsEntity> fillMedicalRecords(Any medicalRecordsAny , List<PersonEntity> persons) {
        List<MedicalRecordsEntity> medicalRecords = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());


        medicalRecordsAny.forEach(a -> {
            try {
                medicalRecords.add( MedicalRecordsEntity.builder()
                        .personMedicalRecord(persons.stream().filter( p ->
                                p.getFirstName().equals(a.get("firstName").toString()) && p.getLastName().equals(a.get("lastName").toString()))
                                .findFirst().orElse(null))
                        .birthDate  (sdf.parse(a.get("birthdate").toString()))
                        .medications(a.get("medications").asList().stream().map(Any::toString).collect(Collectors.toList()))
                        .allergies  (a.get("allergies").asList().stream().map(Any::toString).collect(Collectors.toList()))
                        .build());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        medicalRecordsService.addMedicalRecords(medicalRecords);

        return medicalRecords;
    }

}





