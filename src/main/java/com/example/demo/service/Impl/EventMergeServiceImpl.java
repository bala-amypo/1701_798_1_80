package com.example.demo.service.impl;

import com.example.demo.entity.EventMergeRecord;
import com.example.demo.repository.EventMergeRecordRepository;
import com.example.demo.service.EventMergeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventMergeServiceImpl implements EventMergeService {
    
    private final EventMergeRecordRepository mergeRepository;
    
    public EventMergeServiceImpl(EventMergeRecordRepository mergeRepository) {
        this.mergeRepository = mergeRepository;
    }
    
    @Override
    public EventMergeRecord createMergeRecord(EventMergeRecord mergeRecord) {
        return mergeRepository.save(mergeRecord);
    }
    
    @Override
    public List<EventMergeRecord> getAllMergeRecords() {
        return mergeRepository.findAll();
    }
    
    @Override
    public EventMergeRecord getMergeRecordById(Long id) {
        return mergeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Merge record not found with id: " + id));
    }
    
    @Override
    public void deleteMergeRecord(Long id) {
        EventMergeRecord record = getMergeRecordById(id);
        mergeRepository.delete(record);
    }
}