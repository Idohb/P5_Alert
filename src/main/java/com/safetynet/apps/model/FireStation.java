package com.safetynet.apps.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "fireStation")
public class FireStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "station")
    private int station;

}
