package com.example.demo.service.impl;

import com.example.demo.entity.AcademicEvent;
import com.example.demo.repository.AcademicEventRepository;
import com.example.demo.service.AcademicEventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class AcademicEventServiceImpl implements AcademicEventService {
    private final AcademicEventRepository eventRepository;
    public AcademicEventServiceImpl(AcademicEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    
    @Override public AcademicEvent createEvent(AcademicEvent event) {
        if (event.getStartDate().isAfter(event.getEndDate()))
            throw new RuntimeException("Start date must be before end date");
        if (event.getStartDate().isBefore(LocalDate.now()))
            throw new RuntimeException("Start date cannot be in the past");
        return eventRepository.save(event);
    }
    @Override public List<AcademicEvent> getEventsByBranch(Long branchId) {
        return eventRepository.findByBranchId(branchId);
    }
    @Override public AcademicEvent updateEvent(Long id, AcademicEvent event) {
        AcademicEvent existing = getEventById(id);
        if (event.getStartDate().isAfter(event.getEndDate()))
            throw new RuntimeException("Start date must be before end date");
        existing.setEventName(event.getEventName());
        existing.setEventType(event.getEventType());
        existing.setStartDate(event.getStartDate());
        existing.setEndDate(event.getEndDate());
        existing.setLocation(event.getLocation());
        existing.setDescription(event.getDescription());
        existing.setStatus(event.getStatus());
        return eventRepository.save(existing);
    }
    @Override public AcademicEvent getEventById(Long id) {
        return eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Event not found: " + id));
    }
    @Override public List<AcademicEvent> getAllEvents() { return eventRepository.findAll(); }
    @Override public void deleteEvent(Long id) {
        AcademicEvent event = getEventById(id);
        eventRepository.delete(event);
    }
    @Override public List<AcademicEvent> getEventsByDateRange(LocalDate startDate, LocalDate endDate) {
        return eventRepository.findByStartDateBetween(startDate, endDate);
    }
}