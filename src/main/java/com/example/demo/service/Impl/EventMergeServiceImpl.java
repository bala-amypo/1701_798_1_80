package com.example.demo.service.impl;

import com.example.demo.entity.EventMergeRecord;
import com.example.demo.repository.EventMergeRecordRepository;
import com.example.demo.service.EventMergeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    
    @Override
    public List<EventMergeRecord> findByOriginalEventNames(String eventName1, String eventName2) {
        return mergeRepository.findByOriginalEventName1OrOriginalEventName2(eventName1, eventName2);
    }
    
    @Override
    public EventMergeRecord mergeEvents(List<Long> eventIds, String mergedEventName) {
        // Simple implementation - you might need to add more logic
        EventMergeRecord mergeRecord = new EventMergeRecord();
        mergeRecord.setOriginalEventName1("Event " + eventIds.get(0));
        mergeRecord.setOriginalEventName2("Event " + eventIds.get(1));
        mergeRecord.setMergedEventName(mergedEventName);
        mergeRecord.setMergedStartDate(LocalDate.now());
        mergeRecord.setMergedEndDate(LocalDate.now().plusDays(1));
        return mergeRepository.save(mergeRecord);
    }
    
    @Override
    public List<EventMergeRecord> getMergeRecordsByDate(LocalDate startDate, LocalDate endDate) {
        // Simple implementation - you might need to add date fields to EventMergeRecord
        return mergeRepository.findAll();
    }
}