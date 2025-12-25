package com.example.demo.service.impl;

import com.example.demo.entity.AcademicEvent;
import com.example.demo.repository.AcademicEventRepository;
import com.example.demo.service.AcademicEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AcademicEventServiceImpl implements AcademicEventService {
    
    @Autowired
    private AcademicEventRepository eventRepository;
    
    @Override
    public AcademicEvent createEvent(AcademicEvent academicEvent) {
        return eventRepository.save(academicEvent);
    }
    
    @Override
    public AcademicEvent getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }
    
    @Override
    public List<AcademicEvent> getAllEvents() {
        return eventRepository.findAll();
    }
    
    @Override
    public AcademicEvent updateEvent(Long id, AcademicEvent eventDetails) {
        AcademicEvent event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            event.setEventName(eventDetails.getEventName());
            event.setEventType(eventDetails.getEventType());
            event.setStartTime(eventDetails.getStartTime());
            event.setEndTime(eventDetails.getEndTime());
            event.setLocation(eventDetails.getLocation());
            event.setOrganizer(eventDetails.getOrganizer());
            event.setDescription(eventDetails.getDescription());
            event.setPriority(eventDetails.getPriority());
            event.setRecurrencePattern(eventDetails.getRecurrencePattern());
            return eventRepository.save(event);
        }
        return null;
    }
    
    @Override
    public boolean deleteEvent(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return true;
        }
        return false;
    }
}