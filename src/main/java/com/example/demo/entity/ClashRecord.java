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
    
    @Column
    private String description;
    
    @Column(nullable = false)
    private LocalDateTime detectedAt;
    
    @Column(nullable = false)
    private Boolean resolved;

    public ClashRecord() {}

    public ClashRecord(Long id, Long eventAId, Long eventBId, String clashType, 
                      String severity, String description, LocalDateTime detectedAt, Boolean resolved) {
        this.id = id;
        this.eventAId = eventAId;
        this.eventBId = eventBId;
        this.clashType = clashType;
        this.severity = severity;
        this.description = description;
        this.detectedAt = detectedAt;
        this.resolved = resolved;
    }

    @PrePersist
    protected void prePersist() {
        if (this.detectedAt == null) {
            this.detectedAt = LocalDateTime.now();
        }
        if (this.resolved == null) {
            this.resolved = false;
        }
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
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getDetectedAt() { return detectedAt; }
    public void setDetectedAt(LocalDateTime detectedAt) { this.detectedAt = detectedAt; }
    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }
}