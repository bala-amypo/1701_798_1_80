package com.example.demo.service.impl;

import com.example.demo.entity.AcademicEvent;
import com.example.demo.entity.HarmonizedCalendar;
import com.example.demo.repository.AcademicEventRepository;
import com.example.demo.repository.HarmonizedCalendarRepository;
import com.example.demo.service.HarmonizedCalendarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class HarmonizedCalendarServiceImpl implements HarmonizedCalendarService {
    
    private final HarmonizedCalendarRepository calendarRepository;
    private final AcademicEventRepository eventRepository;
    private final ObjectMapper objectMapper;
    
    public HarmonizedCalendarServiceImpl(HarmonizedCalendarRepository calendarRepository,
                                        AcademicEventRepository eventRepository,
                                        ObjectMapper objectMapper) {
        this.calendarRepository = calendarRepository;
        this.eventRepository = eventRepository;
        this.objectMapper = objectMapper;
    }
    
    @Override
    public HarmonizedCalendar generateHarmonizedCalendar(String title, String generatedBy) {
        List<AcademicEvent> allEvents = eventRepository.findAll();
        
        LocalDate earliestStart = allEvents.stream()
                .map(AcademicEvent::getStartDate)
                .min(LocalDate::compareTo)
                .orElse(LocalDate.now());
        
        LocalDate latestEnd = allEvents.stream()
                .map(AcademicEvent::getEndDate)
                .max(LocalDate::compareTo)
                .orElse(LocalDate.now().plusMonths(6));
        
        try {
            String eventsJson = objectMapper.writeValueAsString(allEvents);
            
            HarmonizedCalendar calendar = new HarmonizedCalendar(
                    title, generatedBy, earliestStart, latestEnd, eventsJson);
            
            return calendarRepository.save(calendar);
        } catch (Exception e) {
            throw new RuntimeException("Error generating harmonized calendar", e);
        }
    }
    
    @Override
    public HarmonizedCalendar getCalendarById(Long id) {
        return calendarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calendar not found with id: " + id));
    }
    
    @Override
    public List<HarmonizedCalendar> getAllCalendars() {
        return calendarRepository.findAll();
    }
    
    @Override
    public List<HarmonizedCalendar> getCalendarsWithinRange(LocalDate start, LocalDate end) {
        return calendarRepository.findByEffectiveFromBetween(start, end);
    }
}