package com.renatinha.fleetmanagement.responses;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class TaxiResponse {
    private Integer id;
    private String plate;
}
