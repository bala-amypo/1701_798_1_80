package com.example.demo.service.impl;

import com.example.demo.entity.ClashRecord;
import com.example.demo.repository.ClashRecordRepository;
import com.example.demo.service.ClashDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClashDetectionServiceImpl implements ClashDetectionService {
    
    @Autowired
    private ClashRecordRepository clashRecordRepository;
    
    @Override
    public void logClash(ClashRecord clashRecord) {
        clashRecordRepository.save(clashRecord);
    }
    
    @Override
    public void resolveClash(Long clashId, String resolutionNotes) {
        ClashRecord clashRecord = clashRecordRepository.findById(clashId)
            .orElseThrow(() -> new RuntimeException("Clash record not found"));
        
        clashRecord.setResolved(true);
        // Use setResolution() instead of setResolutionNotes()
        clashRecord.setResolution(resolutionNotes);
        clashRecordRepository.save(clashRecord);
    }
    
    @Override
    public List<ClashRecord> getClashesForEvent(Long eventId) {
        // You may need to update this method based on your entity structure
        // If you need to find clashes by either eventId1 or eventId2
        // return clashRecordRepository.findByEventId1OrEventId2(eventId, eventId);
        
        // Or if you have a specific field for eventId (not in your current entity)
        // You might need to add a new repository method
        return clashRecordRepository.findByEventId1(eventId);
    }
}