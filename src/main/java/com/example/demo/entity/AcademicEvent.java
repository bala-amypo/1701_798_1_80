package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "academic_events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademicEvent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "event_name", nullable = false)
    private String eventName;
    
    @Column(name = "event_type")
    private String eventType;
    
    @Column(name = "start_time")
    private LocalDateTime startTime;
    
    @Column(name = "end_time")
    private LocalDateTime endTime;
    
    @Column(name = "location")
    private String location;
    
    @Column(name = "organizer")
    private String organizer;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "priority")
    private Integer priority;
    
    @Column(name = "recurrence_pattern")
    private String recurrencePattern;
}