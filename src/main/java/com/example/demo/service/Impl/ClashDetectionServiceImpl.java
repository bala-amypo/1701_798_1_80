package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.entity.ClashRecord;
import com.example.demo.repository.ClashRecordRepository;
import com.example.demo.service.ClashDetectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClashDetectionServiceImpl implements ClashDetectionService {
    
    private final ClashRecordRepository clashRepository;
    
    public ClashDetectionServiceImpl(ClashRecordRepository clashRepository) {
        this.clashRepository = clashRepository;
    }
    
    @Override
    public ClashRecord logClash(ClashRecord clash) {
        return clashRepository.save(clash);
    }
    
    @Override
    public List<ClashRecord> getClashesForEvent(Long eventId) {
        return clashRepository.findByEventAIdOrEventBId(eventId, eventId);
    }
    
    @Override
    public ClashRecord resolveClash(Long clashId) {
        ClashRecord clash = clashRepository.findById(clashId)
                .orElseThrow(() -> new ResourceNotFoundException("Clash not found with id: " + clashId));
        clash.setResolved(true);
        return clashRepository.save(clash);
    }
    
    @Override
    public List<ClashRecord> getUnresolvedClashes() {
        return clashRepository.findByResolvedFalse();
    }
    
    @Override
    public List<ClashRecord> getAllClashes() {
        return clashRepository.findAll();
    }
}