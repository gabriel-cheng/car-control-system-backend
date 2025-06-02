package com.example.carControlSystem.domain.phone;

import ch.qos.logback.core.net.server.Client;

public record RequestPhone(
    String phone,
    Client client
) { }
