package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventMergeRecord {

    private Long id;
    private String sourceEventIds;
    private String mergedTitle;
    private LocalDate mergedStartDate;
    private LocalDate mergedEndDate;
    private String mergeReason;
    private LocalDateTime createdAt;

    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    // constructor, getters, setters
}
