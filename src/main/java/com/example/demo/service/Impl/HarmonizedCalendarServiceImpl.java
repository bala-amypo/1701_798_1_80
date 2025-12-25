package com.example.demo.service.impl;

import com.example.demo.entity.HarmonizedCalendar;
import com.example.demo.repository.HarmonizedCalendarRepository;
import com.example.demo.service.HarmonizedCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class HarmonizedCalendarServiceImpl implements HarmonizedCalendarService {
    
    @Autowired
    private HarmonizedCalendarRepository harmonizedCalendarRepository;
    
    @Override
    public List<HarmonizedCalendar> getAllHarmonizedCalendars() {
        return harmonizedCalendarRepository.findAll();
    }
    
    @Override
    public Optional<HarmonizedCalendar> getHarmonizedCalendarById(Long id) {
        return harmonizedCalendarRepository.findById(id);
    }
    
    @Override
    public HarmonizedCalendar saveHarmonizedCalendar(HarmonizedCalendar harmonizedCalendar) {
        return harmonizedCalendarRepository.save(harmonizedCalendar);
    }
    
    @Override
    public HarmonizedCalendar updateHarmonizedCalendar(Long id, HarmonizedCalendar harmonizedCalendar) {
        if (harmonizedCalendarRepository.existsById(id)) {
            harmonizedCalendar.setId(id);
            return harmonizedCalendarRepository.save(harmonizedCalendar);
        }
        return null;
    }
    
    @Override
    public void deleteHarmonizedCalendar(Long id) {
        harmonizedCalendarRepository.deleteById(id);
    }
    
    @Override
    public List<HarmonizedCalendar> getEventsByDateRange(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime start = LocalDateTime.parse(startDate, formatter);
        LocalDateTime end = LocalDateTime.parse(endDate, formatter);
        return harmonizedCalendarRepository.findByStartTimeBetween(start, end);
    }
    
    @Override
    public List<HarmonizedCalendar> getEventsBySource(String sourceSystem) {
        return harmonizedCalendarRepository.findBySourceSystem(sourceSystem);
    }
    
    @Override
    public List<HarmonizedCalendar> getEventsByType(String eventType) {
        return harmonizedCalendarRepository.findByEventType(eventType);
    }
}