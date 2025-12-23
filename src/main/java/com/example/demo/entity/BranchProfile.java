package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "branch_profiles")
public class BranchProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "branch_name", nullable = false)
    private String branchName;
    
    @Column(name = "location", nullable = false)
    private String location;
    
    @Column(name = "contact_info")
    private String contactInfo;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "is_active")
    private boolean isActive = true;
    
    // Default constructor
    public BranchProfile() {}
    
    // Constructor with 3 parameters
    public BranchProfile(String branchName, String location, String contactInfo) {
        this.branchName = branchName;
        this.location = location;
        this.contactInfo = contactInfo;
    }
    
    // Constructor with 6 parameters (for test compatibility)
    public BranchProfile(Long id, String branchName, String location, 
                         String contactInfo, LocalDateTime createdAt, boolean isActive) {
        this.id = id;
        this.branchName = branchName;
        this.location = location;
        this.contactInfo = contactInfo;
        this.createdAt = createdAt;
        this.isActive = isActive;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getBranchName() { return branchName; }
    public void setBranchName(String branchName) { this.branchName = branchName; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    
    @PrePersist
    protected void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}