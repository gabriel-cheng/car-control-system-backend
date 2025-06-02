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
import com.example.carControlSystem.domain.client.RequestClient;
import com.example.carControlSystem.exceptions.ItemAlreadyExistsException;
import com.example.carControlSystem.exceptions.ResourceNotFoundException;
import com.example.carControlSystem.helpers.ResponseHelper;

@RestController
@RequestMapping("/client")
public class ClientController {
    
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseHelper.getListEntity(
            clientRepository::findAll,
            "Client"
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getOneClient(@PathVariable String id) throws ResourceNotFoundException {
        Client clientFound = clientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found!"));

        return ResponseEntity.status(HttpStatus.OK)
            .body(clientFound);
    }

    @PostMapping
    public ResponseEntity<String> registerNewClient(@RequestBody @Validated RequestClient client) throws ItemAlreadyExistsException {
        boolean existsByEmail = clientRepository.existsByEmail(client.email());
        if(existsByEmail) {
            throw new ItemAlreadyExistsException("This email is not available, please try another one.");
        }

        Client newClient = new Client(client);
        clientRepository.save(newClient);

        return ResponseEntity.status(HttpStatus.OK)
            .body("Client created successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateExistsClient(
        @RequestBody @Validated RequestClient client,
        @PathVariable String id
    ) throws ItemAlreadyExistsException, ResourceNotFoundException {
        Client clientFound = clientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Client " + id + " not found!"));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntity(@PathVariable String id) throws ResourceNotFoundException {
        return ResponseHelper.deleteEntity(
            () -> clientRepository.findById(id),
            clientRepository::delete,
            "Client",
            id
        );
    }

}
