package com.example.demo.service.impl;

import com.example.demo.entity.AcademicEvent;
import com.example.demo.entity.EventMergeRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AcademicEventRepository;
import com.example.demo.repository.EventMergeRecordRepository;
import com.example.demo.service.EventMergeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class EventMergeServiceImpl implements EventMergeService {
    
    @Autowired
    private AcademicEventRepository academicEventRepository;
    
    @Autowired
    private EventMergeRecordRepository eventMergeRecordRepository;

    @Override
    public EventMergeRecord mergeEvents(List<Long> eventIds, String mergeReason) {
        List<AcademicEvent> events = academicEventRepository.findAllById(eventIds);
        if (events.isEmpty()) {
            throw new ResourceNotFoundException("No events found for merging");
        }
        
        EventMergeRecord mergeRecord = new EventMergeRecord();
        mergeRecord.setSourceEventIds(String.join(",", eventIds.stream().map(String::valueOf).toList()));
        
        // Determine merged dates (earliest start, latest end)
        LocalDate earliestStart = events.stream()
                .map(AcademicEvent::getStartDate)
                .min(LocalDate::compareTo)
                .orElse(LocalDate.now());
        LocalDate latestEnd = events.stream()
                .map(AcademicEvent::getEndDate)
                .max(LocalDate::compareTo)
                .orElse(LocalDate.now());
        
        mergeRecord.setMergedStartDate(earliestStart);
        mergeRecord.setMergedEndDate(latestEnd);
        mergeRecord.setMergedTitle("Merged: " + events.get(0).getTitle());
        mergeRecord.setMergeReason(mergeReason);
        
        return eventMergeRecordRepository.save(mergeRecord);
    }

    @Override
    public List<EventMergeRecord> getMergeRecordsByDate(LocalDate start, LocalDate end) {
        return eventMergeRecordRepository.findByMergedStartDateBetween(start, end);
    }
}