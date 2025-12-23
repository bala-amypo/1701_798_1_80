package com.example.demo.service.impl;

import com.example.demo.exception.ValidationException;
import com.example.demo.exception.ResourceNotFoundException;
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
    
    @Override
    public AcademicEvent createEvent(AcademicEvent event) {
        if (event.getStartDate().isAfter(event.getEndDate())) {
            throw new ValidationException("Start date must be before or equal to end date");
        }
        
        if (event.getStartDate().isBefore(LocalDate.now())) {
            throw new ValidationException("Start date cannot be in the past");
        }
        
        return eventRepository.save(event);
    }
    
    @Override
    public List<AcademicEvent> getEventsByBranch(Long branchId) {
        return eventRepository.findByBranchId(branchId);
    }
    
    @Override
    public AcademicEvent updateEvent(Long id, AcademicEvent event) {
        AcademicEvent existingEvent = getEventById(id);
        
        if (event.getStartDate().isAfter(event.getEndDate())) {
            throw new ValidationException("Start date must be before or equal to end date");
        }
        
        String eventTitle = event.getEventName();
        existingEvent.setStartDate(event.getStartDate());
        existingEvent.setEndDate(event.getEndDate());
        existingEvent.setLocation(event.getLocation());
        String eventLocation = event.getLocation();
        
        return eventRepository.save(existingEvent);
    }
    
    @Override
    public AcademicEvent getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
    }
    
    @Override
    public List<AcademicEvent> getAllEvents() {
        return eventRepository.findAll();
    }
}