package com.renatinha.fleetmanagement.services;

import com.renatinha.fleetmanagement.dto.LastTrajectory;
import com.renatinha.fleetmanagement.entities.Taxi;
import com.renatinha.fleetmanagement.repositories.LastTrajectoryRepository;
import com.renatinha.fleetmanagement.repositories.TaxiRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

// JUnit framework para teste unit
// extensão com JUnit
@ExtendWith(SpringExtension.class)
public class TaxiServiceTest {

    @InjectMocks
    private TaxiService taxiService;

    @Mock
    private TaxiRepository taxiRepository;

    //mockito simular algo
    @Mock
    private LastTrajectoryRepository lastTrajectoryRepository;

    // método a ser testado sendo que ele seja público, sem retorno de argumentos e que seu nome tenha o prefixo test
    @Test
    public void testGetAllTaxis() {
        Taxi taxi = new Taxi();
        taxi.setId(7938);
        taxi.setPlate("HLBO-2375");

        when(taxiRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(taxi)));

        Page<Taxi> pages = taxiService.getAllTaxis(Pageable.unpaged());

        //assertions do junit para validar teste
        Assertions.assertEquals(pages.getTotalElements(), 1);
        Assertions.assertEquals(pages.getTotalPages(), 1);
        Assertions.assertEquals(pages.getContent().getFirst().getPlate(), "HLBO-2375");
        Assertions.assertEquals(pages.getContent().getFirst().getId(), 7938);

    }

    @Test
    public void testGetAllLastTrajectory() {
        LastTrajectory lastTrajectory = new LastTrajectory();

        LocalDateTime date = LocalDateTime.parse("2008-02-02T14:22:40", DateTimeFormatter.ISO_DATE_TIME);

        lastTrajectory.setLatitude(321.3455);
        lastTrajectory.setLongitude(123.456);
        lastTrajectory.setDate(date);
        lastTrajectory.setTaxiId(45);
        lastTrajectory.setPlate("HLBO-2375");

        when(lastTrajectoryRepository.findAllTaxisAndYoursLastTrajectory(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(lastTrajectory)));

        Page<LastTrajectory> pages = taxiService.getAllLastTrajectory(Pageable.unpaged());

        Assertions.assertEquals(pages.getTotalElements(), 1);
        Assertions.assertEquals(pages.getTotalPages(), 1);
        Assertions.assertEquals(pages.getContent().getFirst().getPlate(), "HLBO-2375");
        Assertions.assertEquals(pages.getContent().getFirst().getTaxiId(), 45);
        Assertions.assertEquals(pages.getContent().getFirst().getDate(), date);
        Assertions.assertEquals(pages.getContent().getFirst().getLongitude(), 123.456);
        Assertions.assertEquals(pages.getContent().getFirst().getLatitude(),321.3455);

    }
}
