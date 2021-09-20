package com.safetynet.apps.controller;

import com.safetynet.apps.controller.dto.FireStation.FireStationRequest;
import com.safetynet.apps.service.FireStationService;
import com.safetynet.apps.service.data.FireStation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@Slf4j
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
        try {
            log.info("GET fireStation succeed");
            return ResponseEntity.ok(fireStationService.getFireStations());
        } catch (NoSuchElementException e) {
            log.error("GET fireStation error : not found");
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("fireStation/{id}") //localhost:8080/fireStation?station=3
    public ResponseEntity<FireStation> getFireStation(@PathVariable("id") final Long id) {
        try {
            log.info("GET fireStation succeed");
            return ResponseEntity.ok(fireStationService.getFireStation(id));
        } catch (NoSuchElementException e) {
            log.error("GET fireStation error : not found");
            return ResponseEntity.notFound().build();
        }
    }




    @PostMapping("/fireStation")
    public ResponseEntity<FireStation> createFireStation(@RequestBody FireStationRequest fireStation) {
        try {
            log.info("POST add fireStation succeed");
            return ResponseEntity.ok(fireStationService.addFireStation(fireStation));
        } catch (IllegalArgumentException exception) {
            log.error("POST add fireStation error : illegal argument");
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/fireStation/{id}")
    public ResponseEntity<FireStation> updateFireStation(@PathVariable("id") final Long id, @RequestBody FireStationRequest fireStation) {
        try {
            log.info("PUT fireStation updated");
            return ResponseEntity.ok(fireStationService.updateFireStation(id, fireStation));
        } catch (NoSuchElementException exception) {
            log.error("PUT fireStation update error : not found");
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/fireStation/{id}")
    public ResponseEntity<?> deleteFireStation(@PathVariable("id") final Long id) {
        try {
            fireStationService.getFireStation(id);
            fireStationService.deleteFireStation(id);
            log.info("DELETE fireStation succeed");
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException exception) {
            log.error("DELETE fireStation error : not found");
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException exception) {
            log.error("DELETE fireStation error : illegal argument");
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/fireStations")
    public ResponseEntity<?> deleteFireStations() {
        log.info("DELETE all fireStation succeed");
        fireStationService.deleteFireStations();
        return ResponseEntity.noContent().build();
    }


}
