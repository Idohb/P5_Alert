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
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
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
        String filePath = "src/main/resources/json/data.json";

        byte[] bytesFile = Files.readAllBytes(new File(filePath).toPath());

        JsonIterator iter = JsonIterator.parse(bytesFile);
        Any any = iter.readAny();
        Any personAny = any.get("persons");
        List<PersonEntity> persons = new ArrayList<>();
        personAny.forEach(a -> persons.add( PersonEntity.builder().firstName(a.get("firstName").toString())
                .address(a.get("address").toString())
                .city(a.get("city").toString())
                .lastName(a.get("lastName").toString())
                .phone(a.get("phone").toString())
                .zip(a.get("zip").toString())
                .email(a.get("email").toString())
                .build()));

        personService.addPersons(persons);
//        persons.forEach(p -> System.out.println(p.getFirstName().concat(p.getLastName()).concat(p.getAddress()).concat(p.getCity()).concat(p.getPhone()).concat(p.getZip())));


        Any fireStationAny = any.get("firestations");
        List<FireStationEntity> fireStations = new ArrayList<>();
        fireStationAny.forEach(a -> fireStations.add( FireStationEntity.builder().address(a.get("address").toString())
                .station(a.get("station").toString())
                .build()));


        fireStationService.addFireStations(fireStations);
//        fireStations.forEach(p -> System.out.println(p.getAddress().concat(p.getStation())));

        Any medicalRecordsAny = any.get("medicalrecords");
        List<MedicalRecordsEntity> medicalRecords = new ArrayList<>();
        medicalRecordsAny.forEach(a -> medicalRecords.add( MedicalRecordsEntity.builder()
                .personMedicalRecord(persons.stream().filter( p ->
                        p.getFirstName().equals(a.get("firstName").toString()) && p.getLastName().equals(a.get("lastName").toString()))
                        .findFirst().orElse(null))
                .birthDate(a.get("birthdate").toString())
                .medications(a.get("medications").asList().stream().map(Any::toString).collect(Collectors.toList()))
                .allergies(a.get("allergies").asList().stream().map(Any::toString).collect(Collectors.toList()))
                .build()));

        medicalRecordsService.addMedicalRecords(medicalRecords);
//        medicalRecords.forEach(p -> System.out.println(p.getBirthDate()));
    }
}





