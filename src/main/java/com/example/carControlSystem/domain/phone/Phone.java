package com.example.carControlSystem.domain.phone;

import com.example.carControlSystem.domain.client.Client;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="phone")
@Entity(name="phone")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="phoneId")
public class Phone {
    
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="phone_id")
    private String phoneId;

    private String phone;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    public Phone(RequestPhone requestPhone) {
        this.phone = requestPhone.phone();
    }
    
}
