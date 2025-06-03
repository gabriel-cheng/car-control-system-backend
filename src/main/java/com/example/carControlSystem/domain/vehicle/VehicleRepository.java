package com.example.carControlSystem.domain.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Object> {
    
    boolean existsByPlate(String plate);
    
}
