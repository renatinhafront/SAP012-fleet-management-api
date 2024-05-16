package com.renatinha.fleetmanagement.repositories;


import com.renatinha.fleetmanagement.entities.Taxi;
import org.springframework.data.repository.PagingAndSortingRepository;

// spring data PagingAndSortingRepository = fornece métodos para fazer paginação e classificação de registros
public interface TaxiRepository extends PagingAndSortingRepository<Taxi, Long> {
}
