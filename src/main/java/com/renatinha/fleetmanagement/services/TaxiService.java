package com.renatinha.fleetmanagement.services;

import com.renatinha.fleetmanagement.entities.Taxi;
import com.renatinha.fleetmanagement.repositories.TaxiRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaxiService {

    //cria atributo repository que faz a busca no banco
    public final TaxiRepository taxiRepository;

    //construtor
    public TaxiService(final TaxiRepository taxiRepository) {
        this.taxiRepository = taxiRepository;
    }

    //
    public Page<Taxi> getAllTaxis(Pageable pageable) {
        return taxiRepository.findAll(pageable);
    }
}