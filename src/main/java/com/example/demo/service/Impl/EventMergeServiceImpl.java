package com.example.demo.service.impl;

import com.example.demo.entity.AcademicEvent;
import com.example.demo.entity.EventMergeRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AcademicEventRepository;
import com.example.demo.repository.EventMergeRecordRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventMergeServiceImpl {
    private final AcademicEventRepository academicEventRepository;
    private final EventMergeRecordRepository eventMergeRecordRepository;

    public EventMergeServiceImpl(AcademicEventRepository academicEventRepository, EventMergeRecordRepository eventMergeRecordRepository) {
        this.academicEventRepository = academicEventRepository;
        this.eventMergeRecordRepository = eventMergeRecordRepository;
    }

    public EventMergeRecord mergeEvents(List<Long> eventIds, String reason) {
        List<AcademicEvent> events = academicEventRepository.findAllById(eventIds);
        if (events.isEmpty()) {
            throw new ResourceNotFoundException("No events found");
        }
        
        EventMergeRecord record = new EventMergeRecord();
        record.setSourceEventIds(eventIds.stream().map(String::valueOf).collect(Collectors.joining(",")));
        record.setMergedTitle("Merged: " + events.get(0).getTitle());
        record.setMergedStartDate(events.stream().map(AcademicEvent::getStartDate).min(LocalDate::compareTo).orElse(LocalDate.now()));
        record.setMergedEndDate(events.stream().map(AcademicEvent::getEndDate).max(LocalDate::compareTo).orElse(LocalDate.now()));
        record.setMergeReason(reason);
        
        return eventMergeRecordRepository.save(record);
    }

    public List<EventMergeRecord> getMergeRecordsByDate(LocalDate start, LocalDate end) {
        return eventMergeRecordRepository.findByMergedStartDateBetween(start, end);
    }
}