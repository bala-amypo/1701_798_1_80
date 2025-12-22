package com.example.demo.service.impl;

import com.example.demo.exception.ValidationException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.entity.AcademicEvent;
import com.example.demo.entity.EventMergeRecord;
import com.example.demo.repository.AcademicEventRepository;
import com.example.demo.repository.EventMergeRecordRepository;
import com.example.demo.service.EventMergeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventMergeServiceImpl implements EventMergeService {
    
    private final EventMergeRecordRepository mergeRepository;
    private final AcademicEventRepository eventRepository;
    
    public EventMergeServiceImpl(EventMergeRecordRepository mergeRepository, 
                                AcademicEventRepository eventRepository) {
        this.mergeRepository = mergeRepository;
        this.eventRepository = eventRepository;
    }
    
    @Override
    public EventMergeRecord mergeEvents(List<Long> eventIds, String reason) {
        if (eventIds == null || eventIds.size() < 2) {
            throw new ValidationException("At least two events are required for merging");
        }
        
        List<AcademicEvent> events = eventIds.stream()
                .map(id -> eventRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id)))
                .collect(Collectors.toList());
        
        LocalDate earliestStart = events.stream()
                .map(AcademicEvent::getStartDate)
                .min(LocalDate::compareTo)
                .orElseThrow(() -> new ValidationException("No valid start dates found"));
        
        LocalDate latestEnd = events.stream()
                .map(AcademicEvent::getEndDate)
                .max(LocalDate::compareTo)
                .orElseThrow(() -> new ValidationException("No valid end dates found"));
        
        String mergedTitle = "Merged: " + events.get(0).getTitle();
        String sourceEventIds = eventIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        
        EventMergeRecord mergeRecord = new EventMergeRecord(
                sourceEventIds, mergedTitle, earliestStart, latestEnd, reason);
        
        return mergeRepository.save(mergeRecord);
    }
    
    @Override
    public List<EventMergeRecord> getAllMergeRecords() {
        return mergeRepository.findAll();
    }
    
    @Override
    public EventMergeRecord getMergeRecordById(Long id) {
        return mergeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Merge record not found with id: " + id));
    }
    
    @Override
    public List<EventMergeRecord> getMergeRecordsByDate(LocalDate start, LocalDate end) {
        return mergeRepository.findByMergedStartDateBetween(start, end);
    }
}