package com.example.carControlSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carControlSystem.domain.client.Client;
import com.example.carControlSystem.domain.client.ClientRepository;
import com.example.carControlSystem.domain.vehicle.RequestVehicle;
import com.example.carControlSystem.domain.vehicle.Vehicle;
import com.example.carControlSystem.domain.vehicle.VehicleRepository;
import com.example.carControlSystem.exceptions.ItemAlreadyExistsException;
import com.example.carControlSystem.exceptions.ResourceCannotBeNullException;
import com.example.carControlSystem.exceptions.ResourceNotFoundException;
import com.example.carControlSystem.helpers.ResponseHelper;
import com.example.carControlSystem.services.VehicleService;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseHelper.getListEntity(
            vehicleRepository::findAll,
            "Vehicle"
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getOneVehicle(@PathVariable String id)
    throws ResourceNotFoundException {
        return ResponseHelper.getOneEntity(
            id,
            vehicleRepository::findById,
            "Vehicle"
        );
    }

    @PostMapping
    public ResponseEntity<String> registerNewVehicle(@RequestBody @Validated RequestVehicle vehicle)
        throws ItemAlreadyExistsException, ResourceCannotBeNullException, ResourceNotFoundException {
        boolean existsByPlate = vehicleRepository.existsByPlate(vehicle.plate());
        if(existsByPlate) {
            throw new ItemAlreadyExistsException(
                "The vehicle with license plate " + vehicle.plate() + " already exists, please inform another one!"
            );
        }

        Client clientFound = clientRepository.findById(vehicle.client())
            .orElseThrow(() -> new ResourceNotFoundException("Client " + vehicle.client() + " not found!"));

        Vehicle newVehicle = new Vehicle(vehicle);
        newVehicle.setClient(clientFound);

        vehicleRepository.save(newVehicle);

        return ResponseEntity.status(HttpStatus.OK)
            .body("Vehicle registered successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOneVehicle(
        @PathVariable String id,
        @RequestBody @Validated RequestVehicle vehicle
    ) throws ResourceNotFoundException {
        vehicleService.updateVehicle(id, vehicle);

        return ResponseEntity.status(HttpStatus.OK)
            .body("Vehicle updated successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable String id)
    throws ResourceNotFoundException {
        return ResponseHelper.deleteEntity(
            () -> vehicleRepository.findById(id),
            vehicleRepository::delete,
            "Vehicle",
            id
        );
    }

}
