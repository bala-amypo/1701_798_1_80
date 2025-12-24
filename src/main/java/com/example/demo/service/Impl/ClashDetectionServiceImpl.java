package com.example.demo.service.impl;

import com.example.demo.entity.ClashRecord;
import com.example.demo.repository.ClashRecordRepository;
import com.example.demo.service.ClashDetectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ClashDetectionServiceImpl implements ClashDetectionService {
    
    private final ClashRecordRepository clashRepository;
    
    public ClashDetectionServiceImpl(ClashRecordRepository clashRepository) {
        this.clashRepository = clashRepository;
    }
    
    @Override
    public ClashRecord detectClash(Long eventId1, Long eventId2, String description) {
        // Check if clash already exists
        List<ClashRecord> existingClashes = clashRepository.findByEventId1OrEventId2(eventId1, eventId2);
        
        // Create new clash record
        ClashRecord clash = new ClashRecord();
        clash.setEventId1(eventId1);
        clash.setEventId2(eventId2);
        clash.setDescription(description);
        clash.setStatus("DETECTED");
        clash.setResolved(false);
        clash.setCreatedAt(LocalDateTime.now());
        
        return clashRepository.save(clash);
    }
    
    @Override
    public List<ClashRecord> getUnresolvedClashes() {
        return clashRepository.findByResolvedFalse();
    }
    
    @Override
    public ClashRecord resolveClash(Long clashId, String resolution) {
        ClashRecord clash = clashRepository.findById(clashId)
                .orElseThrow(() -> new RuntimeException("Clash not found with id: " + clashId));
        
        clash.setResolution(resolution);
        clash.setStatus("RESOLVED");
        clash.setResolved(true);
        
        return clashRepository.save(clash);
    }
    
    @Override
    public List<ClashRecord> getAllClashes() {
        return clashRepository.findAll();
    }
    
    @Override
    public ClashRecord getClashById(Long id) {
        return clashRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clash not found with id: " + id));
    }
}