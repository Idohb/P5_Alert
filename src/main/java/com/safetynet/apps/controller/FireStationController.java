package com.safetynet.apps.controller;

import com.safetynet.apps.controller.dto.FireStation.FireStationRequest;
import com.safetynet.apps.service.FireStationService;
import com.safetynet.apps.service.data.FireStation;
import com.safetynet.apps.service.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
public class FireStationController {

    @Autowired
    private FireStationService fireStationService;

    /**
     * Read - get all fireStation
     *
     * @return - An iterrable objext of FireStation full filled
     */

    @GetMapping("/fireStations")
    public ResponseEntity<List<FireStation>> getFireStations() {
            return ResponseEntity.ok(fireStationService.getFireStations());
    }

    @GetMapping("fireStation/{id}") //localhost:8080/fireStation?station=3
    public ResponseEntity<FireStation> getFireStation(@PathVariable("id") final Long id) {
        try {
            return ResponseEntity.ok(fireStationService.getFireStation(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }




    @PostMapping("/fireStation")
    public ResponseEntity<FireStation> createFireStation(@RequestBody FireStationRequest fireStation) {
        try {
            return ResponseEntity.ok(fireStationService.addFireStation(fireStation));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/fireStation/{id}")
    public ResponseEntity<FireStation> updateFireStation(@PathVariable("id") final Long id, @RequestBody FireStationRequest fireStation) {
        try {
            return ResponseEntity.ok(fireStationService.updateFireStation(id, fireStation));
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/fireStation/{id}")
    public ResponseEntity<?> deleteFireStation(@PathVariable("id") final Long id) {
        try {
            fireStationService.getFireStation(id);
            fireStationService.deleteFireStation(id);

            return ResponseEntity.ok().build();
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/fireStations")
    public ResponseEntity<?> deleteFireStations() {
        fireStationService.deleteFireStations();
        return ResponseEntity.noContent().build();
    }


}
