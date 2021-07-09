package com.safetynet.apps.controller;


import com.safetynet.apps.controller.dto.Person.PersonRequest;
import com.safetynet.apps.model.entity.PersonEntity;
import com.safetynet.apps.service.PersonService;
import com.safetynet.apps.service.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;


    /**
     * Read - get all person
     *
     * @return - An iterrable objext of Person full filled
     */

    @GetMapping("persons")
    public ResponseEntity<List<Person>> getPersons() {
            return ResponseEntity.ok(personService.getPersons());
    }


    @GetMapping("person/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") final Long id) {
        try {
            return ResponseEntity.ok(personService.getPerson(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.noContent().build();
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
