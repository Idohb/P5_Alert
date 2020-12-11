package com.safetynet.apps.repository;


import com.safetynet.apps.model.FireStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FireStationRepository extends JpaRepository<FireStation, Long> {
}
