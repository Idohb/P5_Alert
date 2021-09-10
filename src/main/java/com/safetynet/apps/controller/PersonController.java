package com.safetynet.apps.controller;


import com.safetynet.apps.controller.dto.Person.PersonRequest;
import com.safetynet.apps.model.entity.PersonEntity;
import com.safetynet.apps.service.FireStationService;
import com.safetynet.apps.service.PersonService;
import com.safetynet.apps.service.data.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private FireStationService fireStationService;


    /**
     * Read - get all person
     *
     * @return - An iterrable objext of Person full filled
     */

    @GetMapping("persons")
    public ResponseEntity<List<Person>> getPersons() {
        log.info("GET persons");
        return ResponseEntity.ok(personService.getPersons());
    }


    @GetMapping("person/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") final Long id) {
        try {
            return ResponseEntity.ok(personService.getPerson(id));
        } catch (NoSuchElementException e) {
            log.error("GET person id error not found");
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * @param person - The person object created
     * @return ResponseEntity of Person
     */
    @PostMapping("/person")
    public ResponseEntity<Person> createPerson(@RequestBody PersonRequest person) {
        try {
            return ResponseEntity.ok(personService.addPerson(person));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("fireStation")
    public ResponseEntity<Object> getPersonWithStation(@RequestParam("station") final String id) {
        try {
            return ResponseEntity.ok(personService.getStation(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("childAlert")
    public ResponseEntity<Object> getChildAlert(@RequestParam("address") final String address) {
        try {
            return ResponseEntity.ok(personService.getChildAlert(address));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("phoneAlert")
    public ResponseEntity<Object> getPhoneNumberOfFireStation(@RequestParam("station") final String station) {
        try {
            return ResponseEntity.ok(personService.getPhoneNumberOfFireStation(station));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("fire")
    public ResponseEntity<Object> getFire(@RequestParam("address") final String address) {
        try {
            return ResponseEntity.ok(personService.getFire(address));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("flood/stations")
    public ResponseEntity<List<Person>> getListPersonFromListOfStations(@RequestParam("stations") final String stations) {
        try {
            return ResponseEntity.ok(personService.getListPersonFromListOfStation(stations));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("personInfo")
    public ResponseEntity<List<Person>> getListPersonInfoFromName(@RequestParam("firstName") final String firstName, @RequestParam("lastName") final String lastName) {
        try {
            return ResponseEntity.ok(personService.getListPersonInfoFromName(firstName,lastName));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("communityEmail")
    public ResponseEntity<Object> getEmailFromCity(@RequestParam("city") final String city) {
        try {
            return ResponseEntity.ok(personService.getEmailFromCity(city));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Update - Update an existing employee
     *
     * @param id     - The id of the employee to update
     * @param person - The employee object updated
     */
    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") final Long id, @RequestBody PersonRequest person) {
        try {
            return ResponseEntity.ok(personService.updatePerson(id, person));
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * Delete - Delete an employee
     *
     * @param id - The id of the employee to delete
     */
    @DeleteMapping("/person/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable("id") final Long id) {
        try {
            personService.getPerson(id);
            personService.deletePerson(id);

            return ResponseEntity.ok().build();
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Delete - Delete an employee
     */
    @DeleteMapping("/persons")
    public ResponseEntity<?> deletePersons() {
        personService.deletePersons();
        return ResponseEntity.noContent().build();
    }


}
