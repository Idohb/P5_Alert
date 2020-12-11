package com.safetynet.apps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.safetynet.apps.model.Person;
import com.safetynet.apps.service.PersonService;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@SpringBootApplication
public class AppsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppsApplication.class, args);
    }

    /*@Bean
    CommandLineRunner runner(PersonService personService) {
        return args -> {
            // read json and write to db
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/data.json");
            try {

                List<Person> persons = mapper.readValue(inputStream,
                        TypeFactory.defaultInstance().constructCollectionType(List.class, Person.class));
                personService.save(persons);
                System.out.println("Users Saved!");
            } catch (IOException e){
                System.out.println("Unable to save persons: " + e.getMessage());
            }
        };
    }*/


}
