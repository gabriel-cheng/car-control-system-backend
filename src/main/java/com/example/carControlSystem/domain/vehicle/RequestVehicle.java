package com.example.carControlSystem.domain.vehicle;

public record RequestVehicle(
    String description,
    String plate,
    String brand,
    String model,
    String color,
    String client
) { }
