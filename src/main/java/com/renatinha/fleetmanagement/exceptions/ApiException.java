package com.renatinha.fleetmanagement.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiException extends RuntimeException {
    private String message;
}
