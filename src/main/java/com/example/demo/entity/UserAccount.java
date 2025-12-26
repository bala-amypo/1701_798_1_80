package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String role;

    // PrePersist method called by UserAccountServiceImpl
    @PrePersist
    public void prePersist() {
        // Optional: Can leave empty or initialize default values
    }
}
