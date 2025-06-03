package com.example.carControlSystem.domain.vehicle;

import com.example.carControlSystem.domain.client.Client;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="vehicle")
@Entity(name="vehicle")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="vehicleId")
public class Vehicle {
    
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="vehicle_id")
    private String vehicleId;

    private String description;

    private String plate;

    private String brand;

    private String model;

    private String color;

    @ManyToOne
    @JoinColumn(name="client_id")
    @JsonIgnoreProperties({"vehicle"})
    private Client client;

    public Vehicle(RequestVehicle requestVehicle) {
        this.description = requestVehicle.description();
        this.plate = requestVehicle.plate();
        this.brand = requestVehicle.brand();
        this.model = requestVehicle.model();
        this.color = requestVehicle.color();
    }

}
