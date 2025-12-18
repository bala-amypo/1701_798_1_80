// src/main/java/com/example/demo/entity/BranchProfile.java
package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "branch_profiles", uniqueConstraints = {
    @UniqueConstraint(columnNames = "branchCode")
})
public class BranchProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "branch_code", nullable = false, unique = true)
    private String branchCode;
    
    @Column(name = "branch_name", nullable = false)
    private String branchName;
    
    @Column(name = "contact_email", nullable = false)
    private String contactEmail;
    
    @Column(name = "last_sync_at")
    private LocalDateTime lastSyncAt;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    @PrePersist
    protected void onCreate() {
        if (lastSyncAt == null) {
            lastSyncAt = LocalDateTime.now();
        }
    }
    
    // Constructors, Getters and Setters
    public BranchProfile() {}
    
    public BranchProfile(String branchCode, String branchName, String contactEmail) {
        this.branchCode = branchCode;
        this.branchName = branchName;
        this.contactEmail = contactEmail;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getBranchCode() { return branchCode; }
    public void setBranchCode(String branchCode) { this.branchCode = branchCode; }
    
    public String getBranchName() { return branchName; }
    public void setBranchName(String branchName) { this.branchName = branchName; }
    
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    
    public LocalDateTime getLastSyncAt() { return lastSyncAt; }
    public void setLastSyncAt(LocalDateTime lastSyncAt) { this.lastSyncAt = lastSyncAt; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}