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
    
    @Column(nullable = false)
    private String title;
    
    @Column(name = "generated_by", nullable = false)
    private String generatedBy;
    
    @Column(name = "generated_at", nullable = false, updatable = false)
    private LocalDateTime generatedAt;
    
    @Column(name = "effective_from", nullable = false)
    private LocalDate effectiveFrom;
    
    @Column(name = "effective_to", nullable = false)
    private LocalDate effectiveTo;
    
    @Column(name = "events_json", columnDefinition = "TEXT", nullable = false)
    private String eventsJson;
    
    @PrePersist
    protected void onCreate() {
        generatedAt = LocalDateTime.now();
    }
    
    public HarmonizedCalendar() {}
    
    public HarmonizedCalendar(String title, String generatedBy, 
                             LocalDate effectiveFrom, LocalDate effectiveTo, 
                             String eventsJson) {
        this.title = title;
        this.generatedBy = generatedBy;
        this.effectiveFrom = effectiveFrom;
        this.effectiveTo = effectiveTo;
        this.eventsJson = eventsJson;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getGeneratedBy() { return generatedBy; }
    public void setGeneratedBy(String generatedBy) { this.generatedBy = generatedBy; }
    
    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
    
    public LocalDate getEffectiveFrom() { return effectiveFrom; }
    public void setEffectiveFrom(LocalDate effectiveFrom) { this.effectiveFrom = effectiveFrom; }
    
    public LocalDate getEffectiveTo() { return effectiveTo; }
    public void setEffectiveTo(LocalDate effectiveTo) { this.effectiveTo = effectiveTo; }
    
    public String getEventsJson() { return eventsJson; }
    public void setEventsJson(String eventsJson) { this.eventsJson = eventsJson; }
}