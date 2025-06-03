package com.example.carControlSystem.domain.client;

import java.util.List;

public record RequestClient(
    String firstname,
    String surname,
    String email,
    String address,
    List<String> phone
) { }
