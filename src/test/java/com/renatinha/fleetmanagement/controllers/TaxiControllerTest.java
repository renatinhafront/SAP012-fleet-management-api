package com.renatinha.fleetmanagement.controllers;


import com.renatinha.fleetmanagement.dto.LastTrajectory;
import com.renatinha.fleetmanagement.entities.Taxi;
import com.renatinha.fleetmanagement.entities.Trajectory;
import com.renatinha.fleetmanagement.services.TaxiService;
import com.renatinha.fleetmanagement.services.TrajectoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//   mockmvc testa controlador
//   foca apenas na camada web e não inicia o contexto completo da aplicação, tornando os testes mais rápidos
@WebMvcTest(TaxiController.class)
class TaxiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TaxiService taxiService;

    @MockBean
    private TrajectoryService trajectoryService;

    @Test
    @DisplayName("Deve retornar uma lista de todos os táxis")
    public void deveraRetornarUmaListaComTodosTaxis() throws Exception {
        Taxi taxi = new Taxi();
        taxi.setId(3859);
        taxi.setPlate("GPGM-7365");

        // quando eu chamar o service com metodo que busca todos os taxis quero que retorne uma nova pagina de taxi
        // com o taxi mockado
        // any(Pageable.class) qualquer coisa que estiver no parametro
        when(taxiService.getAllTaxis(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(taxi)));
        // espero que retorne
        mockMvc.perform(get("/taxis"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.content[0].id").value(taxi.getId()))
                .andExpect(jsonPath("$.content[0].plate").value(taxi.getPlate()))

        .andReturn();
    }

    @Test
    @DisplayName("Deve retornar trajetórias por Id do táxi")
    public void deveraRetornarAsTrajetoriasPorIdDoTaxis() throws Exception {
        Trajectory trajectoryMock = new Trajectory();
        Taxi taxiMock = new Taxi();

        LocalDateTime date = LocalDateTime.parse("2008-02-02T14:22:40", DateTimeFormatter.ISO_DATE_TIME);

        taxiMock.setId(6418);
        trajectoryMock.setTaxi(taxiMock);
        trajectoryMock.setDate(date);
        trajectoryMock.setLatitude(116.30508);
        trajectoryMock.setLongitude(39.96525);
        trajectoryMock.setId(6418);

        when(trajectoryService.getTrajectoriesByTaxiId(any(), any(), any(), any())).thenReturn(new PageImpl<>(List.of(trajectoryMock)));
        // espero que retorne
        mockMvc.perform(get("/taxis/{id}/trajectories", taxiMock.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.content[0].date").exists())
                .andExpect(jsonPath("$.content[0].latitude").value(116.30508))
                .andExpect(jsonPath("$.content[0].longitude").value(39.96525))


        .andReturn();
    }


    @Test
    @DisplayName("Deve retornar a ultima trajetoria de cada taxi")
    public void deveraRetornarUltimaTrajetoriaDeCadaTaxi() throws Exception {
        LastTrajectory lastTrajectory = new LastTrajectory();

        LocalDateTime date = LocalDateTime.parse("2008-02-02T14:22:40", DateTimeFormatter.ISO_DATE_TIME);

        lastTrajectory.setTaxiId(3859);
        lastTrajectory.setPlate("GHGH-1458");
        lastTrajectory.setDate(date);
        lastTrajectory.setLatitude(116.30508);
        lastTrajectory.setLongitude(39.96525);


        when(taxiService.getAllLastTrajectory(any())).thenReturn(new PageImpl<>(List.of(lastTrajectory)));
        // espero que retorne
        mockMvc.perform(get("/taxis/trajectories/last"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.content[0].taxiId").value(3859))
                .andExpect(jsonPath("$.content[0].plate").value("GHGH-1458"))
                .andExpect(jsonPath("$.content[0].date").exists())
                .andExpect(jsonPath("$.content[0].latitude").value(116.30508))
                .andExpect(jsonPath("$.content[0].longitude").value(39.96525))

                .andReturn();
   }
}
