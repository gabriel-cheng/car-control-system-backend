package com.example.carControlSystem.domain.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carControlSystem.domain.client.Client;

public interface VehicleRepository extends JpaRepository<Vehicle, Object> {
    
    boolean existsByPlate(String plate);
    
    void deleteByClient(Client client);

}
