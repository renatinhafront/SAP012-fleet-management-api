package com.renatinha.fleetmanagement.services;


import com.renatinha.fleetmanagement.entities.Trajectory;
import com.renatinha.fleetmanagement.repositories.TrajectoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TrajectoryService {

    public final TrajectoryRepository trajectoryRepository;

    public TrajectoryService(final TrajectoryRepository trajectoryRepository) {
        this.trajectoryRepository = trajectoryRepository;
    }

    public Page<Trajectory> getTrajectoriesByTaxiId(Integer taxiId,
                                                    LocalDateTime startDate,
                                                    LocalDateTime endDate,
                                                    Pageable pageable) {
        return trajectoryRepository.findAllByTaxiIdAndDateBetween(taxiId, startDate, endDate, pageable);
    }
}
