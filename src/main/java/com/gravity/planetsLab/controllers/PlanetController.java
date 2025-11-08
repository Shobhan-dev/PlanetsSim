package com.gravity.planetsLab.controllers;
import com.gravity.planetsLab.models.Planet;
import com.gravity.planetsLab.repository.PlanetRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;



@RestController
@RequestMapping("/planet")
public class PlanetController {

    @Autowired
    PlanetRepo planetRepo;

    @PostMapping("/planets")
    public Planet createPlanet(@RequestBody Planet pl){
        return planetRepo.save(pl);
    }

    @GetMapping("/planets")
    public List<Planet> getPlanets(){
        return planetRepo.findAll();
    }

    @GetMapping("/planets/{id}")
    public Optional<Planet> getPlanetid(@PathVariable Long id){
        Optional<Planet> pl = planetRepo.findById(id);
        System.out.print("the object: " + pl);
        return pl; 
    }

    @PutMapping("/planets/{id}")
    public Planet updatePlanet(@RequestBody Planet pl, @PathVariable Long id){
        // return updatePlanet(pl, id);
        Planet update = planetRepo.findById(id).map(m -> {
        m.setPlanetMass(pl.getPlanetMass());
        m.setPlanetName(pl.getPlanetName());
        m.setPosX(pl.getPosX());
        m.setPosY(pl.getPosY());
        m.setVelX(pl.getVelX());
        m.setVelY(pl.getVelY());
        return m;
        }).orElse(null);
        return planetRepo.save(update);
    }

    @DeleteMapping("/planets/{id}")
    public void deletePlanet(@PathVariable Long id){
        planetRepo.deleteById(id);
    }

}
