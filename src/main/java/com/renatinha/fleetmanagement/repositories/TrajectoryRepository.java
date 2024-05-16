package com.renatinha.fleetmanagement.repositories;

import com.renatinha.fleetmanagement.entities.Trajectory;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TrajectoryRepository extends PagingAndSortingRepository<Trajectory, Long> {
}
