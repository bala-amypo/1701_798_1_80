package com.example.demo.service.impl;

import com.example.demo.entity.HarmonizedCalendar;
import com.example.demo.repository.AcademicEventRepository;
import com.example.demo.repository.HarmonizedCalendarRepository;
import com.example.demo.service.EventMergeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventMergeServiceImpl implements EventMergeService {
    
    @Autowired
    private HarmonizedCalendarRepository harmonizedCalendarRepository;
    
    @Autowired
    private AcademicEventRepository academicEventRepository;
    
    @Override
    public List<HarmonizedCalendar> mergeAllCalendars() {
        // Implementation logic for merging all calendars
        // This is a simplified version
        return harmonizedCalendarRepository.findAll();
    }
    
    @Override
    public HarmonizedCalendar mergeEvent(Long eventId, String sourceType) {
        // Implementation logic for merging a single event
        HarmonizedCalendar mergedEvent = new HarmonizedCalendar();
        mergedEvent.setEventName("Merged Event " + eventId);
        mergedEvent.setSourceSystem(sourceType);
        mergedEvent.setOriginalEventId(eventId);
        return harmonizedCalendarRepository.save(mergedEvent);
    }
    
    @Override
    public List<HarmonizedCalendar> getMergedEvents() {
        return harmonizedCalendarRepository.findAll();
    }
}