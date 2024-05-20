package com.renatinha.fleetmanagement.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class LastTrajectory {

    @Id
    private Long taxiId;
    private String plate;
    private String date;
    private Double longitude;
    private Double latitude;
}

