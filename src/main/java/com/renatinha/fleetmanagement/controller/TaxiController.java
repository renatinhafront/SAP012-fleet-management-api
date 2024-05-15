package com.renatinha.fleetmanagement.controller;

import com.renatinha.fleetmanagement.model.Taxi;
import com.renatinha.fleetmanagement.service.TaxiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/taxis")
public class TaxiController {

    //criando um atributo e/ou instanciando a classe service
    private final TaxiService taxiService;

    //construtor
    public TaxiController(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @Operation(summary = "Get a taxi list", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Parametros inválidos", content = @Content),
    })
    @GetMapping
    public Page<Taxi> getAllTaxis(@RequestParam int pageNumber, @RequestParam int pageSize) {
        PageRequest request = PageRequest.of(pageNumber, pageSize);
        return taxiService.getAllTaxis(request);
    }
}
