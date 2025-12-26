package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HarmonizedCalendar {

    private Long id;
    private String title;
    private String generatedBy;
    private LocalDateTime generatedAt;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private String eventsJson;

    public void prePersist() {
        if (generatedAt == null) generatedAt = LocalDateTime.now();
    }

    // constructor, getters, setters
}
