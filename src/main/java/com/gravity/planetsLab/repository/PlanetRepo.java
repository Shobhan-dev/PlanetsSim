package com.gravity.planetsLab.repository;
import com.gravity.planetsLab.models.Planet;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetRepo extends JpaRepository<Planet, Long>{
    
}
