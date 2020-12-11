package com.safetynet.apps.controller;


import com.safetynet.apps.model.Person;
import com.safetynet.apps.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    /**
     * Read - get all person
     * @return - An iterrable objext of Person full filled
     */

    @RequestMapping(value = "/persons", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Person> getPersons() {
        return personService.getPersons();
    }

    @GetMapping("person/{id}")
    public Person getPersons(@PathVariable("id") final Long id) {
        Optional<Person> person = personService.getPerson(id);
        if(person.isPresent()) {
            return person.get();
        } else {
            return null;
        }
    }

    /**
     * Create a person
     * @param person
     * @return
     */
    @PostMapping("/person")
    public Person createPerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    /**
     * Update - Update an existing employee
     * @param id - The id of the employee to update
     * @param person - The employee object updated
     * @return
     */
    @PutMapping("/employee/{id}")
    public Person updatePerson(@PathVariable("id") final Long id, @RequestBody Person person) {
        Optional<Person> e = personService.getPerson(id);
        if(e.isPresent()) {
            Person currentPerson = e.get();

            String firstName = person.getFirstName();
            if(firstName != null) {
                currentPerson.setFirstName(firstName);
            }

            String lastName = person.getLastName();
            if(lastName != null) {
                currentPerson.setLastName(lastName);;
            }

            String address = person.getAddress();
            if(address != null) {
                currentPerson.setCity(address);
            }

            int zip = person.getZip();
            if(zip != 0) {                  // need test
                currentPerson.setZip(zip);
            }

            String city = person.getCity();
            if(city != null) {
                currentPerson.setCity(city);
            }

            String email = person.getEmail();
            if(email != null) {
                currentPerson.setEmail(email);
            }

            personService.savePerson(currentPerson);
            return currentPerson;

        } else {
            return null;
        }
    }


    /**
     * Delete - Delete an employee
     * @param id - The id of the employee to delete
     */
    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable("id") final Long id) {
        personService.deletePerson(id);
    }

    /**
     * Delete - Delete an employee
     */
    @DeleteMapping("/persons")
    public void deletePerson() {
        personService.deletePersons();
    }


}
