package com.safetynet.apps.model.repository;


import com.safetynet.apps.model.entity.FireStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FireStationRepository extends JpaRepository<FireStationEntity, Long> {
}
