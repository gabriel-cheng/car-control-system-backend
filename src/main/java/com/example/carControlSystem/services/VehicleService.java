package com.example.carControlSystem.services;

import org.springframework.stereotype.Service;

import com.example.carControlSystem.domain.client.Client;
import com.example.carControlSystem.domain.client.ClientRepository;
import com.example.carControlSystem.domain.vehicle.RequestVehicle;
import com.example.carControlSystem.domain.vehicle.Vehicle;
import com.example.carControlSystem.domain.vehicle.VehicleRepository;
import com.example.carControlSystem.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class VehicleService {
    
    private final VehicleRepository vehicleRepository;

    private final ClientRepository clientRepository;

    public VehicleService(
        VehicleRepository vehicleRepository,
        ClientRepository clientRepository
    ) {
        this.vehicleRepository = vehicleRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional
    public void updateVehicle(String id, RequestVehicle vehicle)
       throws ResourceNotFoundException {
        Vehicle vehicleFound = vehicleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "The vehicle " + id + " not found!"
            ));

        vehicleFound.setDescription(vehicle.description());
        vehicleFound.setPlate(vehicle.plate().toUpperCase());
        vehicleFound.setBrand(vehicle.brand());
        vehicleFound.setModel(vehicle.model());
        vehicleFound.setColor(vehicle.color());

        Client clientFound = clientRepository.findById(vehicle.client())
            .orElseThrow(() -> new ResourceNotFoundException("Client " + vehicle.client() + " not found!"));

        vehicleFound.setClient(clientFound);
    }

}
