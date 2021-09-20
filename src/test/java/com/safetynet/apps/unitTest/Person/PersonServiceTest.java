package com.safetynet.apps.unitTest.Person;

import com.safetynet.apps.controller.dto.Person.PersonRequest;
import com.safetynet.apps.mapper.PersonConverter;
import com.safetynet.apps.model.entity.FireStationEntity;
import com.safetynet.apps.model.entity.MedicalRecordsEntity;
import com.safetynet.apps.model.entity.PersonEntity;
import com.safetynet.apps.model.repository.FireStationRepository;
import com.safetynet.apps.model.repository.PersonRepository;
import com.safetynet.apps.service.FireStationService;
import com.safetynet.apps.service.MedicalRecordsService;
import com.safetynet.apps.service.PersonService;

import com.safetynet.apps.service.data.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {


    @Mock
    private PersonRepository personRepository;
    @Mock
    private PersonConverter personConverter;
    @Mock
    private MedicalRecordsService medicalRecordsService;
    @Mock
    private FireStationService fireStationService;
    @Mock
    private FireStationRepository fireStationRepository;
    @InjectMocks
    private PersonService personService;


    @Test
    void getPerson_ShouldThrowExceptionOnMissingPerson() {
        // Given
        // When
        when(personRepository.findById(1L)).thenReturn(Optional.empty());
        // Then
        assertThrows(NoSuchElementException.class, () -> personService.getPerson(1L));
    }

    @Test
    void addPerson_ShouldChangeEntityFromPersonrequest() {
        PersonRequest personRequest = new PersonRequest("1","2","3","4","5","6","7", null);
        PersonEntity personEntity = new PersonEntity(0L,"1","2","3","4","5","6","7", null,null);
        Person person = new Person();
        when(personRepository.save(any(PersonEntity.class))).thenReturn(personEntity);
        when(personConverter.mapperPerson(personEntity)).thenReturn(person);

        personService.addPerson(personRequest);
        verify(personRepository,times(1)).save(any());
    }


//    matchPersonWithStation



    @Test
    void updatePerson_ShouldChangeEntityFromPersonrequest() {
        PersonEntity entity = new PersonEntity    (1L,"1","2","3","4","5","6","7",null,null);
        PersonRequest personRequest = new PersonRequest("6","2","3","4","5","6","7",null);

        when(personRepository.findById(any(Long.class))).thenReturn(Optional.of(entity));
        when(personRepository.save(entity)).thenReturn(entity);
        when(personConverter.mapperPerson(any(PersonEntity.class))).thenReturn(null);
        personService.updatePerson(1L,personRequest);
        assertEquals(entity.getFirstName(),"6");
    }

    @Test
    void updateEntity_ShouldNotChangeEntityFromNullPersonrequest() {

        PersonEntity entity = new PersonEntity(1L,"1","2","3","4","5","6","7",null,null);
        PersonRequest personRequest = new PersonRequest();

        when(personRepository.findById(any(Long.class))).thenReturn(Optional.of(entity));
        when(personRepository.save(entity)).thenReturn(entity);
        when(personConverter.mapperPerson(any(PersonEntity.class))).thenReturn(null);
        personService.updatePerson(1L,personRequest);

        //Then
        assertEquals(entity.getFirstName()  ,"1");
        assertEquals(entity.getLastName()   ,"2");
        assertEquals(entity.getAddress()    ,"3");
        assertEquals(entity.getCity()       ,"4");
        assertEquals(entity.getZip()        ,"5");
        assertEquals(entity.getPhone()      ,"6");
        assertEquals(entity.getEmail()      ,"7");
    }

    @Test
    void matchPersonWithStation_shouldReturnAnAdult() throws ParseException {


        // Set up MedicalRecordsEntity
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        MedicalRecordsEntity me1 = new MedicalRecordsEntity(0L,null,sdf.parse("11/11/1998"),medications,allergies);
        MedicalRecordsEntity me2 = new MedicalRecordsEntity(0L,null,sdf.parse("11/11/2015"),medications,allergies);

        // Set up PersonEntity
        List<PersonEntity> pel = new ArrayList<>();
        PersonEntity pe = new PersonEntity(1L,"1","2","3","4","5","6","7",me1,null);
        PersonEntity pe2 = new PersonEntity(1L,"1","2","3","4","5","6","7",me2,null);

        pel.add(pe);
        pel.add(pe2);

        // Set up FireStationEntity
        List<FireStationEntity> fireStationEntityList = new ArrayList<>();
        FireStationEntity fe = new FireStationEntity(1L,"8","9",pel);
        fireStationEntityList.add(fe);


        when(fireStationRepository.findByStation(any())).thenReturn(fireStationEntityList);
        Map<String, Object> map = personService.getStation("9");

        assertThat(map.get("adult")).isEqualTo(1);
        assertThat(map.get("child")).isEqualTo(1);



    }

    @Test
    void matchChildrenWithAddress_shouldReturnAChild() throws ParseException {
        // Set up MedicalRecordsEntity
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        MedicalRecordsEntity entity = new MedicalRecordsEntity(0L,null,sdf.parse("11/11/2015"),medications,allergies);

        // Set up PersonEntity
        List<PersonEntity> pel = new ArrayList<>();
        PersonEntity pe = new PersonEntity(1L,"1","2","3","4","5","6","7",entity,null);
        PersonEntity pe2 = new PersonEntity(1L,"10","2","3","4","5","6","7",entity,null);
        pel.add(pe);
        pel.add(pe2);

        when(personRepository.findByAddress(any())).thenReturn(pel);
        Map<String, Object> map = personService.getChildAlert("3");
        Map<String, Object> mapExpecting = new HashMap<>();
        List <String> family = new ArrayList<>();
        family.add(pe2.getFirstName() + " " + pe2.getLastName());
        mapExpecting.put("family",family);
        mapExpecting.put("Age",5);
        assertThat(map.get("1 2")).isEqualTo(mapExpecting);
    }

    @Test
    void matchPhoneNumberOfStation_shouldReturnAPhoneNumber() throws ParseException {
        // Set up MedicalRecordsEntity
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        MedicalRecordsEntity entity = new MedicalRecordsEntity(0L,null,sdf.parse("11/11/1998"),medications,allergies);

        // Set up PersonEntity
        List<PersonEntity> pel = new ArrayList<>();
        PersonEntity pe = new PersonEntity(1L,"1","2","3","4","5","6","7",entity,null);
        pel.add(pe);

        // Set up FireStationEntity
        List<FireStationEntity> fireStationEntityList = new ArrayList<>();
        FireStationEntity fe = new FireStationEntity(1L,"8","9",pel);
        fireStationEntityList.add(fe);

        when(fireStationRepository.findByStation(any())).thenReturn(fireStationEntityList);
        Map<String, Object> map = personService.getPhoneNumberOfFireStation("9");

        assertThat(map.get("1 2")).isEqualTo("6");

    }

    @Test
    void matchPersonByAddress_shouldReturnInformationsOfPersonByAddress() throws ParseException {
        // Set up MedicalRecordsEntity
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        MedicalRecordsEntity me = new MedicalRecordsEntity(0L,null,sdf.parse("11/11/2015"),medications,allergies);

        // Set up PersonEntity
        List<PersonEntity> pel = new ArrayList<>();
        PersonEntity pe = new PersonEntity(1L,"1","2","3","4","5","6","7",me,null);

        // Set up FireStationEntity
        List<FireStationEntity> fireStationEntityList = new ArrayList<>();
        FireStationEntity fe = new FireStationEntity(1L,"8","9",pel);
        fireStationEntityList.add(fe);

        pe.setFireStationEntity(fireStationEntityList);
        pel.add(pe);

        when(personRepository.findByAddress(any())).thenReturn(pel);
        Map<String, Object> map = personService.getFire("3");


        Map<String, Object> mapExpecting = new HashMap<>();
        List<String> station = new ArrayList<>();
        station.add("9");
        mapExpecting.put("phone","6");
        mapExpecting.put("age",5);
        mapExpecting.put("allergies",allergies);
        mapExpecting.put("medication",medications);
        mapExpecting.put("station",station);

        assertThat(map.get("1 2")).isEqualTo(mapExpecting);
    }

    @Test
    void listPersonFromListOfStation_shouldReturnPerson() throws ParseException {
        // Set up MedicalRecordsEntity
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        MedicalRecordsEntity me = new MedicalRecordsEntity(0L,null,sdf.parse("11/11/2015"),medications,allergies);

        // Set up PersonEntity
        List<PersonEntity> pel = new ArrayList<>();
        PersonEntity pe = new PersonEntity(1L,"1","2","3","4","5","6","7",me,null);

        // Set up FireStationEntity
        List<FireStationEntity> fireStationEntityList = new ArrayList<>();
        FireStationEntity fe = new FireStationEntity(1L,"8","9",pel);
        fireStationEntityList.add(fe);

        pe.setFireStationEntity(fireStationEntityList);
        pel.add(pe);

        when(fireStationRepository.findByStation(any())).thenReturn(fireStationEntityList);
        Map<String, Object> map = personService.getListPersonFromListOfStation("9");
        System.out.println(map);

        Map<String, Object> mapExpecting = new HashMap<>();
        List<PersonEntity> personEntityList = new ArrayList<>();
        Map<String, Object> mapAddress = new HashMap<>();
        Map<String, Object> mapName = new HashMap<>();
        Map<String, Object> mapInfo = new HashMap<>();

        mapInfo.put("phone", pe.getPhone());
        mapInfo.put("age", "5");
        mapInfo.put("medication", pe.getMedicalRecord().getMedications());
        mapInfo.put("allergies", pe.getMedicalRecord().getAllergies());
        mapName.put("1 2",mapInfo);
        mapAddress.put("8",mapName);
        mapExpecting.put("station 9", mapAddress);
        personEntityList.add(pe);
        System.out.println(mapExpecting);
        assertThat(map.get("station 9")).isEqualTo(mapExpecting.get("station 9"));



    }

    @Test
    void getListPersonInfoFromName_ShouldReturnListPerson() throws ParseException {
        // Set up MedicalRecordsEntity
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        MedicalRecordsEntity me = new MedicalRecordsEntity(0L,null,sdf.parse("11/11/2015"),medications,allergies);

        // Set up PersonEntity
        List<PersonEntity> pel = new ArrayList<>();
        PersonEntity pe = new PersonEntity(1L,"1","2","3","4","5","6","7",me,null);
        PersonEntity pe2 = new PersonEntity(1L,"10","2","3","4","5","6","7",me,null);

        // Set up FireStationEntity
        List<FireStationEntity> fireStationEntityList = new ArrayList<>();
        FireStationEntity fe = new FireStationEntity(1L,"8","9",pel);
        pe.setFireStationEntity(fireStationEntityList);
        pel.add(pe);
        pel.add(pe2);
        fireStationEntityList.add(fe);

        List<Person> personListExpected = new ArrayList<>();
        Person person = new Person(pe);
        personListExpected.add(person);

        when(personRepository.findByFirstNameAndLastName(any(),any())).thenReturn(pel);
        when(personRepository.findAll()).thenReturn(pel);
        Map<String, Object> personList = personService.getListPersonInfoFromName("1","2");

        Map<String, Object> mapExpected = new HashMap<>();
        Map<String, Object> mapInfo = new HashMap<>();
        mapInfo.put("allergies", pe.getMedicalRecord().getAllergies());
        mapInfo.put("mail", pe.getEmail());
        mapInfo.put("medications", pe.getMedicalRecord().getMedications());
        mapInfo.put("address",pe.getAddress());
        mapInfo.put("Age", 5);

        List<String> family = new ArrayList<>();
        for (PersonEntity searchFamily : pel) {
            if (searchFamily.getLastName().equals(pe.getLastName()) && !searchFamily.getFirstName().equals(pe.getFirstName()))
                family.add( searchFamily.getFirstName() + " " + searchFamily.getLastName());
        }
        mapInfo.put("family", family);
        mapExpected.put("1 2", mapInfo);
        assertThat(personList.get("1 2")).isEqualTo(mapExpected.get("1 2"));
    }


    @Test
    void matchEmailFromCity_shouldReturnEmail() throws ParseException {
        // Set up MedicalRecordsEntity
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        MedicalRecordsEntity me = new MedicalRecordsEntity(0L,null,sdf.parse("11/11/2015"),medications,allergies);

        // Set up PersonEntity
        List<PersonEntity> pel = new ArrayList<>();
        PersonEntity pe = new PersonEntity(1L,"1","2","3","4","5","6","7",me,null);

        // Set up FireStationEntity
        List<FireStationEntity> fireStationEntityList = new ArrayList<>();
        FireStationEntity fe = new FireStationEntity(1L,"8","9",pel);
        fireStationEntityList.add(fe);

        pe.setFireStationEntity(fireStationEntityList);
        pel.add(pe);

        when(personRepository.findByCity(any())).thenReturn(pel);
        Map<String, Object> map = personService.getEmailFromCity("4");

        assertThat(map.get("1 2")).isEqualTo("7");



    }

}