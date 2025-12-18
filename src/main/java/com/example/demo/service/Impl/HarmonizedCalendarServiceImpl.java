package com.example.demo.service.impl;

import com.example.demo.entity.CalendarEvent;
import com.example.demo.entity.HarmonizedCalendar;
import com.example.demo.entity.User;
import com.example.demo.repository.CalendarEventRepository;
import com.example.demo.repository.HarmonizedCalendarRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.HarmonizedCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HarmonizedCalendarServiceImpl implements HarmonizedCalendarService {

    @Autowired
    private HarmonizedCalendarRepository harmonizedCalendarRepository;

    @Autowired
    private CalendarEventRepository calendarEventRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public HarmonizedCalendar generateHarmonizedCalendar(String userId, LocalDate startDate, LocalDate endDate, String timeZone) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch calendar events for the user within the date range
        List<CalendarEvent> calendarEvents = calendarEventRepository.findByUserIdAndDateRange(userId, startDate, endDate);

        // Process and merge events (simplified logic)
        List<CalendarEvent> mergedEvents = mergeOverlappingEvents(calendarEvents, timeZone);

        // Create harmonized calendar
        HarmonizedCalendar calendar = new HarmonizedCalendar();
        calendar.setUser(user);
        calendar.setStartDate(startDate);
        calendar.setEndDate(endDate);
        calendar.setTimeZone(timeZone);
        calendar.setGeneratedAt(LocalDateTime.now());
        
        // Set events (you might need to add this method to the entity)
        // calendar.setEvents(mergedEvents); // Uncomment if you have this field
        
        // Calculate statistics
        calendar.setTotalEvents(mergedEvents.size());
        calendar.setUniqueDays(calculateUniqueDays(mergedEvents));
        
        // Save the harmonized calendar
        return harmonizedCalendarRepository.save(calendar);
    }

    @Override
    public List<HarmonizedCalendar> getCalendarsWithinRange(LocalDate startDate, LocalDate endDate) {
        // Implement logic to fetch calendars within the date range
        // This depends on your repository methods
        return harmonizedCalendarRepository.findByStartDateBetween(startDate, endDate);
    }

    private List<CalendarEvent> mergeOverlappingEvents(List<CalendarEvent> events, String timeZone) {
        if (events.isEmpty()) {
            return new ArrayList<>();
        }

        // Sort events by start time
        List<CalendarEvent> sortedEvents = events.stream()
                .sorted(Comparator.comparing(CalendarEvent::getStartTime))
                .collect(Collectors.toList());

        List<CalendarEvent> mergedEvents = new ArrayList<>();
        CalendarEvent currentEvent = sortedEvents.get(0);

        for (int i = 1; i < sortedEvents.size(); i++) {
            CalendarEvent nextEvent = sortedEvents.get(i);
            
            // Check if events overlap (simplified logic)
            if (currentEvent.getEndTime().isAfter(nextEvent.getStartTime())) {
                // Merge overlapping events
                if (currentEvent.getEndTime().isBefore(nextEvent.getEndTime())) {
                    currentEvent.setEndTime(nextEvent.getEndTime());
                }
                // Merge titles or other properties as needed
                currentEvent.setTitle(currentEvent.getTitle() + " / " + nextEvent.getTitle());
            } else {
                mergedEvents.add(currentEvent);
                currentEvent = nextEvent;
            }
        }
        
        mergedEvents.add(currentEvent);
        return mergedEvents;
    }

    private int calculateUniqueDays(List<CalendarEvent> events) {
        return (int) events.stream()
                .map(event -> event.getStartTime().toLocalDate())
                .distinct()
                .count();
    }

    @Override
    public HarmonizedCalendar getHarmonizedCalendarById(String id) {
        return harmonizedCalendarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Harmonized calendar not found"));
    }

    @Override
    public List<HarmonizedCalendar> getHarmonizedCalendarsByUserId(String userId) {
        return harmonizedCalendarRepository.findByUserId(userId);
    }

    @Override
    public void deleteHarmonizedCalendar(String id) {
        harmonizedCalendarRepository.deleteById(id);
    }

    @Override
    public HarmonizedCalendar updateHarmonizedCalendar(String id, HarmonizedCalendar updatedCalendar) {
        HarmonizedCalendar existingCalendar = getHarmonizedCalendarById(id);
        
        // Update fields if they are not null in the updatedCalendar
        if (updatedCalendar.getStartDate() != null) {
            existingCalendar.setStartDate(updatedCalendar.getStartDate());
        }
        if (updatedCalendar.getEndDate() != null) {
            existingCalendar.setEndDate(updatedCalendar.getEndDate());
        }
        if (updatedCalendar.getTimeZone() != null) {
            existingCalendar.setTimeZone(updatedCalendar.getTimeZone());
        }
        
        return harmonizedCalendarRepository.save(existingCalendar);
    }
}