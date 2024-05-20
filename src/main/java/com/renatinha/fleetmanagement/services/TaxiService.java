package com.renatinha.fleetmanagement.services;

import com.renatinha.fleetmanagement.entities.LastTrajectory;
import com.renatinha.fleetmanagement.entities.Taxi;
import com.renatinha.fleetmanagement.repositories.LastTrajectoryRepository;
import com.renatinha.fleetmanagement.repositories.TaxiRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaxiService {

    //cria atributo repository que faz a busca no banco
    public final TaxiRepository taxiRepository;
    public final LastTrajectoryRepository lastTrajectoryRepository;

    //construtor
    public TaxiService(final TaxiRepository taxiRepository, LastTrajectoryRepository lastTrajectoryRepository) {
        this.taxiRepository = taxiRepository;
        this.lastTrajectoryRepository = lastTrajectoryRepository;
    }

    public Page<Taxi> getAllTaxis(Pageable pageable) {
        return taxiRepository.findAll(pageable);
    }

    public Page<LastTrajectory> getAllLastTrajectory(Pageable pageable) {
        return lastTrajectoryRepository.findAllTaxisAndYoursLastTrajectory(pageable);
    }
}