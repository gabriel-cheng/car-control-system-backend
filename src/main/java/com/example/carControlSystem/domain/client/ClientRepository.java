package com.example.carControlSystem.domain.client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
    boolean existsByEmail(String email);
}
