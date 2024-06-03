package com.renatinha.fleetmanagement.dto;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class LastTrajectory {

    @Id
    private int taxiId;
    private String plate;
    private String date;
    private Double longitude;
    private Double latitude;
}

