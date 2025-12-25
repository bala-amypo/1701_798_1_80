package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "harmonized_calendar")
public class HarmonizedCalendar {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calendarId;
    
    @Column(name = "calendar_name", nullable = false)
    private String calendarName;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "effective_from")
    private LocalDateTime effectiveFrom;
    
    @Column(name = "effective_to")
    private LocalDateTime effectiveTo;
    
    @Column(name = "priority")
    private Integer priority;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    public Long getCalendarId() {
        return calendarId;
    }
    
    public void setCalendarId(Long calendarId) {
        this.calendarId = calendarId;
    }
    
    public String getCalendarName() {
        return calendarName;
    }
    
    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalDateTime getEffectiveFrom() {
        return effectiveFrom;
    }
    
    public void setEffectiveFrom(LocalDateTime effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }
    
    public LocalDateTime getEffectiveTo() {
        return effectiveTo;
    }
    
    public void setEffectiveTo(LocalDateTime effectiveTo) {
        this.effectiveTo = effectiveTo;
    }
    
    public Integer getPriority() {
        return priority;
    }
    
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (isActive == null) {
            isActive = true;
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}