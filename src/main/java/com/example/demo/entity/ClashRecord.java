package com.example.demo.entity;

import java.time.LocalDateTime;

public class ClashRecord {

    private Long id;
    private Long eventAId;
    private Long eventBId;
    private String clashType;
    private String severity;
    private String remarks;
    private LocalDateTime detectedAt;
    private Boolean resolved;

    public void prePersist() {
        if (detectedAt == null) detectedAt = LocalDateTime.now();
        if (resolved == null) resolved = false;
    }

    // constructor, getters, setters
}
