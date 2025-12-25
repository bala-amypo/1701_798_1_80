package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "clash_record")
public class ClashRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "event_id1", nullable = false)
    private Long eventId1;
    
    @Column(name = "event_id2", nullable = false)
    private Long eventId2;
    
    @Column(name = "clash_type", nullable = false)
    private String clashType;
    
    @Column(name = "severity")
    private String severity;
    
    @Column(name = "detected_at")
    private LocalDateTime detectedAt;
    
    @Column(name = "resolved")
    private boolean resolved = false;
    
    @Column(name = "resolution")
    private String resolution;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public ClashRecord() {
        this.detectedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }
    
    public ClashRecord(Long eventId1, Long eventId2, String clashType, String severity) {
        this.eventId1 = eventId1;
        this.eventId2 = eventId2;
        this.clashType = clashType;
        this.severity = severity;
        this.detectedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getEventId1() {
        return eventId1;
    }
    
    public void setEventId1(Long eventId1) {
        this.eventId1 = eventId1;
    }
    
    public Long getEventId2() {
        return eventId2;
    }
    
    public void setEventId2(Long eventId2) {
        this.eventId2 = eventId2;
    }
    
    public String getClashType() {
        return clashType;
    }
    
    public void setClashType(String clashType) {
        this.clashType = clashType;
    }
    
    public String getSeverity() {
        return severity;
    }
    
    public void setSeverity(String severity) {
        this.severity = severity;
    }
    
    public LocalDateTime getDetectedAt() {
        return detectedAt;
    }
    
    public void setDetectedAt(LocalDateTime detectedAt) {
        this.detectedAt = detectedAt;
    }
    
    public boolean isResolved() {
        return resolved;
    }
    
    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
    
    public String getResolution() {
        return resolution;
    }
    
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        detectedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    @Override
    public String toString() {
        return "ClashRecord{" +
                "id=" + id +
                ", eventId1=" + eventId1 +
                ", eventId2=" + eventId2 +
                ", clashType='" + clashType + '\'' +
                ", severity='" + severity + '\'' +
                ", resolved=" + resolved +
                '}';
    }
}