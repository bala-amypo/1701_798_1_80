package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "branch_profiles")
@Data
public class BranchProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "branch_name", nullable = false)
    private String branchName;
    
    @Column(name = "branch_code", unique = true)
    private String branchCode;
    
    @Column(name = "department")
    private String department;
    
    @Column(name = "contact_email")
    private String contactEmail;
    
    @Column(name = "contact_phone")
    private String contactPhone;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "is_active")
    private boolean active;
}