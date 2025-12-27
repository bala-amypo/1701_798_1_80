// package com.example.demo.entity;

// import jakarta.persistence.*;
// import java.time.LocalDate;
// import java.time.LocalDateTime;

// @Entity
// @Table(name = "harmonized_calendars")
// public class HarmonizedCalendar {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     @Column(nullable = false)
//     private String title;
    
//     @Column(nullable = false)
//     private String generatedBy;
    
//     private LocalDateTime generatedAt;
    
//     @Column(nullable = false)
//     private LocalDate effectiveFrom;
    
//     @Column(nullable = false)
//     private LocalDate effectiveTo;
    
//     @Column(columnDefinition = "TEXT")
//     private String eventsJson;
    
//     // Constructors
//     public HarmonizedCalendar() {}
    
//     public HarmonizedCalendar(Long id, String title, String generatedBy, LocalDateTime generatedAt, 
//                              LocalDate effectiveFrom, LocalDate effectiveTo, String eventsJson) {
//         this.id = id;
//         this.title = title;
//         this.generatedBy = generatedBy;
//         this.generatedAt = generatedAt;
//         this.effectiveFrom = effectiveFrom;
//         this.effectiveTo = effectiveTo;
//         this.eventsJson = eventsJson;
//     }
    
//     // Getters and Setters
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }
    
//     public String getTitle() { return title; }
//     public void setTitle(String title) { this.title = title; }
    
//     public String getGeneratedBy() { return generatedBy; }
//     public void setGeneratedBy(String generatedBy) { this.generatedBy = generatedBy; }
    
//     public LocalDateTime getGeneratedAt() { return generatedAt; }
//     public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
    
//     public LocalDate getEffectiveFrom() { return effectiveFrom; }
//     public void setEffectiveFrom(LocalDate effectiveFrom) { this.effectiveFrom = effectiveFrom; }
    
//     public LocalDate getEffectiveTo() { return effectiveTo; }
//     public void setEffectiveTo(LocalDate effectiveTo) { this.effectiveTo = effectiveTo; }
    
//     public String getEventsJson() { return eventsJson; }
//     public void setEventsJson(String eventsJson) { this.eventsJson = eventsJson; }
    
//     @PrePersist
//     public void prePersist() {
//         if (generatedAt == null) {
//             generatedAt = LocalDateTime.now();
//         }
//     }
// }

package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "harmonized_calendar")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HarmonizedCalendar {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "generated_by", nullable = false)
    private String generatedBy;
    
    @CreationTimestamp
    @Column(name = "generated_at", updatable = false)
    private LocalDateTime generatedAt;
    
    @Column(name = "effective_from", nullable = false)
    private LocalDate effectiveFrom;
    
    @Column(name = "effective_to", nullable = false)
    private LocalDate effectiveTo;
    
    @Column(name = "events_json", columnDefinition = "TEXT")
    private String eventsJson;
    
    // Constructor without id for creating new records
    public HarmonizedCalendar(String title, String generatedBy, 
                             LocalDate effectiveFrom, LocalDate effectiveTo, 
                             String eventsJson) {
        this.title = title;
        this.generatedBy = generatedBy;
        this.effectiveFrom = effectiveFrom;
        this.effectiveTo = effectiveTo;
        this.eventsJson = eventsJson;
    }
    
    // Constructor for test (as shown in test file)
    public HarmonizedCalendar(Long id, String title, String generatedBy, 
                             LocalDateTime generatedAt, 
                             LocalDate effectiveFrom, LocalDate effectiveTo, 
                             String eventsJson) {
        this.id = id;
        this.title = title;
        this.generatedBy = generatedBy;
        this.generatedAt = generatedAt;
        this.effectiveFrom = effectiveFrom;
        this.effectiveTo = effectiveTo;
        this.eventsJson = eventsJson;
    }
    
    // Constructor for test line 765 (as shown in test)
    public HarmonizedCalendar(Long id, String title, String generatedBy, 
                             LocalDateTime generatedAt, 
                             LocalDate effectiveFrom, LocalDate effectiveTo) {
        this.id = id;
        this.title = title;
        this.generatedBy = generatedBy;
        this.generatedAt = generatedAt;
        this.effectiveFrom = effectiveFrom;
        this.effectiveTo = effectiveTo;
        this.eventsJson = "[]";
    }
    
    @PrePersist
    protected void prePersist() {
        if (generatedAt == null) {
            generatedAt = LocalDateTime.now();
        }
        if (eventsJson == null) {
            eventsJson = "[]";
        }
    }
    
    // Helper method to check if a date is within range
    public boolean isDateInRange(LocalDate date) {
        return !date.isBefore(effectiveFrom) && !date.isAfter(effectiveTo);
    }
    
    // Getters and Setters (handled by Lombok @Data)
}