package com.example.demo.entity;

import java.time.LocalDateTime;

public class BranchProfile {

    private Long id;
    private String branchCode;
    private String branchName;
    private String contactEmail;
    private LocalDateTime lastSyncAt;
    private Boolean active;

    public void prePersist() {
        if (lastSyncAt == null) lastSyncAt = LocalDateTime.now();
        if (active == null) active = true;
    }

    // constructor, getters, setters
}
