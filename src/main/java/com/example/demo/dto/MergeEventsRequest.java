package com.example.demo.dto;

import java.util.List;

public class MergeEventsRequest {
    private List<Long> eventIds;
    private String reason;
    private String mergedTitle;
    
    // Constructors
    public MergeEventsRequest() {}
    
    public MergeEventsRequest(List<Long> eventIds, String reason, String mergedTitle) {
        this.eventIds = eventIds;
        this.reason = reason;
        this.mergedTitle = mergedTitle;
    }
    
    // Getters and Setters
    public List<Long> getEventIds() { return eventIds; }
    public void setEventIds(List<Long> eventIds) { this.eventIds = eventIds; }
    
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    
    public String getMergedTitle() { return mergedTitle; }
    public void setMergedTitle(String mergedTitle) { this.mergedTitle = mergedTitle; }
}