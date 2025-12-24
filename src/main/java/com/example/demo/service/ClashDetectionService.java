// ClashDetectionService.java
package com.example.demo.service;

import com.example.demo.entity.ClashRecord;
import java.util.List;

public interface ClashDetectionService {
    
    // These methods should match the errors:
    void logClash(ClashRecord clashRecord);  // Missing in implementation
    
    void resolveClash(Long clashId, String resolutionNotes);  // Has 2 parameters, not 1
    
    List<ClashRecord> getClashesForEvent(Long eventId);  // Missing in implementation
    
    // Add other methods that should be implemented
}