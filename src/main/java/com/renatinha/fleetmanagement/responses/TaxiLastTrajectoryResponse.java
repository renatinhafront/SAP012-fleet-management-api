package com.renatinha.fleetmanagement.responses;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class TaxiLastTrajectoryResponse {
    private Integer id;
    private String plate;
    private LocalDateTime dateTime;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
