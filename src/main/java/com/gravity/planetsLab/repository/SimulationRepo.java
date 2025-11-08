package com.gravity.planetsLab.repository;
import com.gravity.planetsLab.models.Simulation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SimulationRepo extends JpaRepository<Simulation, Long>{
   
}
