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
    
    @Column(name = "source_event_ids", nullable = false, length = 1000)
    private String sourceEventIds;
    
    @Column(name = "merged_title", nullable = false)
    private String mergedTitle;
    
    @Column(name = "merged_start_date", nullable = false)
    private LocalDate mergedStartDate;
    
    @Column(name = "merged_end_date", nullable = false)
    private LocalDate mergedEndDate;
    
    @Column(name = "merge_reason", nullable = false)
    private String mergeReason;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    public EventMergeRecord() {}
    
    public EventMergeRecord(String sourceEventIds, String mergedTitle, 
                           LocalDate mergedStartDate, LocalDate mergedEndDate, 
                           String mergeReason) {
        this.sourceEventIds = sourceEventIds;
        this.mergedTitle = mergedTitle;
        this.mergedStartDate = mergedStartDate;
        this.mergedEndDate = mergedEndDate;
        this.mergeReason = mergeReason;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getSourceEventIds() { return sourceEventIds; }
    public void setSourceEventIds(String sourceEventIds) { this.sourceEventIds = sourceEventIds; }
    
    public String getMergedTitle() { return mergedTitle; }
    public void setMergedTitle(String mergedTitle) { this.mergedTitle = mergedTitle; }
    
    public LocalDate getMergedStartDate() { return mergedStartDate; }
    public void setMergedStartDate(LocalDate mergedStartDate) { this.mergedStartDate = mergedStartDate; }
    
    public LocalDate getMergedEndDate() { return mergedEndDate; }
    public void setMergedEndDate(LocalDate mergedEndDate) { this.mergedEndDate = mergedEndDate; }
    
    public String getMergeReason() { return mergeReason; }
    public void setMergeReason(String mergeReason) { this.mergeReason = mergeReason; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}