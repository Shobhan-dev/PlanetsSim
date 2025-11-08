package com.gravity.planetsLab.repository;
import com.gravity.planetsLab.models.Trajectory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrajectoryRepo extends JpaRepository<Trajectory, Long>{
    @Query("SELECT pl FROM Trajectory pl WHERE pl.planetId = :planetId")
    List<Trajectory> findByPlanetId(@Param("planetId") Long id);

}
