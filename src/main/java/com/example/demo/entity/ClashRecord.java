package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "clash_record")
public class ClashRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "event_id1")
    private Long eventId1;
    
    @Column(name = "event_id2")
    private Long eventId2;
    
    @Column(name = "clash_type")
    private String clashType;
    
    @Column(name = "severity")
    private String severity;
    
    @Column(name = "detected_at")
    private LocalDateTime detectedAt;
    
    @Column(name = "resolved")
    private boolean resolved = false;
    
    @Column(name = "resolution")
    private String resolution;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getEventId1() { return eventId1; }
    public void setEventId1(Long eventId1) { this.eventId1 = eventId1; }
    public Long getEventId2() { return eventId2; }
    public void setEventId2(Long eventId2) { this.eventId2 = eventId2; }
    public String getClashType() { return clashType; }
    public void setClashType(String clashType) { this.clashType = clashType; }
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    public LocalDateTime getDetectedAt() { return detectedAt; }
    public void setDetectedAt(LocalDateTime detectedAt) { this.detectedAt = detectedAt; }
    public boolean isResolved() { return resolved; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }
    public String getResolution() { return resolution; }
    public void setResolution(String resolution) { this.resolution = resolution; }
}