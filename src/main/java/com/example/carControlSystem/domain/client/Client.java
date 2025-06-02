package com.example.carControlSystem.domain.client;

import java.util.ArrayList;
import java.util.List;

import com.example.carControlSystem.domain.phone.Phone;

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

    private String name;

    private String email;

    private String address;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phone = new ArrayList<>();

    public Client(RequestClient requestClient) {
        this.name = requestClient.name();
        this.email = requestClient.email();
        this.address = requestClient.address();
    }

}
