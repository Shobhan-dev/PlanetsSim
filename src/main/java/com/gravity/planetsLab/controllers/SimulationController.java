package com.gravity.planetsLab.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gravity.planetsLab.models.Simulation;
import com.gravity.planetsLab.models.Trajectory;
import com.gravity.planetsLab.services.GravityService;

@RestController
@RequestMapping("/simulation")
public class SimulationController {

    @Autowired
    private GravityService service;

    @GetMapping("/{id}")
    public Optional<Simulation> getSim(@PathVariable Long id){
        return service.getSimulation(id);
    }

    @GetMapping("/simAll")
    public List<Simulation> simResult(){
        return service.getAllSimulations();
    }

    @GetMapping("/trajectory/{id}")
    public Optional<Trajectory> getTraject(@PathVariable Long id){
        return service.getTrajectory(id);
    }

    @GetMapping("/planet/{id}")
    public List<Trajectory> getPlanetMap(@PathVariable Long id){
        return service.planetTrajectory(id);
    }

    @PostMapping("/run")
    public List<Trajectory> runSim(@RequestBody Simulation sim){
        return service.runSimulation(sim.gettotalTime(), sim.getTimestep());
    }

    @DeleteMapping("/{id}")
    public String deleteSim(@PathVariable Long id){
        return service.deleteSimulation(id);
    }

    @DeleteMapping("/delete")
    public String removeTrajectory(){
        return service.deleteTrajectory();
    }
}
