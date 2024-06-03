package com.renatinha.fleetmanagement.controllers;

import com.renatinha.fleetmanagement.dto.Error;
import com.renatinha.fleetmanagement.dto.LastTrajectory;
import com.renatinha.fleetmanagement.entities.Taxi;
import com.renatinha.fleetmanagement.entities.Trajectory;
import com.renatinha.fleetmanagement.responses.*;
import com.renatinha.fleetmanagement.services.TaxiService;
import com.renatinha.fleetmanagement.services.TrajectoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Táxis")
//é um controller onde cada método retorna um objeto de domínio diretamente para o corpo da resposta HTTP.
@RestController
//Define o caminho base para todos os métodos neste controller, que é /taxis
@RequestMapping("/taxis")
public class TaxiController {

    //criando um atributo e/ou instanciando a classe service
    private final TaxiService taxiService;
    private final TrajectoryService trajectoryService;


    //construtor
    public TaxiController(TaxiService taxiService, TrajectoryService trajectoryService) {
        this.taxiService = taxiService;
        this.trajectoryService = trajectoryService;
    }


    @Operation(
            summary = "Busca uma lista com todos os táxis",
            method = "GET")
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

        List<TaxiResponse> taxiResponses = pages.getContent().stream()
                .map(taxi -> TaxiResponse.builder()
                        .id(taxi.getId())
                        .plate(taxi.getPlate())
                        .build())
                .toList();


        // dto para mapear os campos
        return ResponseEntity.ok(PageResponse.builder()
                .totalElements(pages.getTotalElements())
                .totalPages(pages.getTotalPages())
                .content(taxiResponses)
                //construa este objeto
                .build()
        );
    }


    @Operation(
            summary = "Busca trajetórias por ID do táxi",
            method = "GET")
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

    //O endpoint responde para o id do táxi e uma data consultado as seguintes informações:
    // latitude, longitude e timestamp (data e hora).

    @GetMapping("/{id}/trajectories")
    public ResponseEntity<PageResponse> getTrajectoriesByTaxiId(
            @PathVariable @Parameter (example = "6418") Integer id,
            @Parameter(example = "2008-02-02T00:00:00") LocalDateTime startDate,
            @Parameter(example = "2008-02-02T23:59:59") LocalDateTime endDate,
            @ParameterObject Pageable pageable) {

        // trajetórias por ID do táxi
        Page<Trajectory> pages = trajectoryService.getTrajectoriesByTaxiId(id, startDate, endDate, pageable);

        List<TrajectoryResponse> trajectoryResponses = pages.getContent().stream()
                .map(taxi -> TrajectoryResponse.builder()
                        .date(taxi.getDate())
                        .longitude(taxi.getLongitude())
                        .latitude(taxi.getLatitude())
                        .build())
                .toList();

        // Construir a resposta com PageResponse genérico
        PageResponse response = PageResponse.builder()
                .totalElements(pages.getTotalElements())
                .totalPages(pages.getTotalPages())
                .content(trajectoryResponses)
                .build();

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Busca a última localização reportada por cada táxi",
            method = "GET")
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


    @GetMapping("/trajectories/last")
    public ResponseEntity<PageResponse> getAllTaxisLastTrajectory(@ParameterObject Pageable pageable) {

        //classe Page de paginação
        Page<LastTrajectory> pages = taxiService.getAllLastTrajectory(pageable);

        // Construir a resposta com PageResponse genérico
        PageResponse response = PageResponse.builder()
                .totalElements(pages.getTotalElements())
                .totalPages(pages.getTotalPages())
                .content(pages.getContent())
                .build();

        return ResponseEntity.ok(response);

    }
}
