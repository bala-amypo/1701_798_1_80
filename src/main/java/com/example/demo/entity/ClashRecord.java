package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "clash_records")
public class ClashRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long event1Id;
    private String event1Name;
    private Long event2Id;
    private String event2Name;
    private LocalDateTime clashTime;
    private String clashType; // e.g., "time", "location", "both"
    private String status; // e.g., "pending", "resolved", "ignored"
    private boolean resolved; // Add this field
    private LocalDateTime detectedAt; // Add this field
    private String severity; // Add this field
    private String resolution; // Add this field
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getEvent1Id() { return event1Id; }
    public void setEvent1Id(Long event1Id) { this.event1Id = event1Id; }
    
    public String getEvent1Name() { return event1Name; }
    public void setEvent1Name(String event1Name) { this.event1Name = event1Name; }
    
    public Long getEvent2Id() { return event2Id; }
    public void setEvent2Id(Long event2Id) { this.event2Id = event2Id; }
    
    public String getEvent2Name() { return event2Name; }
    public void setEvent2Name(String event2Name) { this.event2Name = event2Name; }
    
    public LocalDateTime getClashTime() { return clashTime; }
    public void setClashTime(LocalDateTime clashTime) { this.clashTime = clashTime; }
    
    public String getClashType() { return clashType; }
    public void setClashType(String clashType) { this.clashType = clashType; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    // New getters and setters
    public boolean isResolved() { return resolved; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }
    
    public LocalDateTime getDetectedAt() { return detectedAt; }
    public void setDetectedAt(LocalDateTime detectedAt) { this.detectedAt = detectedAt; }
    
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    
    public String getResolution() { return resolution; }
    public void setResolution(String resolution) { this.resolution = resolution; }
}