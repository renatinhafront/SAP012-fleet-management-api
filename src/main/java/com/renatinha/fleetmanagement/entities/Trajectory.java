package com.renatinha.fleetmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity(name = "trajectories")
public class Trajectory {

    @Id
    private Integer id;
    private LocalDateTime date;
    private BigDecimal latitude;
    private BigDecimal longitude;

    //join lado forte da tabela
    @ManyToOne
    @JoinColumn(name="taxi_id")
    private Taxi taxi;
}
