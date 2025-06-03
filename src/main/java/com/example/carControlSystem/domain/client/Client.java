package com.example.carControlSystem.domain.client;

import java.util.ArrayList;
import java.util.List;

import com.example.carControlSystem.domain.phone.Phone;
import com.example.carControlSystem.domain.vehicle.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="client")
@Entity(name="client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="clientId")
public class Client {
    
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="client_id")
    private String clientId;

    private String firstname;

    private String surname;

    private String email;

    private String address;

    @JsonIgnoreProperties({"phoneId", "client"})
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phone = new ArrayList<>();

    @JsonIgnoreProperties({"client"})
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicle = new ArrayList<>();

    public Client(RequestClient requestClient) {
        this.firstname = requestClient.firstname();
        this.surname = requestClient.surname();
        this.email = requestClient.email();
        this.address = requestClient.address();
    }

}
