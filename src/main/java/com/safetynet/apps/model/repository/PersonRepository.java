package com.safetynet.apps.model.repository;

import com.safetynet.apps.model.entity.FireStationEntity;
import com.safetynet.apps.model.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
//    List<PersonEntity> findAllByFireStationEntityIsContaining(FireStationEntity fireStationEntity);

    List<PersonEntity> findByAddress(String address);

    List<PersonEntity> findByFirstNameAndLastName(String firstName, String lastName);

    List<PersonEntity> findByCity(String city);
//    List<PersonEntity> findBy
}
