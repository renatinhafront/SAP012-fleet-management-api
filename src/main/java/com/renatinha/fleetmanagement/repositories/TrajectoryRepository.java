package com.renatinha.fleetmanagement.repositories;

import com.renatinha.fleetmanagement.entities.Trajectory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;

public interface TrajectoryRepository extends PagingAndSortingRepository<Trajectory, Integer> {
    Page<Trajectory> findAllByTaxiIdAndDateBetween(
            Integer taxiId,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable);
}