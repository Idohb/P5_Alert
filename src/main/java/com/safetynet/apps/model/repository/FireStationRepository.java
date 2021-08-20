package com.safetynet.apps.model.repository;


import com.safetynet.apps.model.entity.FireStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FireStationRepository extends JpaRepository<FireStationEntity, Long> {
    List<FireStationEntity> findByStation(String station);
}
