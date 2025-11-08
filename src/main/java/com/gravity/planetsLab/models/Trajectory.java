package com.gravity.planetsLab.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Trajectory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long planetId;
    private double posX, posY, time, velX, velY;
    private LocalDateTime createdAt = LocalDateTime.now();


    public Trajectory(){}

    public Trajectory(Long planetId, double posX, double posY, double velX, double velY, double time, LocalDateTime createdAt){
        this.planetId = planetId;
        this.posX     = posX;
        this.posY     = posY;
        this.velX     = velX;
        this.velY     = velY;
        this.time     = time;
        this.createdAt  = createdAt;

    }

    public Long getId() {
        return id;
    }
    public Long getplanetId() {
        return planetId;
    }
    public double getPosX() {
        return posX;
    }
    public double getPosY() {
        return posY;
    }
    public double getTime() {
        return time;
    }
    public double getVelX() {
        return velX;
    }
    public double getVelY() {
        return velY;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setplanetId(Long planetId) {
        this.planetId = planetId;
    }
    public void setPosX(double posX) {
        this.posX = posX;
    }
    public void setPosY(double posY) {
        this.posY = posY;
    }
    public void setTime(double time) {
        this.time = time;
    }
    public void setVelX(double velX) {
        this.velX = velX;
    }
    public void setVelY(double velY) {
        this.velY = velY;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
