package com.example.carControlSystem.domain.phone;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.carControlSystem.domain.client.Client;

public interface PhoneRepository extends JpaRepository<Phone, String> {
    
    boolean existsByPhone(String phone);
    
    void deleteAllByClient(Client client);

}
