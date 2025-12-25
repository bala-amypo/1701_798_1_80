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
    private ClashRecordRepository clashRecordRepository;
    
    @Override
    public List<ClashRecord> detectClashes() {
        // Simplified clash detection logic
        ClashRecord clashRecord = new ClashRecord();
        clashRecord.setEvent1Id(1L);
        clashRecord.setEvent1Name("Event 1");
        clashRecord.setEvent2Id(2L);
        clashRecord.setEvent2Name("Event 2");
        clashRecord.setClashTime(LocalDateTime.now());
        clashRecord.setClashType("time");
        clashRecord.setStatus("pending");
        clashRecord.setResolved(false);
        clashRecord.setDetectedAt(LocalDateTime.now());
        clashRecord.setSeverity("medium");
        
        clashRecordRepository.save(clashRecord);
        return clashRecordRepository.findAll();
    }
    
    @Override
    public List<ClashRecord> getAllClashRecords() {
        return clashRecordRepository.findAll();
    }
    
    @Override
    public ClashRecord getClashRecordById(Long id) {
        return clashRecordRepository.findById(id).orElse(null);
    }
    
    @Override
    public ClashRecord saveClashRecord(ClashRecord clashRecord) {
        return clashRecordRepository.save(clashRecord);
    }
    
    @Override
    public ClashRecord updateClashRecordStatus(Long id, String status) {
        ClashRecord clashRecord = clashRecordRepository.findById(id).orElse(null);
        if (clashRecord != null) {
            clashRecord.setStatus(status);
            return clashRecordRepository.save(clashRecord);
        }
        return null;
    }
    
    @Override
    public void deleteClashRecord(Long id) {
        clashRecordRepository.deleteById(id);
    }
}