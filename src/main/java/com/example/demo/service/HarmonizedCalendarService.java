package com.example.demo.service.impl;

import com.example.demo.entity.AcademicEvent;
import com.example.demo.entity.HarmonizedCalendar;
import com.example.demo.entity.UserAccount;
import com.example.demo.repository.AcademicEventRepository;
import com.example.demo.repository.HarmonizedCalendarRepository;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.HarmonizedCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HarmonizedCalendarServiceImpl implements HarmonizedCalendarService {

    @Autowired
    private HarmonizedCalendarRepository harmonizedCalendarRepository;

    @Autowired
    private AcademicEventRepository academicEventRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    @Transactional
    public HarmonizedCalendar generateHarmonizedCalendar(String userId, LocalDate startDate, LocalDate endDate, String timeZone) {
        Long userIdLong = Long.parseLong(userId);
        
        UserAccount userAccount = userAccountRepository.findById(userIdLong)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<AcademicEvent> academicEvents = academicEventRepository.findByUserAccountIdAndDateRange(userIdLong, startDate, endDate);

        List<AcademicEvent> mergedEvents = mergeOverlappingEvents(academicEvents, timeZone);
        HarmonizedCalendar calendar = new HarmonizedCalendar();
        calendar.setUserAccount(userAccount);
        calendar.setStartDate(startDate);
        calendar.setEndDate(endDate);
        calendar.setTimeZone(timeZone);
        calendar.setGeneratedAt(LocalDateTime.now());
        
        calendar.setTotalEvents(mergedEvents.size());
        calendar.setUniqueDays(calculateUniqueDays(mergedEvents));
                return harmonizedCalendarRepository.save(calendar);
    }

    @Override
    public List<HarmonizedCalendar> getCalendarsWithinRange(LocalDate startDate, LocalDate endDate) {
        return harmonizedCalendarRepository.findByStartDateBetween(startDate, endDate);
    }

    @Override
    public HarmonizedCalendar getHarmonizedCalendarById(String id) {
        Long idLong = Long.parseLong(id);
        return harmonizedCalendarRepository.findById(idLong)
                .orElseThrow(() -> new RuntimeException("Harmonized calendar not found"));
    }

    @Override
    public List<HarmonizedCalendar> getHarmonizedCalendarsByUserId(String userId) {
        Long userIdLong = Long.parseLong(userId);
        return harmonizedCalendarRepository.findByUserAccountId(userIdLong);
    }

    @Override
    public void deleteHarmonizedCalendar(String id) {
        Long idLong = Long.parseLong(id);
        harmonizedCalendarRepository.deleteById(idLong);
    }

    @Override
    public HarmonizedCalendar updateHarmonizedCalendar(String id, HarmonizedCalendar updatedCalendar) {
        Long idLong = Long.parseLong(id);
        HarmonizedCalendar existingCalendar = getHarmonizedCalendarById(String.valueOf(idLong));
        
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

    private List<AcademicEvent> mergeOverlappingEvents(List<AcademicEvent> events, String timeZone) {
        if (events.isEmpty()) {
            return new ArrayList<>();
        }

        // Sort events by start time
        List<AcademicEvent> sortedEvents = events.stream()
                .sorted(Comparator.comparing(AcademicEvent::getStartTime))
                .collect(Collectors.toList());

        List<AcademicEvent> mergedEvents = new ArrayList<>();
        AcademicEvent currentEvent = sortedEvents.get(0);

        for (int i = 1; i < sortedEvents.size(); i++) {
            AcademicEvent nextEvent = sortedEvents.get(i);
            
            // Check if events overlap
            if (currentEvent.getEndTime().isAfter(nextEvent.getStartTime())) {
                // Merge overlapping events
                if (currentEvent.getEndTime().isBefore(nextEvent.getEndTime())) {
                    currentEvent.setEndTime(nextEvent.getEndTime());
                }
                // Merge titles or descriptions
                currentEvent.setEventName(currentEvent.getEventName() + " / " + nextEvent.getEventName());
            } else {
                mergedEvents.add(currentEvent);
                currentEvent = nextEvent;
            }
        }
        
        mergedEvents.add(currentEvent);
        return mergedEvents;
    }

    private int calculateUniqueDays(List<AcademicEvent> events) {
        return (int) events.stream()
                .map(event -> event.getStartTime().toLocalDate())
                .distinct()
                .count();
    }
}