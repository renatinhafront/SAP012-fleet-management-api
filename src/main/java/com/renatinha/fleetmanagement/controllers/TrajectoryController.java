//package com.renatinha.fleetmanagement.controllers;
//
//import com.renatinha.fleetmanagement.entities.Trajectory;
//import com.renatinha.fleetmanagement.responses.PageResponse;
//import com.renatinha.fleetmanagement.responses.TrajectoryResponse;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springdoc.core.annotations.ParameterObject;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//
////O endpoint responde para cada táxi as seguintes informações:
//// id, placa, latitude, longitude e timestamp (data e hora).
//
//
//@Tag(name = "Taxi")
//@GetMapping("/{id}/trajectories")
//public ResponseEntity<PageResponse> getLastTrajectories(
//        @PathVariable @Parameter(example = "6418") Integer id,
//        @Parameter
//        @Parameter(example = "2008-02-02T00:00:00") LocalDateTime startDate,
//        @Parameter(example = "2008-02-02T23:59:59") LocalDateTime endDate,
//        @ParameterObject Pageable pageable) {
//
//    // trajetórias por ID do táxi
//    Page<Trajectory> pages = trajectoryService.getLastTrajectories(id, startDate, endDate, pageable);
//
//    List<TrajectoryResponse> trajectoryResponses = pages.getContent().stream()
//            .map(t -> TrajectoryResponse.builder()
//                    .dateTime(t.getDate())
//                    .longitude(t.getLongitude())
//                    .latitude(t.getLatitude())
//                    .build())
//            .toList();
//
//    // Construir a resposta com PageResponse genérico
//    PageResponse response = PageResponse.builder()
//            .totalElements(pages.getTotalElements())
//            .totalPages(pages.getTotalPages())
//            .content(trajectoryResponses)
//            .build();
//
//    return ResponseEntity.ok(response);
//}
//
//}
