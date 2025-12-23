package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "clash_records")
public class ClashRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "event_id_1", nullable = false)
    private Long eventId1;
    
    @Column(name = "event_id_2", nullable = false)
    private Long eventId2;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "resolution", columnDefinition = "TEXT")
    private String resolution;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "resolved")
    private boolean resolved = false;
    
    // Default constructor
    public ClashRecord() {}
    
    // Constructor with 5 parameters
    public ClashRecord(Long eventId1, Long eventId2, String description,
                      String resolution, String status) {
        this.eventId1 = eventId1;
        this.eventId2 = eventId2;
        this.description = description;
        this.resolution = resolution;
        this.status = status;
    }
    
    // Constructor with 8 parameters (for test compatibility)
    public ClashRecord(Long id, Long eventId1, Long eventId2, String description,
                      String resolution, String status, LocalDateTime createdAt,
                      boolean resolved) {
        this.id = id;
        this.eventId1 = eventId1;
        this.eventId2 = eventId2;
        this.description = description;
        this.resolution = resolution;
        this.status = status;
        this.createdAt = createdAt;
        this.resolved = resolved;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getEventId1() { return eventId1; }
    public void setEventId1(Long eventId1) { this.eventId1 = eventId1; }
    
    public Long getEventId2() { return eventId2; }
    public void setEventId2(Long eventId2) { this.eventId2 = eventId2; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getResolution() { return resolution; }
    public void setResolution(String resolution) { this.resolution = resolution; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public boolean isResolved() { return resolved; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }
    
    @PrePersist
    protected void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (status == null) {
            status = "DETECTED";
        }
    }
}