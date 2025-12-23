package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "event_merge_records")
public class EventMergeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "original_event_name_1", nullable = false)
    private String originalEventName1;
    
    @Column(name = "original_event_name_2", nullable = false)
    private String originalEventName2;
    
    @Column(name = "merged_start_date", nullable = false)
    private LocalDate mergedStartDate;
    
    @Column(name = "merged_end_date", nullable = false)
    private LocalDate mergedEndDate;
    
    @Column(name = "merged_event_name", nullable = false)
    private String mergedEventName;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    // Default constructor
    public EventMergeRecord() {}
    
    // Constructor with 5 parameters
    public EventMergeRecord(String originalEventName1, String originalEventName2,
                           LocalDate mergedStartDate, LocalDate mergedEndDate,
                           String mergedEventName) {
        this.originalEventName1 = originalEventName1;
        this.originalEventName2 = originalEventName2;
        this.mergedStartDate = mergedStartDate;
        this.mergedEndDate = mergedEndDate;
        this.mergedEventName = mergedEventName;
    }
    
    // Constructor with 7 parameters (for test compatibility)
    public EventMergeRecord(Long id, String originalEventName1, String originalEventName2,
                           LocalDate mergedStartDate, LocalDate mergedEndDate,
                           String mergedEventName, LocalDateTime createdAt) {
        this.id = id;
        this.originalEventName1 = originalEventName1;
        this.originalEventName2 = originalEventName2;
        this.mergedStartDate = mergedStartDate;
        this.mergedEndDate = mergedEndDate;
        this.mergedEventName = mergedEventName;
        this.createdAt = createdAt;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getOriginalEventName1() { return originalEventName1; }
    public void setOriginalEventName1(String originalEventName1) { this.originalEventName1 = originalEventName1; }
    
    public String getOriginalEventName2() { return originalEventName2; }
    public void setOriginalEventName2(String originalEventName2) { this.originalEventName2 = originalEventName2; }
    
    public LocalDate getMergedStartDate() { return mergedStartDate; }
    public void setMergedStartDate(LocalDate mergedStartDate) { this.mergedStartDate = mergedStartDate; }
    
    public LocalDate getMergedEndDate() { return mergedEndDate; }
    public void setMergedEndDate(LocalDate mergedEndDate) { this.mergedEndDate = mergedEndDate; }
    
    public String getMergedEventName() { return mergedEventName; }
    public void setMergedEventName(String mergedEventName) { this.mergedEventName = mergedEventName; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    @PrePersist
    protected void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}