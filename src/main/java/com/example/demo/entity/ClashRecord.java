package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "clash_record")
public class ClashRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long eventId1;
    private Long eventId2;
    private String clashType;
    private String severity;
    private LocalDateTime detectedAt;
    private Boolean resolved = false;
    private String resolution;
    
    // Getters and setters:
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getEventId1() { return eventId1; }
    public void setEventId1(Long eventId1) { this.eventId1 = eventId1; }
    
    public Long getEventId2() { return eventId2; }
    public void setEventId2(Long eventId2) { this.eventId2 = eventId2; }
    
    public String getClashType() { return clashType; }
    public