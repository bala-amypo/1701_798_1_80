package com.example.demo.entity;

import java.time.LocalDateTime;

public class UserAccount {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String department;
    private LocalDateTime createdAt;

    public UserAccount() {}

    public UserAccount(Long id, String name, String email, String password, String role, String department, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.department = department;
        this.createdAt = createdAt;
    }

    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (role == null) role = "REVIEWER";
    }

    // getters and setters
}
