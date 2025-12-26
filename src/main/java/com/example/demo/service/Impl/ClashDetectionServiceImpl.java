package com.example.demo.service.impl;

import com.example.demo.entity.ClashRecord;
import com.example.demo.repository.ClashRecordRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClashDetectionServiceImpl {
    private final ClashRecordRepository clashRecordRepository;

    public ClashDetectionServiceImpl(ClashRecordRepository clashRecordRepository) {
        this.clashRecordRepository = clashRecordRepository;
    }

    public List<ClashRecord> getClashesForEvent(Long eventId) {
        return clashRecordRepository.findByEventAIdOrEventBId(eventId, eventId);
    }

    public List<ClashRecord> getUnresolvedClashes() {
        return clashRecordRepository.findByResolvedFalse();
    }

    public ClashRecord resolveClash(Long clashId) {
        ClashRecord clash = clashRecordRepository.findById(clashId).orElseThrow();
        clash.setResolved(true);
        return clashRecordRepository.save(clash);
    }
}