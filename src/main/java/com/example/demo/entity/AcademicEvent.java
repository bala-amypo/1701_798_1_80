package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AcademicEvent {

    private Long id;
    private Long branchId;
    private String title;
    private String eventType;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime submittedAt;

    public void prePersist() {
        if (submittedAt == null) submittedAt = LocalDateTime.now();
    }

    // constructor, getters, setters
}
