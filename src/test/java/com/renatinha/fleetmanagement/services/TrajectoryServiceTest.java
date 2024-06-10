package com.renatinha.fleetmanagement.services;

import com.renatinha.fleetmanagement.entities.Taxi;
import com.renatinha.fleetmanagement.entities.Trajectory;
import com.renatinha.fleetmanagement.repositories.TrajectoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrajectoryServiceTest {

    @InjectMocks
    private TrajectoryService trajectoryService;

    @Mock
    private TrajectoryRepository trajectoryRepository;

    @Test
    public void testGetAllTaxis() {
        //arrange - prepara
        Taxi taxi = new Taxi();

        //converte o texto com a data em um obj localdateTime
        // no padrão de formato iso 8601
        LocalDateTime date = LocalDateTime.parse("2008-02-02T14:22:40", DateTimeFormatter.ISO_DATE_TIME);

        Trajectory trajectory = new Trajectory();
        trajectory.setTaxi(taxi);
        trajectory.setId(56);
        trajectory.setDate(date);
        trajectory.setLongitude(567.6789);
        trajectory.setLatitude(123.9876);

        //act - ação
        //quando trajectoryRespository.buscar todos o taxis por id entre a data (parametros qualquer um)
        when(trajectoryRepository.findAllByTaxiIdAndDateBetween(any(), any(), any(), any()))
                //quero que retorne uma nova pag com a lista de trajetoria
                .thenReturn(new PageImpl<>(List.of(trajectory)));


        //assert - afirmação
        //pagina coma lista de trajetorias
        Page<Trajectory> pages = trajectoryService.getTrajectoriesByTaxiId(
                taxi.getId(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                Pageable.unpaged());

        Assertions.assertEquals(pages.getTotalElements(), 1);
        Assertions.assertEquals(pages.getTotalPages(), 1);
        Assertions.assertEquals(pages.getContent().getFirst().getTaxi().getId(), taxi.getId());
        Assertions.assertEquals(pages.getContent().getFirst().getDate(), date);
        Assertions.assertEquals(pages.getContent().getFirst().getLongitude(), 567.6789);
        Assertions.assertEquals(pages.getContent().getFirst().getLatitude(), 123.9876);
    }

}
