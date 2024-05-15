package com.renatinha.fleetmanagement.service;

import com.renatinha.fleetmanagement.model.Taxi;
import com.renatinha.fleetmanagement.repository.TaxiRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxiService {

    //cria atributo repository que faz a busca no banco
    public final TaxiRepository taxiRepository;

    //construtor
    public TaxiService(final TaxiRepository taxiRepository) {
        this.taxiRepository = taxiRepository;
    }
    //cria a lista de todos os taxis
    public List<Taxi> getAllTaxis() {
        return taxiRepository.findAll();
    }

    //
    public Page<Taxi> getAllTaxis(Pageable pageable) {
        return taxiRepository.findAll(pageable);
    }
}