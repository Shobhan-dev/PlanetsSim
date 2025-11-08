package com.gravity.planetsLab.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gravity.planetsLab.models.Planet;
import com.gravity.planetsLab.models.Simulation;
import com.gravity.planetsLab.models.Trajectory;
import com.gravity.planetsLab.repository.PlanetRepo;
import com.gravity.planetsLab.repository.SimulationRepo;
import com.gravity.planetsLab.repository.TrajectoryRepo;

@Service
public class GravityService {

    @Autowired
    PlanetRepo planetRepo;

    @Autowired
    TrajectoryRepo trajectoryRepo;

    @Autowired
    SimulationRepo simulationRepo;

    public static final double g = 6.67430e-11;
    
    // Run steps no of simulations and in each simulation the time frame is dt.
    public List<Trajectory> runSimulation(double dt, double steps){
        List<Planet> allPlanets = planetRepo.findAll();
        Simulation sim = new Simulation();
        sim.setTimestep(steps);
        sim.settotalTime(dt);

        sim.setstatus("Started");
        sim.setStartTime(LocalDateTime.now());
        simulationRepo.save(sim);
        for (int i = 0; i < steps; i++) {
            simulateStep(allPlanets, dt);
        }
        sim.setstatus("Completed");
        sim.setEndTime(LocalDateTime.now());
        simulationRepo.save(sim);

        return trajectoryRepo.findAll();
    }

    // Single simulation 
    public List<Planet> simulateStep(List<Planet> allPlanets, double dt){
        double force[], acc[];
        for (Planet planet : allPlanets) {
            force = calculateNetForce(planet, allPlanets);
            acc   = calculateAcceleration(force[0], force[1], planet.getPlanetMass());
            updateVelocity(planet, acc[0], acc[1], dt);
            updatePosition(planet, dt);
            recordTrajectory(planet, dt);
        }
        return allPlanets;
    }

    // Update the trajectory table with new trajectory record;
    public Trajectory recordTrajectory(Planet planet, double time)
    {
        Trajectory t = new Trajectory();
        t.setplanetId(planet.getId());
        t.setTime(time);
        t.setPosX(planet.getPosX());
        t.setPosY(planet.getPosY());
        t.setVelX(planet.getVelX());
        t.setVelY(planet.getVelY());
        return trajectoryRepo.save(t);
    }

    // Calculate force on planet a from planet b;
    public double[] calculateForce(Planet a, Planet b){
        double rx, ry, r, fx, fy, f, rvx, rvy;

        rx = b.getPosX() - a.getPosX();
        ry = b.getPosY() - a.getPosY();
        r  = Math.sqrt(Math.pow(rx, 2) - Math.pow(ry, 2));          //Formula for straight line distance between two points on a 2D plane. Pythagoras theorem;
        rvx= rx/r;                                                      //to calculate the direction of force applied.
        rvy= ry/r;

        f = g * (a.getPlanetMass()*b.getPlanetMass())/Math.pow(r, 2); //Force formula F = G*(m1*m2)/r^2;
        fx = f * rvx;                                                   //Direction of force along x and y axis;
        fy = f * rvy;

        return new double[]{fx, fy};                                    
    }

    // Calculate total force applied on the planet a from all other planets;
    public double[] calculateNetForce(Planet a, List<Planet> allPlanets){
        double fx_total, fy_total, f[];

        fx_total = 0;
        fy_total = 0;

        for (Planet pl : allPlanets) {                                  //Loop through all the planets to calculate the force they apply on planet a.
            if(pl != a){
                f = calculateForce(a, pl);
                fx_total += f[0];
                fy_total += f[1];
            }
        }
        return new double[]{fx_total, fy_total};
    }    

    // calculate the acceleration using F=ma -> a = F/m;
    public double[] calculateAcceleration(double fx, double fy, double mass){
        double ax, ay;

        ax = fx/mass;
        ay = fy/mass;

        return new double[]{ax, ay};
    }

    // Update the velocity of the planet and update the velocity for the planet;
    public void updateVelocity(Planet planet, double ax, double ay, double dt){
        double vx, vy;

        // get the velocity of the planet;
        vx = planet.getVelX();
        vy = planet.getVelY();

        // update the velocity;
        vx += ax * dt;
        vy += ay * dt;

        // Set the updated velocity;
        planet.setVelX(vx);
        planet.setVelY(vy);
    }

    // Update the position of planet at time dt.
    public void updatePosition(Planet planet, double dt){
        double posX, posY, velX, velY;

        // get the position and velocity of the planet;
        posX = planet.getPosX();
        posY = planet.getPosY();
        velX = planet.getVelX();
        velY = planet.getVelY();

        // update the postion;
        posX += velX * dt;
        posY += velY * dt;

        // Set the updated position;
        planet.setPosX(posX);
        planet.setPosY(posY);
    }

    public Optional<Simulation> getSimulation(Long id){
        Optional<Simulation> sim = simulationRepo.findById(id);
        return sim;
    }

    public List<Simulation> getAllSimulations(){
        return simulationRepo.findAll();
    }

    public Optional<Trajectory> getTrajectory(Long id){
        Optional<Trajectory> trajectory = trajectoryRepo.findById(id);
        return trajectory;
    }
    
    public String deleteSimulation(Long id){
       simulationRepo.deleteById(id);
       return ("Deleted:" + id);
    }

    public String deleteTrajectory(){
       trajectoryRepo.deleteAll();
       return ("Deleted");
    }

    public List<Trajectory> planetTrajectory(Long id){
        return trajectoryRepo.findByPlanetId(id);

    }

}
