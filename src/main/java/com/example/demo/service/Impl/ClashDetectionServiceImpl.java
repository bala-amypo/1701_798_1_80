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
        clashRecord.setResolutionNotes(resolutionNotes);
        clashRecordRepository.save(clashRecord);
    }
    
    @Override
    public List<ClashRecord> getClashesForEvent(Long eventId) {
        return clashRecordRepository.findByEventId(eventId);
    }
    
    // Remove or fix the incorrect method signature
    // The following method should be removed if it doesn't match the interface:
    // @Override
    // public void resolveClash(Long clashId) { // WRONG - missing resolutionNotes parameter
    //     // implementation
    // }
}