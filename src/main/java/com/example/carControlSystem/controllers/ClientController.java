package com.example.carControlSystem.controllers;

import java.util.ArrayList;
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
import com.example.carControlSystem.domain.phone.Phone;
import com.example.carControlSystem.domain.phone.PhoneRepository;
import com.example.carControlSystem.exceptions.ItemAlreadyExistsException;
import com.example.carControlSystem.exceptions.ResourceCannotBeNullException;
import com.example.carControlSystem.exceptions.ResourceNotFoundException;
import com.example.carControlSystem.helpers.ResponseHelper;
import com.example.carControlSystem.services.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseHelper.getListEntity(
            clientRepository::findAll,
            "Client"
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getOneClient(@PathVariable String id)
        throws ResourceNotFoundException {
        Client clientFound = clientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found!"));

        return ResponseEntity.status(HttpStatus.OK)
            .body(clientFound);
    }

    @PostMapping
    public ResponseEntity<String> registerNewClient(@RequestBody @Validated RequestClient client)
        throws ItemAlreadyExistsException, ResourceCannotBeNullException {
        boolean existsByEmail = clientRepository.existsByEmail(client.email());
        if(existsByEmail) {
            throw new ItemAlreadyExistsException("This email is not available, please try another one.");
        }

        if(client.phone().isEmpty()) {
            throw new ResourceCannotBeNullException("You must provide at least 1 contact phone number!");
        }
        if(!client.email().contains("@")) {
            throw new ResourceCannotBeNullException("You must provide a valid email address to continue!");
        }

        Client newClient = new Client(client);

        List<Phone> phones = new ArrayList<>();
        for(String phoneNumber : client.phone()) {
            boolean exists = phoneRepository.existsByPhone(phoneNumber);
            if (exists) {
                throw new ItemAlreadyExistsException(
                    "Phone number " + phoneNumber + " already exists. Please enter another one!"
                );
            }

            Phone phone = new Phone();
            phone.setPhone(phoneNumber);
            phone.setClient(newClient);

            phones.add(phone);
        }

        clientRepository.save(newClient);
        phoneRepository.saveAll(phones);

        return ResponseEntity.status(HttpStatus.OK)
            .body("Client created successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateExistsClient(
        @RequestBody @Validated RequestClient client,
        @PathVariable String id
    ) throws ItemAlreadyExistsException, ResourceNotFoundException {
        clientService.updateClient(id, client);

        return ResponseEntity.status(HttpStatus.OK)
            .body("Client update successfully!");
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
