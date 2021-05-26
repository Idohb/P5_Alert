package com.safetynet.apps.controller.dto;

import com.safetynet.apps.model.entity.FireStationEntity;
import lombok.Data;

@Data
public class FireStationDTO {

    private String address;
    private String station;

    public FireStationDTO(FireStationEntity fireStation) {
        address = fireStation.getAddress();
        station = fireStation.getStation();
    }

}
