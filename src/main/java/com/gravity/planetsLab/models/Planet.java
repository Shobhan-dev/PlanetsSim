package com.gravity.planetsLab.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private String planetName;
    private double planetMass, posX, posY, velX, velY;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Planet(){};

    public Planet (String planetName, double planetMass, double posX, double posY, double velX, double velY, LocalDateTime createdAt){
        this.planetName = planetName;
        this.planetMass = planetMass;
        this.posX       = posX;
        this.posY       = posY;
        this.velX       = velX;
        this.velY       = velY;
        this.createdAt  = createdAt;
    }

    public Long getId() {
        return id;
    }
    public double getPlanetMass() {
        return planetMass;
    }
    public String getPlanetName() {
        return planetName;
    }
    public double getPosX() {
        return posX;
    }
    public double getPosY() {
        return posY;
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
    public void setPlanetMass(double planetMass) {
        this.planetMass = planetMass;
    }
    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }
    public void setPosX(double posX) {
        this.posX = posX;
    }
    public void setPosY(double posY) {
        this.posY = posY;
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