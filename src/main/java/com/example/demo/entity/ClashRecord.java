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
    
    @Column(name = "resolution_notes", columnDefinition = "TEXT") // Changed column name
    private String resolutionNotes; // Changed field name
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "resolved")
    private boolean resolved = false;
    
    // Constructors (update them)
    public ClashRecord() {}
    
    public ClashRecord(Long eventId1, Long eventId2, String description,
                      String resolutionNotes, String status) { // Updated parameter
        this.eventId1 = eventId1;
        this.eventId2 = eventId2;
        this.description = description;
        this.resolutionNotes = resolutionNotes; // Updated
        this.status = status;
    }
    
    // Getters and setters (add/update)
    public String getResolutionNotes() {
        return resolutionNotes;
    }
    
    public void setResolutionNotes(String resolutionNotes) {
        this.resolutionNotes = resolutionNotes;
    }
    
    // Remove old resolution getter/setter or keep for compatibility
    // public String getResolution() { return resolutionNotes; }
    // public void setResolution(String resolution) { this.resolutionNotes = resolution; }
    
    // ... rest of your existing code
}