package com.renatinha.fleetmanagement.controllers;

import com.renatinha.fleetmanagement.entities.Error;
import com.renatinha.fleetmanagement.entities.Taxi;
import com.renatinha.fleetmanagement.responses.PageResponse;
import com.renatinha.fleetmanagement.services.TaxiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping("/taxis")
public class TaxiController {

    //criando um atributo e/ou instanciando a classe service
    private final TaxiService taxiService;


    //construtor
    public TaxiController(TaxiService taxiService) {
        this.taxiService = taxiService;
    }


    @Operation(summary = "Busca uma lista com todos os taxis", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operação com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PageResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Parametros inválidos",
                    content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = Error.class)
                    )
            ),
    })

    @GetMapping
    public ResponseEntity<PageResponse> getAllTaxis(@ParameterObject Pageable pageable) {

        //declarando uma váriavel
        Page<Taxi> pages = taxiService.getAllTaxis(pageable);
        // dto para mapear os campos
        return ResponseEntity.ok(PageResponse.builder()
                .totalElements(pages.getTotalElements())
                .totalPages(pages.getTotalPages())
                .content(Collections.singletonList(pages.getContent()))
                //construa este objeto
                .build()
        );
    }
}
