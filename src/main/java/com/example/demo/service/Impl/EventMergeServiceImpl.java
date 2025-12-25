package com.example.demo.service.Impl;

import com.example.demo.entity.AcademicEvent;
import com.example.demo.entity.HarmonizedCalendar;
import com.example.demo.repository.AcademicEventRepository;
import com.example.demo.repository.HarmonizedCalendarRepository;
import com.example.demo.service.EventMergeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventMergeServiceImpl implements EventMergeService {
    
    @Autowired
    private AcademicEventRepository academicEventRepository;
    
    @Autowired
    private HarmonizedCalendarRepository harmonizedCalendarRepository;
    
    @Override
    public List<HarmonizedCalendar> mergeEvents() {
        List<HarmonizedCalendar> mergedCalendars = new ArrayList<>();
        
        List<AcademicEvent> academicEvents = academicEventRepository.findAll();
        
        for (AcademicEvent event : academicEvents) {
            HarmonizedCalendar calendar = new HarmonizedCalendar();
            calendar.setCalendarName(event.getEventName());
            calendar.setDescription(event.getDescription());
            
            if (event.getStartTime() != null) {
                calendar.setEffectiveFrom(event.getStartTime().toLocalDate());
            }
            
            if (event.getEndTime() != null) {
                calendar.setEffectiveTo(event.getEndTime().toLocalDate());
            }
            
            calendar.setPriority(event.getPriority());
            calendar.setCalendarId(event.getId());
            calendar.setCreatedAt(LocalDateTime.now());
            calendar.setUpdatedAt(LocalDateTime.now());
            calendar.setIsActive(true);
            
            mergedCalendars.add(calendar);
        }
        
        harmonizedCalendarRepository.saveAll(mergedCalendars);
        
        return mergedCalendars;
    }
    
    @Override
    public List<HarmonizedCalendar> mergeEventsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<HarmonizedCalendar> mergedCalendars = new ArrayList<>();
        
        List<AcademicEvent> academicEvents = academicEventRepository.findAll();
        
        for (AcademicEvent event : academicEvents) {
            if (event.getStartTime() != null && 
                !event.getStartTime().toLocalDate().isBefore(startDate) &&
                !event.getStartTime().toLocalDate().isAfter(endDate)) {
                
                HarmonizedCalendar calendar = new HarmonizedCalendar();
                calendar.setCalendarName(event.getEventName());
                calendar.setDescription(event.getDescription());
                calendar.setEffectiveFrom(event.getStartTime().toLocalDate());
                
                if (event.getEndTime() != null) {
                    calendar.setEffectiveTo(event.getEndTime().toLocalDate());
                }
                
                calendar.setPriority(event.getPriority());
                calendar.setCalendarId(event.getId());
                calendar.setCreatedAt(LocalDateTime.now());
                calendar.setUpdatedAt(LocalDateTime.now());
                calendar.setIsActive(true);
                
                mergedCalendars.add(calendar);
            }
        }
        
        harmonizedCalendarRepository.saveAll(mergedCalendars);
        
        return mergedCalendars;
    }
    
    @Override
    public List<HarmonizedCalendar> getMergeRecordsByDate(LocalDate startDate, LocalDate endDate) {
        return harmonizedCalendarRepository.findByEffectiveFromBetween(startDate, endDate);
    }
}