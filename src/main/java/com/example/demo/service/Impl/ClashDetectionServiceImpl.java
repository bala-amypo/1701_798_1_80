package com.example.demo.service.impl;

import com.example.demo.entity.ClashRecord;
import com.example.demo.repository.ClashRecordRepository;
import com.example.demo.service.ClashDetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClashDetectionServiceImpl implements ClashDetectionService {
    
    @Autowired
    private ClashRecordRepository clashRepository;
    
    @Override
    public ClashRecord detectClash(Long eventId1, Long eventId2, String clashType, String severity) {
        ClashRecord clashRecord = new ClashRecord();
        clashRecord.setEventId1(eventId1);
        clashRecord.setEventId2(eventId2);
        clashRecord.setClashType(clashType);
        clashRecord.setSeverity(severity);
        clashRecord.setDetectedAt(LocalDateTime.now());
        clashRecord.setResolved(false);
        return clashRepository.save(clashRecord);
    }
    
    @Override
    public List<ClashRecord> getClashesByEvent(Long eventId) {
        return clashRepository.findByEventId1OrEventId2(eventId, eventId);
    }
    
    @Override
    public List<ClashRecord> getAllClashes() {
        return clashRepository.findAll();
    }
    
    @Override
    public ClashRecord resolveClash(Long clashId, String resolution) {
        ClashRecord clashRecord = clashRepository.findById(clashId).orElse(null);
        if (clashRecord != null) {
            clashRecord.setResolved(true);
            clashRecord.setResolution(resolution);
            return clashRepository.save(clashRecord);
        }
        return null;
    }
    
    @Override
    public List<ClashRecord> getUnresolvedClashes() {
        return clashRepository.findByResolved(false);
    }
    
    @Override
    public List<ClashRecord> getClashesByType(String clashType) {
        return clashRepository.findByClashType(clashType);
    }
}