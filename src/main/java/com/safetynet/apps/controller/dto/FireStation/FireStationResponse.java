package com.safetynet.apps.controller.dto.FireStation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FireStationResponse {

    private Long idFireStation;

    private String address;

    private String station;

}
