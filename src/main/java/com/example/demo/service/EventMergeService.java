package com.example.demo.service;

import com.example.demo.entity.EventMergeRecord;
import java.time.LocalDate;
import java.util.List;

public interface EventMergeService {
    EventMergeRecord createMergeRecord(EventMergeRecord mergeRecord);
    List<EventMergeRecord> getAllMergeRecords();
    EventMergeRecord getMergeRecordById(Long id);
    void deleteMergeRecord(Long id);
    List<EventMergeRecord> findByOriginalEventNames(String eventName1, String eventName2);
    EventMergeRecord mergeEvents(List<Long> eventIds, String mergedEventName);
    List<EventMergeRecord> getMergeRecordsByDate(LocalDate startDate, LocalDate endDate);
}