package com.example.demo.service.impl;

import com.example.demo.entity.HarmonizedCalendar;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.HarmonizedCalendarRepository;
import com.example.demo.service.HarmonizedCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class HarmonizedCalendarServiceImpl implements HarmonizedCalendarService {
    
    private final HarmonizedCalendarRepository harmonizedCalendarRepository;
    
    @Autowired
    public HarmonizedCalendarServiceImpl(HarmonizedCalendarRepository harmonizedCalendarRepository) {
        this.harmonizedCalendarRepository = harmonizedCalendarRepository;
    }
    
    @Override
    public HarmonizedCalendar generateHarmonizedCalendar(String title, String generatedBy) {
        HarmonizedCalendar calendar = new HarmonizedCalendar();
        calendar.setTitle(title);
        calendar.setGeneratedBy(generatedBy);
        calendar.setEffectiveFrom(LocalDate.now());
        calendar.setEffectiveTo(LocalDate.now().plusMonths(3));
        calendar.setEventsJson("[]"); // Empty JSON array for now
        
        return harmonizedCalendarRepository.save(calendar);
    }
    
    @Override
    public HarmonizedCalendar getCalendarById(Long id) {
        return harmonizedCalendarRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calendar not found with id: " + id));
    }
    
    @Override
    public List<HarmonizedCalendar> getAllCalendars() {
        return harmonizedCalendarRepository.findAll();
    }
    
    @Override
    public List<HarmonizedCalendar> getCalendarsWithinRange(LocalDate start, LocalDate end) {
        return harmonizedCalendarRepository.findCalendarsWithinRange(start, end);
    }
}