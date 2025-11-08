package com.gravity.planetsLab.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Simulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String status;
    private double timestep, totalTime;
    private LocalDateTime startTime, endTime;

    public Simulation(){}

    public Simulation(String status, double timestep, double totalTime, LocalDateTime startTime, LocalDateTime endTime){
        this.status    = status;
        this.timestep  = timestep;
        this.totalTime = totalTime;
        this.endTime   = endTime;
        this.startTime = startTime;
    }
    
    public Long getId() {
        return id;
    }
    public String getstatus() {
        return status;
    }
    public double getTimestep() {
        return timestep;
    }
    public double gettotalTime() {
        return totalTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setstatus(String status) {
        this.status = status;
    }
    public void setTimestep(double timestep) {
        this.timestep = timestep;
    }
    public void settotalTime(double totalTime) {
        this.totalTime = totalTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

}
