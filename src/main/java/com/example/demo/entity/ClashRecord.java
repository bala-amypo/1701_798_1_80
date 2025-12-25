package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "clash_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private Boolean resolved = false;
    
    @Column(name = "resolution")
    private String resolution;
    
    @PrePersist
    protected void onCreate() {
        if (detectedAt == null) {
            detectedAt = LocalDateTime.now();
        }
    }
}