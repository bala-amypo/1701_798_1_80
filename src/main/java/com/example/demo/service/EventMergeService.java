package com.example.demo.service;

import com.example.demo.entity.EventMergeRecord;
import java.util.List;

public interface EventMergeService {
    EventMergeRecord createMergeRecord(EventMergeRecord mergeRecord);
    List<EventMergeRecord> getAllMergeRecords();
    EventMergeRecord getMergeRecordById(Long id);
    void deleteMergeRecord(Long id);
    // Add this method if your implementation has it
    List<EventMergeRecord> findByOriginalEventNames(String eventName1, String eventName2);
}