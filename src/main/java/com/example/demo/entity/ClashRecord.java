package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "clash_records")
public class ClashRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long eventAId;
    
    @Column(nullable = false)
    private Long eventBId;
    
    @Column(nullable = false)
    private String clashType;
    
    @Column(nullable = false)
    private String severity;
    
    private String details;
    
    private LocalDateTime detectedAt;
    
    private Boolean resolved;
    
    // Constructors
    public ClashRecord() {
        this.resolved = false;
    }
    
    public ClashRecord(Long id, Long eventAId, Long eventBId, String clashType, String severity, 
                      String details, LocalDateTime detectedAt, Boolean resolved) {
        this.id = id;
        this.eventAId = eventAId;
        this.eventBId = eventBId;
        this.clashType = clashType;
        this.severity = severity;
        this.details = details;
        this.detectedAt = detectedAt;
        this.resolved = resolved != null ? resolved : false;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getEventAId() { return eventAId; }
    public void setEventAId(Long eventAId) { this.eventAId = eventAId; }
    
    public Long getEventBId() { return eventBId; }
    public void setEventBId(Long eventBId) { this.eventBId = eventBId; }
    
    public String getClashType() { return clashType; }
    public void setClashType(String clashType) { this.clashType = clashType; }
    
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
    
    public LocalDateTime getDetectedAt() { return detectedAt; }
    public void setDetectedAt(LocalDateTime detectedAt) { this.detectedAt = detectedAt; }
    
    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }
    
    @PrePersist
    public void prePersist() {
        if (detectedAt == null) {
            detectedAt = LocalDateTime.now();
        }
        if (resolved == null) {
            resolved = false;
        }
    }
}