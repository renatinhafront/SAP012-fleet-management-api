package com.renatinha.fleetmanagement.repository;


import com.renatinha.fleetmanagement.model.Taxi;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

// spring data PagingAndSortingRepository = fornece métodos para fazer paginação e classificação de registros
public interface TaxiRepository extends PagingAndSortingRepository<Taxi, Long> {
    List<Taxi> findAll();
}
