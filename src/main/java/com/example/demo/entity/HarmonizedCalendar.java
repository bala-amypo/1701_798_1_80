package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "harmonized_calendars")
public class HarmonizedCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "calendar_name", nullable = false)
    private String calendarName;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    
    @Column(name = "status")
    private String status;
    
    // Default constructor
    public HarmonizedCalendar() {}
    
    // Constructor with 5 parameters
    public HarmonizedCalendar(String calendarName, String description,
                             LocalDate startDate, LocalDate endDate, String status) {
        this.calendarName = calendarName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }
    
    // Constructor with 7 parameters (for test compatibility)
    public HarmonizedCalendar(Long id, String calendarName, String description,
                             LocalDateTime createdAt, LocalDate startDate,
                             LocalDate endDate, String status) {
        this.id = id;
        this.calendarName = calendarName;
        this.description = description;
        this.createdAt = createdAt;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCalendarName() { return calendarName; }
    public void setCalendarName(String calendarName) { this.calendarName = calendarName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    @PrePersist
    protected void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (status == null) {
            status = "DRAFT";
        }
    }
}