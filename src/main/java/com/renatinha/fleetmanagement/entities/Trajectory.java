package com.renatinha.fleetmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity(name = "trajectories")
public class Trajectory {

    @Id
    private Integer id;
    private Integer taxiId;
    private LocalDateTime date;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
