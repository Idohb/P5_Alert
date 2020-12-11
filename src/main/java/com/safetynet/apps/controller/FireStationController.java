package com.safetynet.apps.controller;

import com.safetynet.apps.model.FireStation;
import com.safetynet.apps.model.Person;
import com.safetynet.apps.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class FireStationController {

    @Autowired
    private FireStationService fireStationService;

    /**
     * Read - get all fireStation
     * @return - An iterrable objext of FireStation full filled
     */

    @RequestMapping(value = "/fireStations", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<FireStation> getFireStations() {
        return fireStationService.getFireStations();
    }

    @GetMapping("fireStation/{id}")
    public FireStation getFireStations(@PathVariable("id") final Long id) {
        Optional<FireStation> fireStation = fireStationService.getFireStation(id);
        if(fireStation.isPresent()) {
            return fireStation.get();
        } else {
            return null;
        }
    }

    /**
     * Create a fireStation
     * @param fireStation
     * @return
     */
    @PostMapping("/fireStation")
    public FireStation createFireStation(@RequestBody FireStation fireStation) {
        return fireStationService.saveFireStation(fireStation);
    }

    /**
     * Update - Update an existing employee
     * @param id - The id of the employee to update
     * @param fireStation - The employee object updated
     * @return
     */
    @PutMapping("/fireStation/{id}")
    public FireStation updateFireStation(@PathVariable("id") final Long id, @RequestBody FireStation fireStation) {
        Optional<FireStation> e = fireStationService.getFireStation(id);
        if(e.isPresent()) {
            FireStation currentFireStation = e.get();

            String address = fireStation.getAddress();
            if(address != null) {
                currentFireStation.setAddress(address);
            }


            int station = fireStation.getStation();
            if(station != 0) {                  // need test
                currentFireStation.setStation(station);
            }

            fireStationService.saveFireStation(currentFireStation);
            return currentFireStation;

        } else {
            return null;
        }
    }


    /**
     * Delete - Delete an employee
     * @param id - The id of the employee to delete
     */
    @DeleteMapping("/fireStation/{id}")
    public void deleteFireStation(@PathVariable("id") final Long id) {
        fireStationService.deleteFireStation(id);
    }

    /**
     * Delete - Delete an employee
     */
    @DeleteMapping("/fireStations")
    public void deleteFireStations() {
        fireStationService.deleteFireStations();
    }


}
