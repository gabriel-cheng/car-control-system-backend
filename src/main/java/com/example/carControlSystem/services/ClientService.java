package com.example.carControlSystem.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.carControlSystem.domain.client.Client;
import com.example.carControlSystem.domain.client.ClientRepository;
import com.example.carControlSystem.domain.client.RequestClient;
import com.example.carControlSystem.domain.phone.Phone;
import com.example.carControlSystem.domain.phone.PhoneRepository;
import com.example.carControlSystem.exceptions.ItemAlreadyExistsException;
import com.example.carControlSystem.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final PhoneRepository phoneRepository;

    public ClientService(
        ClientRepository clientRepository,
        PhoneRepository phoneRepository
    ) {
        this.clientRepository = clientRepository;
        this.phoneRepository = phoneRepository;
    }
    
    @Transactional
    public void updateClient(String id, RequestClient client)
        throws ResourceNotFoundException, ItemAlreadyExistsException {
        Client existingClient = clientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Client " + id + " not found!"));

        boolean emailAlreadyExists = clientRepository.existsByEmail(client.email());

        boolean isChangingEmail = !client.email().equalsIgnoreCase(existingClient.getEmail());

        if(emailAlreadyExists && isChangingEmail) {
            throw new ItemAlreadyExistsException("This email is not available, please try another one.");
        }

        phoneRepository.deleteAllByClient(existingClient);

        List<Phone> phones = client.phone().stream()
            .map(phoneNumbers -> {
                Phone p = new Phone();
                p.setClient(existingClient);
                p.setPhone(phoneNumbers);

                return p;
            }).collect(Collectors.toList());

        existingClient.setFirstname(client.firstname());
        existingClient.setSurname(client.surname());
        existingClient.setEmail(client.email());
        existingClient.setAddress(client.address());

        clientRepository.save(existingClient);
        phoneRepository.saveAll(phones);
    }

}
