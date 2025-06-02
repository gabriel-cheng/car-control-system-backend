package com.example.carControlSystem.domain.client;

import java.util.List;

import com.example.carControlSystem.domain.phone.Phone;

public record RequestClient(
    String name,
    String email,
    String address,
    List<Phone> phone
) { }
