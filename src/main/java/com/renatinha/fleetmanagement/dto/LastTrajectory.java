package com.renatinha.fleetmanagement.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class LastTrajectory {

    @Id
    private int taxiId;
    private String plate;
    private LocalDateTime date;
    private Double longitude;
    private Double latitude;
}

