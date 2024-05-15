package com.renatinha.fleetmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "taxis")
public class Taxi {

    @Id
    private int id;
    private String plate;


    public Taxi() {
    }
}
