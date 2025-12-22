package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "clash_records")
public class ClashRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "event_a_id", nullable = false)
    private Long eventAId;
    
    @Column(name = "event_b_id", nullable = false)
    private Long eventBId;
    
    @Column(name = "clash_type", nullable = false)
    private String clashType;
    
    @Column(nullable = false)
    private String severity;
    
    @Column(length = 1000)
    private String details;
    
    @Column(name = "detected_at", nullable = false, updatable = false)
    private LocalDateTime detectedAt;
    
    @Column(nullable = false)
    private Boolean resolved = false;
    
    @PrePersist
    protected void onCreate() {
        detectedAt = LocalDateTime.now();
    }
    
    public ClashRecord() {}
    
    public ClashRecord(Long eventAId, Long eventBId, String clashType, 
                      String severity, String details) {
        this.eventAId = eventAId;
        this.eventBId = eventBId;
        this.clashType = clashType;
        this.severity = severity;
        this.details = details;
    }
    
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
}