package com.renatinha.fleetmanagement.repositories;

import com.renatinha.fleetmanagement.entities.LastTrajectory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;



public interface LastTrajectoryRepository extends PagingAndSortingRepository<LastTrajectory, Long> {

    @Query(value = """
        SELECT
            distinct on (t.taxi_id)
            t.taxi_id,
            tx.plate,
            t.date,
            t.longitude,
            t.latitude
          FROM trajectories t
         INNER JOIN taxis tx ON tx.id = t.taxi_id
        ORDER BY t.taxi_id, t.date DESC;
    """, nativeQuery = true)
    Page<LastTrajectory> findAllTaxisAndYoursLastTrajectory(Pageable pageable);


}
