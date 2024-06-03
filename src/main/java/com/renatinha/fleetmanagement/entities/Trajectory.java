package com.renatinha.fleetmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;


//data -encapsulamento- gera em tempo de execuçao os métodos get e set e outros
@Data
@Entity(name = "trajectories")
public class Trajectory {

    @Id
    private Integer id;
    private LocalDateTime date;
    private double latitude;
    private double longitude;

    //join lado forte da tabela
    @ManyToOne
    @JoinColumn(name="taxi_id")
    private Taxi taxi;
}
