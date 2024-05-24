package com.renatinha.fleetmanagement.responses;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class TrajectoryResponse {
    private LocalDateTime date;
    private double latitude;
    private double longitude;
}
